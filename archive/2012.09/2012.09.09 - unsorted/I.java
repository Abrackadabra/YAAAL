package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class I {
    ArrayList<Long> getPrimes(int upto) {
        boolean[] a = new boolean[upto + 1];
        ArrayList<Long> res = new ArrayList<Long>();
        for (int i = 2; i <= upto; i++) {
            if (!a[i]) {
                res.add(i * 1L);
                for (int j = 2 * i; j <= upto; j += i)
                    a[j] = true;
            }
        }
        return res;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n = in.nextLong();

        ArrayList<Long> primes = getPrimes(10000000);

        ArrayList<Long> pp = new ArrayList<Long>();

        for (int i = 0; i < primes.size(); i++)
            if (n % primes.get(i) == 0)
                pp.add(primes.get(i));

        primes = pp;

        ArrayList<Integer> degs = new ArrayList<Integer>();

        for (int i = 0; i < primes.size(); i++) {
            int c = 0;
            while (n % primes.get(i) == 0) {
                n /= primes.get(i);
                c++;
            }
            degs.add(c);
        }

        if (BigInteger.valueOf(n).isProbablePrime(100)) {
            primes.add(1L * n);
            degs.add(1);
        }

        ArrayList<Long> divisors = new ArrayList<Long>();
        divisors.add(1L);
        for (int i = 0; i < primes.size(); i++) {
            ArrayList<Long> thisDegs = new ArrayList<Long>();
            long q = 1;
            for (int j = 0; j <= degs.get(i); j++) {
                thisDegs.add(q);
                q *= primes.get(i);
            }
            ArrayList<Long> newDivisors = new ArrayList<Long>();
            for (Long a : divisors) {
                for (Long b : thisDegs) {
                    newDivisors.add(a * b);
                }
            }

            divisors = newDivisors;
        }

        Collections.sort(divisors);

        BigInteger k = BigInteger.ZERO;
        for (Long d : divisors) {
            if (BigInteger.valueOf(d).compareTo(k.add(BigInteger.ONE)) > 0) {
                out.println(k.add(BigInteger.ONE));
                return;
            } else {
                k = k.add(BigInteger.valueOf(d));
            }
        }
        out.println(k.add(BigInteger.ONE));
    }
}
