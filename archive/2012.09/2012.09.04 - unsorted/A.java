package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class A {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.nextLine();
        out.println((s.contains("H") || s.contains("Q") || s.contains("9")) ? "YES" : "NO");
	}
}
