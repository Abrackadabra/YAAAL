package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Pans {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextDouble();
        }
        double ans = 0.0;
        for (int i = 1; i < n; i++) {
            double diff = Math.abs(a[i] - a[i - 1]);
            if (2 * Math.PI - diff < diff) {
                diff = 2 * Math.PI - diff;
            }
            ans += diff;
        }
        out.println(ans);
	}
}
