package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.Arrays;

public class HORSES {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            minDiff = Math.min(minDiff, Math.abs(a[i] - a[i + 1]));
        }
        out.println(minDiff);
	}
}
