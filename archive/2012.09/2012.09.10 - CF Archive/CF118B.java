package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class CF118B {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();

        int m = n * 2 + 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int dist = Math.abs(i - n) + Math.abs(j - n);
                int d = n - dist;
                if (d < 0)
                    out.print(" ");
                else
                    out.print(d);
                if (d == 0 && j >= n) break;
                out.print(" ");
            }
            out.println();
        }
    }
}
