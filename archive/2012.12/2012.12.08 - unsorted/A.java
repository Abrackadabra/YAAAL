package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class A {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();

        int bestDiff = Integer.MAX_VALUE;
        int bestArea = -1;

        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            int a = x - t, b = t - y;
            if (a > 0 && b > 0) {
                int d = Math.abs(a - b);
                if ((d == bestDiff && t > bestArea) || d < bestDiff) {
                    bestDiff = d;
                    bestArea = t;
                }
            }
        }

        if (bestArea == -1) {
            out.println(-1);
        } else {
            out.println(bestArea + " " + (x - bestArea) + " " + (bestArea - y));
        }
	}
}
