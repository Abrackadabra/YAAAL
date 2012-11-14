package chelper;

import abrackadabra.Scanner;
import abrackadabra.math.MathUtils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Phi {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        ArrayList<Integer> primes = MathUtils.getPrimes(2000100);
        long a = in.nextLong(), b = in.nextLong();
        long res = 0;
        if (a == 1) {
            res += 1;
            a++;
        }
        if (b - a == -1) {
            out.println(1);
            return;
        }
        long[] val = new long[(int) (b - a + 1)];
        long[] back = new long[(int) (b - a + 1)];
        for (long i = a; i <= b; i++) {
            val[(int) (i - a)] = i;
            back[(int) (i - a)] = i;
        }
        for (int p : primes) {
            double m = 1 - 1.0 / p;
            for (long q = (a + p - 1) / p * p; q <= b; q += p) {
                val[(int) (q - a)] /= p;
                val[(int) (q - a)] *= p - 1;
                while (back[(int) (q - a)] % p == 0) {
                    back[(int) (q - a)] /= p;
                }
            }
        }
        for (long i = a; i <= b; i++) {
            if (back[(int) (i - a)] != 1) {
                val[(int) (i - a)] /= back[(int) (i - a)];
                val[(int) (i - a)] *= back[(int) (i - a)] - 1;
            }
            res += val[(int) (i - a)];
        }
        out.println(res);
    }
}
