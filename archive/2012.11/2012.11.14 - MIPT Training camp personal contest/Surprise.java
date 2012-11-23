package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Surprise {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        double r = in.nextDouble();

        double alpha = 2 * Math.PI / n / 2;

        double R = 1 / Math.sin(alpha) * r;
        out.printf("%.6f", R);
	}
}
