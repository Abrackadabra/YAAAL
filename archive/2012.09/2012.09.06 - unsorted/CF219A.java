package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class CF219A {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int k = in.nextInt();
        String s = in.next();

        int[] chars = new int[26];

        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++)
            if (chars[i] % k != 0) {
                out.println(-1);
                return;
            } else {
                chars[i] /= k;
            }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 26; j++) {
                for (int q = 0; q < chars[j]; q++) {
                    out.print((char) ('a' + j));
                }
            }
        }
	}
}
