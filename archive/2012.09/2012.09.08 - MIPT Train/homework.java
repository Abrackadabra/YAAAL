package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class homework {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        int[] t = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++)
            s += t[i] = in.nextInt();

        boolean[] bad = new boolean[n];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt();
            bad[a - 1] = true;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!bad[i]) {
                int q = s - t[i];
                if (q < min) min = q;
            }
        }
        out.println(min);
	}
}
