package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();
        int[] letters = new int[26];
        int n = s.length();

        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }

        double res = 0.0;

        for (int a : letters) {
            int b = n - a;
            double x = 0.0;

            double[] ca = new double[a + 1];
            double c = 1.0;
            for (int i = 0; i <= a; i++) {
                ca[i] = c;
                c *= a - i;
                c /= i + 1;
            }

            double[] cb = new double[b + 1];
            c = 1.0;
            for (int i = 0; i <= b; i++) {
                cb[i] = c;
                c *= b - i;
                c /= i + 1;
            }

            for (int i = 1; i <= a; i++) {
                int cai = i;
                int cbi = a - i;

                if (cai >= 0 && cbi >= 0 && cai <= a && cbi <= b) {
                    x += i * ca[cai] * cb[cbi];
                }
            }

            x /= fac(a + b);
            x *= fac(a);
            x *= fac(b);

            res += x;
        }

        out.println(res);
    }

    double fac(int x) {
        double res = 1.0;
        for (int i = 2; i <= x; i++) {
            res *= i;
        }
        return res;
    }
}
