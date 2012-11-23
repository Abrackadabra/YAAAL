package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Fish {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        out.println((n - 1) / m);
	}
}
