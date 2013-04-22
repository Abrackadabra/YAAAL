package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class J {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] d = in.nextIntArray(n);

        long startTime = System.nanoTime();

        int max = -1;

        for (int i = m; i >= 0; i--) {
            if (System.nanoTime() - startTime > 1.8e9) {
                break;
            }

            Set<Integer> set = new HashSet<Integer>();

            for (int x : d) {
                set.add(i / x);
                if (i % x != 0) {
                    set.add(i / x + 1);
                }
            }

            if (set.size() > max) {
                max = set.size();
            }
        }

        out.println(max);
    }
}
