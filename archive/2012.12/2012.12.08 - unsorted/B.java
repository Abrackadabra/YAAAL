package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class B {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        long l = in.nextLong();
        for (long a = 1; a <= l; a++) {
            long r = 2 * a * a + l * l - 2 * a * l;
            long q = 2 * (l - a);
            if (q == 0) continue;
            long c = r / q;
            if (c <= 0) continue;
            long b = l - a - c;
            if (b <= 0) continue;
            if (a * a + b * b == c * c) {
                out.println(a + " " + b + " " + c);
                return;
            }
        }
        out.println(-1);
	}
}
