package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.math.*;

public class Hrundy {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        BigInteger[] ans = new BigInteger[1001];
        ans[1] = BigInteger.ONE;
        ans[2] = BigInteger.ONE.add(BigInteger.ONE);
        ans[3] = ans[2].add(ans[2]);
        for (int i = 4; i <= 1000; ++i){
            ans[i] = ans[i - 1].add(ans[i - 2]);
        }
        out.print(ans[n]);
	}
}
