package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class computers {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int a1 = in.nextInt(), a2 = in.nextInt(), a3 = in.nextInt();
        int b1 = in.nextInt(), b2 = in.nextInt(), b3 = in.nextInt();

        int res = 0;

        int q = Math.min(a1, b1);
        res += q;
        a1 -= q;
        b1 -= q;
        q = Math.min(a2, b2);
        res += q;
        a1 -= q;
        b1 -= q;

        q = Math.min(a1, b3);
        res += q;
        a1 -= q;
        b3 -= q;

        q = Math.min(a2, b3);
        res += q;
        a2 -= q;
        b3 -= q;

        q = Math.min(a3, b1);
        res += q;
        a3 -= q;
        b1 -= q;

        q = Math.min(a3, b2);
        res += q;
        a3 -= q;
        b2 -= q;

        q = Math.min(a3, b3);
        res += q;
        a3 -= q;
        b3 -= q;

        out.println(res);
	}
}
