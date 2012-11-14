package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class Knownothing {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        long t1 = (long)in.nextInt();
        long t2 = (long)in.nextInt();
        long s1 = (long)in.nextInt();
        long s2 = (long)in.nextInt();
        long s = (long)in.nextInt();

        /*if (s2 > s1) {
            long t = s1;
            s1 = s2;
            s2 = t;
            t = t1;
            t1 = t2;
            t2 = t;
        }  */

        if (s1 >= s) {
            out.printf("%.2f\n",  ((double)s / s1) * t1);
            return;
        }

        if (s1 <= s2) {
            out.println(-1);
            return;
        }

        double v1 = (double) (s1 - s2);
        long i = (long) ((s - s1) / v1);

        for (long j = i - 100; j < i + 100; j++) {
            if (j < 0) continue;

            long a = (s1 - s2) * j;
            if (a + s1 >= s) {
                double ans = (double) j * (t1 + t2) + ((double)s - a) / ((double)s1 / t1);
                out.printf("%.2f\n", ans);
                return;
            }
        }

        v1 = (double) (s1 - s2);
        i = (long) (s / v1);

        for (long j = i - 100; j < i + 100; j++) {
            if (j < 0) continue;

            long a = (s1 - s2) * j;
            if (a + s1 >= s) {
                double ans = (double) j * (t1 + t2) + ((double)s - a) / ((double)s1 / t1);
                out.printf("%.2f\n", ans);
                return;
            }
        }
	}
}
