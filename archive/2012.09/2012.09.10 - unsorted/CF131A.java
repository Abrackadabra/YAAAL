package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class CF131A {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.nextString();
        if (s.length() <= 1 || s.substring(1).equals(s.substring(1).toUpperCase())) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) <= 'Z') {
                    out.print(((char) (s.charAt(i) - 'A' + 'a')));
                } else {
                    out.print(((char) (s.charAt(i) - 'a' + 'A')));
                }
            }
            return;
        }
        out.println(s);
	}
}
