package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class RNA {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        String s = in.nextString();
        out.println(s.replace('T', 'U'));
	}
}
