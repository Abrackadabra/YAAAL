package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.Rational;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class H {
    ArrayList<String> names = new ArrayList<String>();
    Map<String, Integer> name2id = new HashMap<String, Integer>();

    Map<Integer, Integer> parse(String s) {
        Map<Integer, Integer> res = new HashMap<Integer, Integer>();
        for (int i = 0; i < s.length(); ) {
            int j = i;
            if (s.charAt(i) == '(') {
                int balance = 1;
                for (j = j + 1; ; ++j) {
                    if (s.charAt(j) == '(')
                        ++balance;
                    if (s.charAt(j) == ')')
                        --balance;

                    if (balance == 0) {
                        break;
                    }
                }

                Map<Integer, Integer> prev = parse(s.substring(i + 1, j));

                int ns = j + 1;
                j = ns;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    ++j;
                }

                int v = ns == j ? 1 : Integer.parseInt(s.substring(ns, j));
                //System.out.println(prev);
                for (Map.Entry<Integer, Integer> entry : prev.entrySet()) {
                    add(res, entry.getKey(), entry.getValue() * v);
                }

            } else {
                j = i + 1;
                while (j < s.length() && s.charAt(j) <= 'z' && s.charAt(j) >= 'a') {
                    ++j;
                }
                String element = s.substring(i, j);
                int ns = j;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    ++j;
                }

                int v = ns == j ? 1 : Integer.parseInt(s.substring(ns, j));

                add(res, element, v);


            }
            i = j;
        }
        return res;
    }

    private void add(Map<Integer, Integer> res, Integer id, int v) {
        res.put(id, res.containsKey(id) ? res.get(id) + v : v);
    }

    private void add(Map<Integer, Integer> res, String element, int v) {
        int id = 0;
        if (name2id.containsKey(element)) {
            id = name2id.get(element);
        } else {
            int curCnt = names.size();
            name2id.put(element, curCnt);
            names.add(element);
            id = curCnt;
        }

        add(res, id, v);
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        if (n == 0 && m == 0)
            throw new UnknownError();
        ArrayList<Map<Integer, Integer>> p = new ArrayList<Map<Integer, Integer>>();
        for (int i = 0; i < n; ++i) {
            p.add(parse(in.nextString()));
        }

        for (int i = 0; i < m; ++i) {
            p.add(parse(in.nextString()));
        }

        Rational[][] c = new Rational[names.size()][n + m];
        Rational zero = new Rational(0);


        for (int i = 0; i < names.size(); ++i) {
            Arrays.fill(c[i], zero);
        }


        for (int i = 0; i < n; ++i) {

            for (Map.Entry<Integer, Integer> z : p.get(i).entrySet()) {
                c[z.getKey()][i] = new Rational(z.getValue());
            }


        }
        for (int i = n; i < m + n; ++i) {

            for (Map.Entry<Integer, Integer> z : p.get(i).entrySet()) {
                c[z.getKey()][i] = new Rational(-z.getValue());
            }
        }

        //System.out.println(Arrays.deepToString(c));

        n = n + m;


        boolean free[] = new boolean[n];

        Arrays.fill(free, true);


        for (int firstRow = 0; firstRow < n && firstRow < c.length; ++firstRow) {
            boolean good = false;
            for (int i = firstRow; i < c.length; ++i) {

                if (!c[i][firstRow].equals(zero)) {
                    Rational[] tmp = c[i];
                    c[i] = c[firstRow];
                    c[firstRow] = tmp;
                    good = true;
                    break;
                }
            }

            if (!good) {
                free[firstRow] = true;
                continue;
            } else
                free[firstRow] = false;

            for (int i = firstRow + 1; i < c.length; ++i) {
                Rational coeff = c[i][firstRow].divide(c[firstRow][firstRow]);
                c[i][firstRow] = zero;
                for (int j = firstRow + 1; j < n; ++j) {
                    c[i][j] = c[i][j].subtract(c[firstRow][j].multiply(coeff));
                }
            }
        }

        int ans = 0;
        int freeVar = -1;
        for (int i = 0; i < n; ++i) {
            ans += free[i] ? 1 : 0;
            if (free[i])
                freeVar = i;
        }

        out.print("Case " + testNumber + ":");


        if (ans != 1) {
            out.println(" No");
            return;
        }

        Rational[] values = new Rational[n];

        values[freeVar] = new Rational(1);

        //System.out.println(Arrays.deepToString(c));


        for (int i = c.length - 1; i >= 0; --i) {
            boolean good = false;
            int j;
            for (j = 0; j < n; ++j) {
                if (!c[i][j].equals(zero)) {
                    good = true;
                    break;
                }
            }
            if (!good)
                continue;

            // System.out.println(j);

            values[j] = zero;

            for (int k = j + 1; k < n; ++k) {
                //       System.out.println("k = " + k);
                values[j] = values[j].subtract(c[i][k].multiply(values[k]));
            }

            values[j] = values[j].divide(c[i][j]);
        }

        for (Rational value : values) {
            if(value.compareTo(zero) <= 0){
                out.println(" No");
                return;
            }
        }

        BigInteger denominator = BigInteger.ONE;
        for (Rational value : values) {
            denominator = denominator.divide(denominator.gcd(value.denominator)).multiply(value.denominator);
        }

        for (Rational value : values) {
            out.print(" " + value.numerator.multiply(denominator.divide(value.denominator)));
        }
        out.println();


    }
}
