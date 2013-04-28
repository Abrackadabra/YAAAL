package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;
import abrackadabra.math.Rational;

import java.math.BigInteger;
import java.util.Arrays;

public class H {
    Rational[] weights;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = 0;

        if (n == 0) {
            throw new UnknownError();
        }

        Arm[] arms = new Arm[n];

        for (int i = 0; i < n; i++) {
            arms[i] = new Arm();
        }

        for (int i = 0; i < n; i++) {
            arms[i].lenL = in.nextInt();
            arms[i].lenR = in.nextInt();
            boolean armL = in.nextString().charAt(0) == 'A';
            boolean armR = in.nextString().charAt(0) == 'A';

            if (armL) {
                arms[i].armL = arms[in.nextInt() - 1];
            } else {
                arms[i].armL = new Arm();
                arms[i].armL.isWeight = true;
                arms[i].armL.weightNum = in.nextInt() - 1;
                m++;
            }
            arms[i].armL.parent = arms[i];

            if (armR) {
                arms[i].armR = arms[in.nextInt() - 1];
            } else {
                arms[i].armR = new Arm();
                arms[i].armR.isWeight = true;
                arms[i].armR.weightNum = in.nextInt() - 1;
                m++;
            }
            arms[i].armR.parent = arms[i];
        }

        weights = new Rational[m];
        int start = in.nextInt() - 1;

        int min = in.nextInt();

        weights[start] = new Rational(1);

        sum = new Rational(0);

        for (Arm arm : arms) {
            if (arm.armL.isWeight && arm.armL.weightNum == start) {
                arm.armL.calc(true);
            }
            if (arm.armR.isWeight && arm.armR.weightNum == start) {
                arm.armR.calc(false);
            }
        }

        BigInteger minDenominator = BigInteger.ONE;

        for (Rational Rational : weights) {
            minDenominator = lcm(minDenominator, Rational.getDenominator());
        }

        /*out.println(Arrays.toString(weights));
        out.println(minDenominator);
        out.println(sum);*/

        BigInteger x = (minDenominator.add(BigInteger.valueOf(min - 1))).divide(minDenominator).multiply(minDenominator);

        out.println("Case " + testNumber + ": " + sum.multiply(new Rational(x)).getNumerator());
    }

    Rational sum;

    BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(a.gcd(b)).multiply(b);// / AMath.gcd(a, b);
    }

    class Arm {
        boolean isWeight = false;
        int weightNum;
        int lenL, lenR;
        Arm armL, armR;
        Arm parent;
        Rational val = null;

        void calc(boolean left) {
            val = new Rational(1);

            if (parent != null) {
                parent.up(val, left);
            }
        }

        void up(Rational Rational, boolean left) {
            if (left) {
                Rational r = Rational.multiply(new Rational(lenL)).divide(new Rational(lenR));
                val = Rational.add(r);
                armR.down(r);
            } else {
                Rational r = Rational.multiply(new Rational(lenR)).divide(new Rational(lenL));
                val = Rational.add(r);
                armL.down(r);
            }

            if (parent != null) {
                parent.up(val, parent.armL == this);
            } else {
                sum = val;
            }
        }

        void down(Rational total) {
            val = total;

            if (isWeight) {
                weights[weightNum] = val;
                return;
            }

            armL.down(total.multiply(new Rational(lenR, lenL + lenR)));
            armR.down(total.multiply(new Rational(lenL, lenL + lenR)));
        }
    }
}
