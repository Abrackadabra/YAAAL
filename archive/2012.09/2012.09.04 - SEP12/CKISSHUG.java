package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class CKISSHUG {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        BigInteger res = BigInteger.valueOf(2);
        BigInteger q = BigInteger.valueOf(n / 2);
        res = res.add(q.multiply(BigInteger.valueOf(3).add(q)).divide(BigInteger.valueOf(2)));
        q = BigInteger.valueOf((n - 1) / 2);
        res = res.add(q.multiply(BigInteger.valueOf(3).add(q)).divide(BigInteger.valueOf(2)));
        out.println(res.mod(BigInteger.valueOf(1000000007)));
	}
}
