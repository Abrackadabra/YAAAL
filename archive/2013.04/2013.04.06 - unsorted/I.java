package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.SortedSet;
import java.util.TreeSet;

public class I {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt(), m = in.nextInt();

        int real = Integer.parseInt(in.nextString(), 2);

        SortedSet<Integer> valid = new TreeSet<Integer>();

        for (int i = 0; i < (1 << m); i++) {
            boolean ok = true;

            int a = 0, b = 0;

            for (int j = 0; j < m; j++) {
                if ((i & (1 << (m - j - 1))) > 0) {
                    a++;
                } else {
                    b++;
                }
                if (j != m - 1 && (a == n || b == n)) {
                    ok = false;
                }
                if (j == m - 1 && a < n && b < n) {
                    ok = false;
                }
            }

            if (ok) {
                valid.add(i);
            }
        }

        int already = 0;

        int count = 0;

        for (int i = 0; i < m + 1; i++) {
            /*for (int x : valid) {
                for (int j = m - 1; j >= 0; j--) {
                    if ((x & (1 << j)) > 0) {
                        out.print(1);
                    } else {
                        out.print(0);
                    }
                }
                out.println();
            }
            out.println();*/

            if (valid.size() == 1) {
                out.println(count);
                return;
            }

            boolean b = (real & (1 << (m - i - 1))) > 0;
            SortedSet<Integer> nextSet;
            if (b) {
                nextSet = valid.tailSet(already + (1 << (m - i - 1)));
                already += (1 << (m - i - 1));
            } else {
                nextSet = valid.headSet(already + (1 << (m - i - 1)));
            }
            if (nextSet.size() != valid.size()) {
                count++;
            }
            valid = nextSet;
        }
    }
}
