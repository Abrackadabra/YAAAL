package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class ACARVANS {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int max = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            if (max >= t) {
                res++;
                max = t;
            }
        }
        out.println(res);
	}
}
