package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

public class H {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int sum = 0;
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            double r = AMath.hypot(in.nextInt(), in.nextInt()) - 1e-12;
            int score = AMath.max(0, 10 - (int) r / 20);
            sum += score;
        }
        out.println(sum);
	}
}
