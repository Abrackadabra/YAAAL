package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.math.*;

public class E {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        BigInteger[] F = new BigInteger[10005];
        F[0] = BigInteger.valueOf(1);
        F[1] = BigInteger.valueOf(2);
        for (int i = 2; i <= 10000; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }

        while (true) {
            String s = in.nextString();
            if (s == null) break;
            int n = Integer.parseInt(s);
            BigInteger a = F[n - 1].add(F[n - 3]);
            out.println(a);
        }
	}
}
