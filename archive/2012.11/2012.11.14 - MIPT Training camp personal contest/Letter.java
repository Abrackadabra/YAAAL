package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class Letter {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), k = in.nextInt();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(in.nextLine());
        while (stringBuilder.length() < n)
            stringBuilder.append(' ');
        String s = stringBuilder.toString();
        int p = n - k;
        for (int i = 0; i < n; i++) {
            out.print(s.charAt(p++ % n));
        }
	}
}
