package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class Numbers {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong(), m = in.nextLong();

        long ans = 0;
        int c = 0;
        long q = m;

        if (n > 100000000 && m == 2) {
            long t = n / m;
            q = (t + 1) * m;

            long x = (2 * t - 1) / 2;
            ans += x * (x + 1) / 2;

            long z = q;
            for (; true; q += m) {
                t = calc(q, n);
                if (Math.abs(q - z) > 100) {
                    ans += (t + 1) * t / 2;
                    q = 100000000000L;
                    break;
                }
                ans += t;
                if (t == 0) c++;
                if (c == 3) break;
            }
        }

        if (n > 100000000 && m == 3) {
            long t = n / m;
            q = (t + 1) * m;

            if ((3 * t - 1) % m == 2) {
                ans += (3 * t - 1) / 2;
                t--;
            }

            long x = (3 * t - 1) / 2;
            ans += t / 2 * (x + 2) / 2;

            x = (3 * t - 1 - 3) / 2;
            ans += t / 2 * (x + 1) / 2;
        }

        if (n > 100000000 && m == 4) {
            long t = n / m;
            //q = (t + 1) * m;

            long x = (2 * t - 1) / 2;
            //ans += x * (x + 1) / 2;
        }

        for (; true; q += m) {
            long t = calc(q, n);
            ans += t;
            if (t == 0) c++;
            if (c == 3) break;
        }
        out.println(ans);
	}

    long calc(long q, long n) {
        if (q - 1 <= n && q - 1 >= 1) {
            return (q - 1) / 2;
        }
        if (q > n + 1 && q - n <= n) {
            return (2 * n - q + 1) / 2;
        }
        return 0;
    }
}
