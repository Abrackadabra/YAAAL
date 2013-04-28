package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;
import abrackadabra.util.MiscUtils;

import java.util.*;

public class A {
    int l;
    int[] p;
    int[] degs;
    int[] a;

    Map<Integer, ArrayList<Long>> divisors = new TreeMap<Integer, ArrayList<Long>>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();

        ArrayList<Integer> primes = AMath.getPrimes(100);
        l = primes.size();

        p = new int[l];
        degs = new int[l];
        a = new int[l];
        for (int i = 0; i < l; i++) {
            p[i] = primes.get(i);
            while (n % p[i] == 0) {
                n /= p[i];
                degs[i]++;
            }
        }

        gen(0);

        ArrayList<Long> best = new ArrayList<Long>();
        for (ArrayList<Long> a : divisors.values()) {
            if (a.size() > best.size()) {
                best = a;
            }
        }
        Collections.sort(best);
        out.println(best.size());
        out.printSeparated(best);
    }

    int sum = 0;

    void gen(int i) {
        if (i == l) {
            long x = 1;
            for (int d = 0; d < l; d++) {
                for (int j = 0; j < a[d]; j++) {
                    x *= p[d];
                }
            }
            if (!divisors.containsKey(sum)) {
                divisors.put(sum, new ArrayList<Long>());
            }
            divisors.get(sum).add(x);

            return;
        }
        for (a[i] = 0; a[i] <= degs[i]; a[i]++) {
            sum += a[i];
            gen(i + 1);
            sum -= a[i];
        }
    }
}
