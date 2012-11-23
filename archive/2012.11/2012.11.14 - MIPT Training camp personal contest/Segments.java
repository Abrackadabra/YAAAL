package chelper;

import abrackadabra.io.InputReader;
import java.io.PrintWriter;

public class Segments {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            long l = in.nextLong(), a = in.nextLong(), b = in.nextLong();
            long L = l + a, R = l + b;
            long d = R - L;
            out.println((L + d / 2) + " " + (d / 2));
        }
	}
}
