package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int d = in.nextInt(), n = in.nextInt(), m = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < n; j++) {
                a[j] += in.nextInt();
            }
        }

        for (int i = 1; i < n; i++) {
            a[i] += a[i - 1];
        }

        long max = -1;
        int maxi = -1;

        for (int i = 0; i < n; i++) {
            if (i + m - 1 >= n) break;

            long x = a[i + m - 1];

            if (i > 0)
                x -= a[i - 1];

            if (x > max) {
                max = x;
                maxi = i + 1;
            }
        }

        out.println(maxi + " " + max);
    }
}
