package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class F {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt(), l = in.nextInt();

        int res = 0;
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            if (t <= l) {
                res += t;
            } else {
                break;
            }
        }

        out.println(res);
	}
}
