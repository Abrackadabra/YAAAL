package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
        int q = b * c;
        q += (a - 1) * (b + c - 1);
        out.println(q);
	}
}
