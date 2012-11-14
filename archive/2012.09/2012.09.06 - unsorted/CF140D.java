package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class CF140D {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int leftTime = 6 * 60 - 10;
        int rightTime = 6 * 60;
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);

        int solved = 0, penalty = 0;
        int t = -leftTime;
        for (int i = 0; i < n; i++) {
            t += a[i];
            if (t > rightTime) break;
            solved++;
            if (t > 0) {
                penalty += t;
            }
        }
        out.println(solved + " " + penalty);
	}
}
