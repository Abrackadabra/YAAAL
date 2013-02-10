package abrackadabra.structure;

import java.math.BigInteger;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 10/02/13
 * Time: 01:07
 * To change this template use File | Settings | File Templates.
 */
public class Polynomial {
    private static class Token {
        enum Type {
            POLYNOMIAL, OPERATOR
        }

        Type type;

        Polynomial polynomial = new Polynomial();

        char operator;

        private boolean isNumber(String s) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }

        private boolean isVariable(String s) {
            return s.length() == 1 && s.charAt(0) >= 'a' && s.charAt(0) <= 'z';
        }

        Token(String s) {
            if (isVariable(s)) {
                type = Type.POLYNOMIAL;
                polynomial.coeffs.put(1, 1L);
                return;
            }

            if (isNumber(s)) {
                type = Type.POLYNOMIAL;
                polynomial.coeffs.put(0, Long.parseLong(s));
                return;
            }

            if (s.length() == 1) {
                operator = s.charAt(0);
                type = Type.OPERATOR;
                return;
            }

            throw new IllegalArgumentException();
        }
    }

    Map<Integer, Long> coeffs = new HashMap<Integer, Long>();

    private Polynomial() {
    }

    private Polynomial(Map<Integer, Long> coeffs) {
        if (coeffs == null) {
            throw new NullPointerException();
        }
        this.coeffs.putAll(coeffs);
    }

    private List<Token> parse(String s) {
        List<Token> tokens = new ArrayList<Token>();

        char[] chars = s.toCharArray();

        int n = chars.length;

        for (int i = 0; i < n; i++) {
            if (chars[i] == ' ') {
                continue;
            }

            if (chars[i] >= '0' && chars[i] <= '9') {
                int r = i + 1;
                for (; r < n && chars[r] >= '0' && chars[r] <= '9'; r++) ;
                tokens.add(new Token(s.substring(i, r)));
                i = r - 1;
                continue;
            }

            tokens.add(new Token(s.substring(i, i + 1)));
        }

        return tokens;
    }

    private int priority(int operator) {
        if (operator < 0)
            return 4; // op == -'+' || op == -'-'
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    Polynomial inverse() {
        Map<Integer, Long> map = new HashMap<Integer, Long>();

        for (Map.Entry<Integer, Long> e : coeffs.entrySet()) {
            map.put(e.getKey(), -e.getValue());
        }

        return new Polynomial(map);
    }

    Polynomial add(Polynomial arg) {
        Map<Integer, Long> map = new HashMap<Integer, Long>();

        Set<Integer> keys = new HashSet<Integer>();
        keys.addAll(coeffs.keySet());
        keys.addAll(arg.coeffs.keySet());

        for (Integer key : keys) {
            long val = 0;
            if (coeffs.containsKey(key)) val += coeffs.get(key);
            if (arg.coeffs.containsKey(key)) val += arg.coeffs.get(key);
            if (val != 0)
                map.put(key, val);
        }

        return new Polynomial(map);
    }

    Polynomial subtract(Polynomial arg) {
        Map<Integer, Long> map = new HashMap<Integer, Long>();

        Set<Integer> keys = new HashSet<Integer>();
        keys.addAll(coeffs.keySet());
        keys.addAll(arg.coeffs.keySet());

        for (Integer key : keys) {
            long val = 0;
            if (coeffs.containsKey(key)) val += coeffs.get(key);
            if (arg.coeffs.containsKey(key)) val -= arg.coeffs.get(key);
            if (val != 0)
                map.put(key, val);
        }

        return new Polynomial(map);
    }

    Polynomial multiply(Polynomial arg) {
        Map<Integer, Long> map = new HashMap<Integer, Long>();

        for (Map.Entry<Integer, Long> a : coeffs.entrySet()) {
            for (Map.Entry<Integer, Long> b : arg.coeffs.entrySet()) {
                int degree = a.getKey() + b.getKey();
                long val = a.getValue() * b.getValue();
                    /*if (Math.log(a.getValue()) + Math.log(b.getValue()) > Math.log(Long.MAX_VALUE)) {
                        throw new IllegalArgumentException();
                    }*/
                if (map.containsKey(degree)) {
                    val += map.get(degree);
                }
                if (val != 0) {
                    map.put(degree, val);
                } else {
                    if (map.containsKey(degree)) {
                        map.remove(degree);
                    }
                }
            }
        }

        return new Polynomial(map);
    }

    Polynomial pow(int arg) {
        if (arg == 0) {
            if (coeffs.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Map<Integer, Long> map = new HashMap<Integer, Long>();
            map.put(0, 1L);
            return new Polynomial(map);
        }

        if (arg < 0) {
            throw new IllegalArgumentException();
        }

        Map<Integer, Long> map = new HashMap<Integer, Long>();
        map.put(0, 1L);

        Polynomial result = new Polynomial(map);

        for (int i = 1; i <= arg; i++) {
            result = result.multiply(this);
        }

        return result;
    }

    private void applyOperator(Stack<Polynomial> stack, int operator) {
        if (operator < 0) {
            Polynomial l = stack.pop();
            switch (-operator) {
                case '+':
                    stack.push(l);
                    break;
                case '-':
                    stack.push(l.inverse());
                    break;
            }
        } else {
            Polynomial r = stack.pop();
            Polynomial l = stack.pop();
            switch (operator) {
                case '+':
                    stack.push(l.add(r));
                    break;
                case '-':
                    stack.push(l.subtract(r));
                    break;
                case '*':
                    stack.push(l.multiply(r));
                    break;
                case '^':
                    stack.push(l.pow((int) (long) r.coeffs.get(0)));
                    break;
            }
        }
    }

    public Polynomial(String s) {
        if (s.contains("=")) {
            s = s.substring(0, s.indexOf("=")) + "-(" + s.substring(s.indexOf("=") + 1, s.length()) + ")";
        }
        s = s.replace(")(", ")*(");

        List<Token> tokens = parse(s);

        boolean mayUnary = true;
        Stack<Polynomial> stack = new Stack<Polynomial>();
        Stack<Integer> operators = new Stack<Integer>();

        int reducingOperators = 0;

        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).operator == '(') {
                operators.push((int) '(');
                mayUnary = true;
            } else if (tokens.get(i).operator == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    if (operators.peek() > 0) {
                        reducingOperators--;
                    }
                    applyOperator(stack, operators.pop());
                }
                operators.pop();

                mayUnary = false;
            } else if (tokens.get(i).type == Token.Type.OPERATOR) {
                int operator = tokens.get(i).operator;

                if (mayUnary && (operator == '+' || operator == '-')) operator = -operator;

                while (!operators.empty() && priority(operators.peek()) >= priority(operator)) {
                    if (operators.peek() > 0) {
                        reducingOperators--;
                    }
                    applyOperator(stack, operators.pop());
                }

                operators.push(operator);
                if (operator > 0) {
                    reducingOperators++;
                }
                mayUnary = true;
            } else {
                stack.push(tokens.get(i).polynomial);
                mayUnary = false;
            }
            while (stack.size() > reducingOperators + 1) {
                operators.push((int) '*');
                reducingOperators++;
            }
        }
        while (!operators.empty()) {
            applyOperator(stack, operators.pop());
        }

        coeffs = new HashMap<Integer, Long>();
        coeffs.putAll(stack.pop().coeffs);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Set<Integer> keys = new TreeSet<Integer>();
        keys.addAll(coeffs.keySet());

        for (Integer key : keys) {
            long val = coeffs.get(key);

            if (val >= 0) {
                stringBuilder.append('+');
            }
            stringBuilder.append(val);

            if (key > 0) {
                stringBuilder.append('x');
            }
            if (key > 1) {
                stringBuilder.append('^');
                stringBuilder.append(key);
            }
        }
        return stringBuilder.toString();
    }

    public boolean isRoot(int x) {
        BigInteger value = BigInteger.ZERO;
        BigInteger X = BigInteger.valueOf(x);

        for (int i = 0; i <= 10; i++) {
            if (coeffs.containsKey(i))
                value = value.add(X.pow(i).multiply(BigInteger.valueOf(coeffs.get(i))));
        }

        return value.compareTo(BigInteger.ZERO) == 0;
    }

}
