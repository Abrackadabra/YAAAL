package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;

public class J {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int s = in.nextInt();
        int n = in.nextInt();

        int[] c = new int[s + 1];
        int bad = 0;

        boolean[] ok = new boolean[s];
        Arrays.fill(ok, true);

        int[] a = in.nextIntArray(n);

        for (int i = 0; true; i++) {
            int add = i;
            int remove = i - s;
            if (remove >= n) break;

            if (add >= 0 && add < n) {
                c[a[add]]++;
                if (c[a[add]] == 2) bad++;
            }

            if (remove >= 0 && remove < n) {
                c[a[remove]]--;
                if (c[a[remove]] == 1) bad--;
            }

            if (bad != 0) {
                ok[add % s] = false;
            }
        }

        int total = 0;
        for (boolean i : ok) {
            if (i) {
                total++;
            }
        }
        out.println(total);
    }
}
