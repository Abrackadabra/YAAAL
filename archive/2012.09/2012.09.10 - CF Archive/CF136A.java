package chelper;

import abrackadabra.ArrayUtils;
import abrackadabra.Scanner;
import java.io.PrintWriter;

public class CF136A {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            b[a[i] - 1] = i + 1;
        }

        out.println(ArrayUtils.toSimpleString(b));
	}
}
