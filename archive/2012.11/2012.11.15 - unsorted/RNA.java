package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class RNA {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextString();
        out.println(s.replace('T', 'U'));
	}
}
