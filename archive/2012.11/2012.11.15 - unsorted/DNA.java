package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class DNA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.nextString();
        int a = 0, c = 0, g = 0, t = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A')
                a++;
            if (s.charAt(i) == 'C')
                c++;
            if (s.charAt(i) == 'G')
                g++;
            if (s.charAt(i) == 'T')
                t++;
        }
        out.println(a + " " + c + " " + g + " " + t);
	}
}
