package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class RNA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.nextString();
        out.println(s.replace('T', 'U'));
	}
}
