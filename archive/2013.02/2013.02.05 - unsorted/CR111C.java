package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;
import java.util.TreeMap;

public class CR111C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long k = in.nextLong() - 1;

        int[] a = in.nextIntArray(n);

        Arrays.sort(a);

        int m = 0;
        int prev = Integer.MIN_VALUE;

        int[] b = new int[n];
        int[] c = new int[n];

        for (int i : a) {
            if (i == prev) {
                b[m - 1]++;
            } else {
                c[m] = prev = i;
                b[m++]++;
            }
        }

        for (int i = 0; i < m; i++) {
            long q = (long) n * b[i];
            if (k >= q) {
                k -= q;
                continue;
            }

            for (int j = 0; j < m; j++) {
                q = (long) b[i] * b[j];
                if (k >= q) {
                    k -= q;
                } else {
                    out.println(c[i] + " " + c[j]);
                    return;
                }
            }
        }
    }
}
