package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class Fish {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        out.println((n - 1) / m);
	}
}
