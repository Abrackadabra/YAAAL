package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.math.BigInteger;

public class A {
    boolean normal;

    OutputWriter out;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (this.out == null) {
            try {
                this.out = new OutputWriter("A-large.out");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out = this.out;

        BigInteger r = new BigInteger(in.nextString());
        BigInteger s = new BigInteger(in.nextString());

        normal = r.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE);

        BigInteger start = r.divide(BigInteger.valueOf(2));

        BigInteger left = BigInteger.ZERO;
        BigInteger right = BigInteger.TEN.pow(100);

        while (right.subtract(BigInteger.ONE).compareTo(left) > 0) {
            BigInteger m = left.add(right).divide(BigInteger.valueOf(2));

            if (s.compareTo(calc(m, start)) >= 0) {
                left = m;
            } else {
                right = m;
            }
        }

        out.printf("Case #%d: ", testNumber);
        out.println(left);

        out.flush();
    }

    BigInteger calc(BigInteger n, BigInteger start) {
        return f(n.add(start)).subtract(f(start));
    }

    BigInteger f(BigInteger n) {
        if (normal) {
            return even(n).subtract(odd(n));
        } else {
            return odd(n).subtract(even(n.subtract(BigInteger.ONE)));
        }
    }

    BigInteger odd(BigInteger n) {
        return n.pow(3).multiply(BigInteger.valueOf(4)).subtract(n).divide(BigInteger.valueOf(3));
    }

    BigInteger even(BigInteger n) {
        return n.multiply(n.add(BigInteger.ONE)).multiply(n.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE)).multiply(BigInteger.valueOf(2)).divide(BigInteger.valueOf(3));
    }
}
