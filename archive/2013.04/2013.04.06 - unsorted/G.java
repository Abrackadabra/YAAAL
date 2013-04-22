package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class G {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        SortedSet<Integer> set = new TreeSet<Integer>();

        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();

            if (!freq.containsKey(a[i])) {
                freq.put(a[i], 0);
            }
            freq.put(a[i], freq.get(a[i]) + 1);

            set.add(a[i]);
        }

        int min = Integer.MAX_VALUE;
        int ma = -1;
        int mb = -1;

        for (int i = 0; i < n; i++) {
            if (freq.get(a[i]) == 1) {
                set.remove(a[i]);
            }

            SortedSet<Integer> curSet = set;

            int already = 0;
            int diff = 0;

            for (int bit = 30; bit >= 0; bit--) {
                SortedSet<Integer> left = curSet.headSet(already + (1 << bit));
                SortedSet<Integer> right = curSet.tailSet(already + (1 << bit));

                boolean b = (a[i] & (1 << bit)) > 0;

                if (b) {
                    if (!right.isEmpty()) {
                        curSet = right;
                        already += (1 << bit);
                    } else {
                        curSet = left;
                        diff += (1 << bit);
                    }
                } else {
                    if (!left.isEmpty()) {
                        curSet = left;
                    } else {
                        curSet = right;
                        diff += (1 << bit);
                        already += (1 << bit);
                    }
                }
            }

            if (diff < min) {
                min = diff;
                ma = a[i];
                mb = already;
            }

            set.add(a[i]);
        }

        int ai = -1;
        int bi = -1;

        for (int i = 0; i < n; i++) {
            if (a[i] == ma && ai == -1) {
                ai = i;
                continue;
            }
            if (a[i] == mb) {
                bi = i;
            }
        }

        out.println((ai + 1) + " " + (bi + 1));
    }
}
