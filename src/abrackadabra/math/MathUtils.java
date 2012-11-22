package abrackadabra.math;

import java.math.BigInteger;
import java.util.ArrayList;

public class MathUtils {
    public static int min(int... a) {
        int min = a[0];
        for (int i = 1; i < a.length; i++)
            min = Math.min(min, a[i]);
        return min;
    }

    public static int gcd(int a, int b) {
        if (a == 0 || b == 0) return 1;
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return Math.abs(a);
    }

    public static long gcd(long a, long b) {
        if (a == 0 || b == 0) return 1;
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return Math.abs(a);
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.signum() == 0 || b.signum() == 0) return BigInteger.ONE;
        while (b.signum() != 0) {
            BigInteger t = a.mod(b);
            a = b;
            b = t;
        }
        return a.abs();
    }

    public static double getRandomBetween(double a, double b) {
        if (a > b) {
            double t = a;
            a = b;
            b = t;
        }
        return Math.random() * (b - a) + a;
    }

    public static ArrayList<Integer> getPrimes(int max) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        boolean[] a = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            if (a[i]) continue;
            res.add(i);
            for (int j = 2 * i; j <= max; j += i)
                a[j] = true;
        }
        return res;
    }


}
