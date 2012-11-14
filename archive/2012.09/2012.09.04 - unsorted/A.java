package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class A {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.readLine();
        out.println((s.contains("H") || s.contains("Q") || s.contains("9")) ? "YES" : "NO");
	}
}
