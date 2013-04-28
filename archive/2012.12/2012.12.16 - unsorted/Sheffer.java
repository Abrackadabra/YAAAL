package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class Sheffer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int n = 1;
        int d = 2;
        while (d < s.length()) {
            d <<= 1;
            n++;
        }

        boolean[] a = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i) == '1';
        }

        out.println(getExpression(a));


        if (1 == 1) {
            return;
        }

        Random random = new Random();

        while (true) {
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextBoolean();
            }

            for (int i = 0; i < d; i++) {
                s = getExpression(a).toString();
                for (int bit = 0; bit < n; bit++) {
                    boolean val = ((1 << bit) & i) > 0;

                    s = s.replace((char) ('a' + bit), val ? '1' : '0');
                }

                if (a[i] != calc(s)) {
                    System.out.println(Arrays.toString(a));
                    System.exit(1);
                }

                //out.println(s);
                System.out.println(a[i] + " " + calc(s));
            }
        }
    }

    String getExpression(boolean[] a) {
        int n = 1;
        int d = 2;
        while (d < a.length) {
            d <<= 1;
            n++;
        }

        ArrayList<Expression> parts = new ArrayList<Expression>();
        for (int i = 0; i < a.length; i++) {
            if (!a[i]) {
                continue;
            }

            Expression expression = Expression.TRUE;
            for (int bit = 0; bit < n; bit++) {
                boolean val = ((1 << (n - bit - 1)) & i) > 0;

                Expression that = new Expression((char) (bit + 'a'));
                if (!val) {
                    that = that.negate();
                }

                expression = expression.and(that);
            }

            parts.add(expression);
        }

        if (parts.isEmpty()) {
            return Expression.FALSE.toString();
        }

        Expression ans = Expression.FALSE;
        for (Expression part : parts) {
            ans = ans.or(part);
        }
        return ans.toString();
    }

    boolean calc(String s) {
        Stack<Boolean> stack = new Stack<Boolean>();
        for (char c : s.toCharArray()) {
            if (c == '1') {
                stack.push(true);
            }
            if (c == '0') {
                stack.push(false);
            }
            if (c == '|') {
                stack.push(shefer(stack.pop(), stack.pop()));
            }
        }
        if (stack.size() != 1) {
            System.exit(12);
        }
        return stack.pop();
    }

    boolean shefer(boolean a, boolean b) {
        return (!a) | (!b);
    }
}

class Expression {
    public static final Expression TRUE = new Expression(Arrays.asList('a', 'a', '|', 'a', '|'));
    public static final Expression FALSE = TRUE.negate();

    private final List<Character> a;

    public Expression(char c) {
        a = new ArrayList<Character>();
        a.add(c);
    }

    public Expression(List<Character> a) {
        this.a = a;
    }

    public Expression negate() {
        ArrayList<Character> res = new ArrayList<Character>();
        res.addAll(a);
        res.addAll(TRUE.a);
        res.add('|');
        return new Expression(res);
    }

    public Expression or(Expression e) {
        ArrayList<Character> res = new ArrayList<Character>();
        res.addAll(negate().a);
        res.addAll(e.negate().a);
        res.add('|');
        return new Expression(res);
    }

    public Expression and(Expression e) {
        ArrayList<Character> res = new ArrayList<Character>();
        res.addAll(a);
        res.addAll(e.a);
        res.add('|');
        res.addAll(TRUE.a);
        res.add('|');
        return new Expression(res);
    }

    @Override
    public String toString() {
        char[] chars = new char[a.size()];

        int i = 0;
        for (char c : a) {
            chars[i++] = c;
        }

        return new String(chars);
    }
}