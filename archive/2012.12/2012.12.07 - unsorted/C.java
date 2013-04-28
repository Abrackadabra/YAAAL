package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();

        int[] a = new int[n];
        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + in.nextInt();
        }

        int best = -1;
        int bestL = -1;
        int bestR = -1;

        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n - 1 && a[r + 1] - a[l] <= t) {
                r++;
            }

            int c = r - l + 1;
            if (c > best) {
                best = c;
                bestL = l + 1;
                bestR = r + 1;
            }
        }

        out.print(best + "\n" + bestL + " " + bestR);
    }
}
