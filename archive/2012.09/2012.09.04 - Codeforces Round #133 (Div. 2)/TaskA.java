package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
        int q = b * c;
        q += (a - 1) * (b + c - 1);
        out.println(q);
	}
}
