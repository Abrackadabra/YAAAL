package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt() - 1;
        int[] a = in.nextIntArray(n);

        for (int i = k; i < n; i++) {
            if (a[i] != a[k]) {
                out.println(-1);
                return;
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            if (a[i] != a[k]) {
                out.println(i + 1);
                return;
            }
        }

        out.println(0);
	}
}
