package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class D {
    public static final int MOD = 10001;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        if (n == 0) {
            return;
        }
        if (n == 1) {
            out.println(0);
            return;
        }

        for (int i = 0; i < MOD - 1; ++i) {
            NavigableSet<Integer> need = new TreeSet<Integer>();
            for (int j = 1; j < n; ++j) {
                int delta = ((a[j] - a[j - 1] * i % MOD * i) % MOD + MOD) % MOD;
                if (i % 73 == 72) {
                    if (delta % 73 != 0) {
                        need.add(-1);
                        need.add(-2);
                        break;
                    }
                    delta /= 73;
                    need.add(delta * pow((i + 1) / 73, 135, 137) % MOD);
                } else if (i % 137 == 136) {
                    if (delta % 137 != 0) {
                        need.add(-1);
                        need.add(-2);
                        break;
                    }
                    delta /= 137;
                    need.add(delta * pow((i + 1) / 137, 71, 73) % MOD);
                } else {

                    need.add(delta * pow((i + 1), 72 * 136 - 1, MOD) % MOD);
                }

            }
            /*
            if (need.isEmpty()) {
                throw new AssertionError();
            }*/
            if (need.size() == 1) {
                int b = need.first();
                for (int j = 0; j < n; ++j) {
                    out.println((a[j] * i + b) % MOD);
                }
                return;
            }
        }
       throw new AssertionError();
    }

    private int pow(int a, int n, int mod) {
        if (n == 0) {
            return 1;
        }
        if ((n & 1) == 1) {
            return pow(a, n - 1, mod) * a % mod;
        }
        return pow(a * a % mod, n / 2, mod);
    }

}
