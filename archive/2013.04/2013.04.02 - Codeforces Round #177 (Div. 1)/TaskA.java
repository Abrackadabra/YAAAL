package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        char[] a = new char[n];

        for (int i = k - 1; i >= 2; i--) {
            int pos = n - 1 - (k - 1 - i);
            if (pos < 0 || pos >= n) break;
            a[pos] = (char) ('a' + i);
        }

        for (int i = 0; i < n; i++) {
            if (a[i] != 0) break;

            if (i % 2 == 0) {
                a[i] = 'a';
            } else {
                a[i] = 'b';
            }
        }

        Set<Character> set = new HashSet<Character>();
        for (char c : a) {
            set.add(c);
        }

        if (set.size() != k) {
            out.println(-1);
            return;
        }

        out.println(new String(a));
    }
}
