package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.*;

public class J {

    HashMap<String, Integer> romans = new HashMap<String, Integer>();
    String tens[] = new String[]{"","X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String units[] = new String[]{"","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        TreeSet<String> answer =  new TreeSet<String>();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if(i == 0 && j==0)
                    continue;
                romans.put(tens[i] + units[j], 10 * i + j);
            }
        }
        String s = in.nextString();
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        for(int i = 0; i < s.length(); ++i){
            sb.append(s.charAt(i)).append(' ');
        }
        Map<Character, HashSet<Character>> same = new HashMap<Character, HashSet<Character>>();
        same.put('I', new HashSet<Character>());
        same.put('-', new HashSet<Character>());
        same.put('L', new HashSet<Character>());
        same.put('=', new HashSet<Character>());
        same.put('V', new HashSet<Character>());
        same.put('+', new HashSet<Character>());
        same.put('X', new HashSet<Character>());
        same.get('I').add('-');
        same.get('-').add('I');
        same.get('L').add('+');
        same.get('+').add('L');
        same.get('=').add('+');
        same.get('+').add('=');
        same.get('V').add('X');
        same.get('X').add('V');

        int n = sb.length();

        for (int i = 0; i < n; ++i) {
            char real = sb.charAt(i);

            if (same.containsKey(real))
                for (Character c : same.get(real)) {
                    sb.setCharAt(i, c);

                    if (isCorrect(sb)) {
                       answer.add(trim(sb));
                        //return;
                    }
                }

            sb.setCharAt(i, real);
        }


        Map<Character, HashSet<Character>> inc = new HashMap<Character, HashSet<Character>>();
        Map<Character, HashSet<Character>> dec = new HashMap<Character, HashSet<Character>>();

        inc.put('I', new HashSet<Character>());
        inc.put('-', new HashSet<Character>());
        inc.put(' ', new HashSet<Character>());


        dec.put('L', new HashSet<Character>());
        dec.put('+', new HashSet<Character>());
        dec.put('=', new HashSet<Character>());
        dec.put('I', new HashSet<Character>());
        dec.put('-', new HashSet<Character>());

        inc.get('I').add('L');
        inc.get('I').add('+');
        inc.get('-').add('=');
        inc.get('-').add('+');
        inc.get(' ').add('-');
        inc.get(' ').add('I');

        dec.get('L').add('I');
        dec.get('+').add('I');
        dec.get('+').add('-');
        dec.get('=').add('-');
        dec.get('I').add(' ');
        dec.get('-').add(' ');


        for (int i = 0; i < n; ++i) {
            char realI = sb.charAt(i);
            if (!inc.containsKey(realI))
                continue;
            for (int d = 0; d < n; ++d) {
                char realD = sb.charAt(d);
                if (i == d)
                    continue;


                if (!dec.containsKey(realD))
                    continue;

                for (Character ci : inc.get(realI)) {
                    for (Character cd : dec.get(realD)) {
                        sb.setCharAt(i, ci);
                        sb.setCharAt(d, cd);
                        if (isCorrect(sb)) {
                            answer.add(trim(sb));
                           // return;
                        }
                    }
                }

                sb.setCharAt(d, realD);
            }

            sb.setCharAt(i, realI);

        }

        for (String s1 : answer) {
            out.println(s1);
        }

    }

    private String trim(CharSequence s){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) !=' '){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }


    private boolean isCorrect(StringBuilder s) {

        boolean first = true;
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;
            if (s.charAt(i) == '=') {
                if (!first) return false;
                first = false;
                continue;
            }
            if (first)
                left.append(s.charAt(i));
            else
                right.append(s.charAt(i));
        }
        if (left.length() == 0 || right.length() == 0) return false;
        int a = calculate(left);
        int b = calculate(right);
        return a != Integer.MIN_VALUE && a == b;
    }

    private int calculate(CharSequence s) {
        int n = s.length();
        if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(n - 1) == '-' || s.charAt(n - 1) == '+') {
            return Integer.MIN_VALUE;
        }
        for (int i = 0; i < n - 1; i++) {
            if (
                    (s.charAt(i) == '-' && s.charAt(i + 1) == '-') ||
                            (s.charAt(i) == '-' && s.charAt(i + 1) == '+') ||
                            (s.charAt(i) == '+' && s.charAt(i + 1) == '-') ||
                            (s.charAt(i) == '+' && s.charAt(i + 1) == '+')
                    )
                return Integer.MIN_VALUE;
        }

        int res = 0;
        StringBuilder t = new StringBuilder();
        int sign = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                int q = parseRomanInteger(t);
                if (q == Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
                res += sign * q;
                sign = s.charAt(i) == '+' ? 1 : -1;
                t = new StringBuilder();
                continue;
            }
            t.append(s.charAt(i));
        }
        int q = parseRomanInteger(t);
        if (q == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        res += sign * q;
        return res;
    }

    private int parseRomanInteger(StringBuilder s) {
        String st = s.toString();
        if (!romans.containsKey(st)) return Integer.MIN_VALUE;
        return romans.get(st);
    }
}
