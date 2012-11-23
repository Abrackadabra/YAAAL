package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class sorting {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int M = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        int[] max = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) max[i] = a[i];
            else {
                max[i] = Math.max(max[i - 1], a[i]);
            }
        }

        for (int i = 1; i < n; i++) {
            if (max[i - 1] > a[i]) {
                if (max[i - 1] + a[i] > M) {
                    out.println("No");
                    return;
                }
            }
        }

        out.println("Yes");
	}
}
