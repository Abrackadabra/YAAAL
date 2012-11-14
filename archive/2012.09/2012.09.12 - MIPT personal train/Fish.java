package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class Fish {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        out.println((n - 1) / m);
	}
}
