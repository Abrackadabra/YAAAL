package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.FastFourierTransform;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class D {
    final int MOD = 86400;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.nextString();
            a[i] =
                    (s.charAt(0) - '0') * 10 * 60 * 60 +
                            (s.charAt(1) - '0') * 60 * 60 +
                            (s.charAt(3) - '0') * 10 * 60 +
                            (s.charAt(4) - '0') * 60 +
                            (s.charAt(6) - '0') * 10 +
                            (s.charAt(7) - '0');

        }

        int[] b = in.nextIntArray(m);

        boolean[] ok = new boolean[MOD];

        long[] x = new long[MOD];
        for (int i : a) {
            x[i] = 1;
        }
        long[] y = new long[MOD];
        for (int i : a) {
            y[(MOD - i) % MOD] = 1;
        }

        long[] z = FastFourierTransform.multiply(x, y);
        for (int i = 0; i < z.length; i++) {
            if (z[i] != 0) {
                ok[i % MOD] = true;
            }
        }

        for (int i : b) {
            out.printYesNo(ok[i], true);
        }
    }
}
