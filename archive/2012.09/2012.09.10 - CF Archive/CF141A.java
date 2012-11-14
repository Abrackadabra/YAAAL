package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class CF141A {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        String a = in.nextString();
        String b = in.nextString();
        String c = in.nextString();

        char[] x = a.toCharArray();
        char[] y = b.toCharArray();

        char[] t = new char[x.length + y.length];
        for (int i = 0; i < t.length; i++) {
            if (i < x.length) {
                t[i] = x[i];
            } else {
                t[i] = y[i - x.length];
            }
        }

        char[] z = c.toCharArray();

        Arrays.sort(t);
        Arrays.sort(z);
        out.println(Arrays.equals(t, z) ? "YES" : "NO");
	}
}
