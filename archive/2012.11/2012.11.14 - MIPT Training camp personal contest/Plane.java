package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Plane {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        boolean bing = true, bong = true;
        for (int i = 0; i < 4; i++) {
            String s = in.nextString();
            if (s.contains("E") || s.contains("F")) {
                bong = false;
            }
            if (s.length() < 3) {
                bing = false;
            }
        }
        if (bing && !bong) {
            out.println("Bing");
            return;
        }
        if (!bing && bong) {
            out.println("Bong");
            return;
        }
        out.println("Ambigious");
	}
}
