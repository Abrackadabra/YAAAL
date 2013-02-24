package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        Map<Integer, Collection<Integer>> begins = new HashMap<Integer, Collection<Integer>>();
        Map<Integer, Collection<Integer>> ends = new HashMap<Integer, Collection<Integer>>();

        for (int i = -1; i <= m; i++) {
            begins.put(i, new ArrayList<Integer>());
            ends.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < n; i++) {
            int[] a = in.nextIntArray(in.nextInt());

            Arrays.sort(a);

            for (int j = 0; j < a.length; j++) {
                if (j == 0 || a[j - 1] != a[j] - 1) {
                    begins.get(a[j] - 1).add(i);
                }
                if (j == a.length - 1 || a[j + 1] != a[j] + 1) {
                    ends.get(a[j] - 1).add(i);
                }
            }
        }

        ends.get(-1).add(-1);

        Map<Integer, Integer> taken = new HashMap<Integer, Integer>(); // what when

        Set<Integer> notTaken = new HashSet<Integer>();

        int max = -1;

        for (int i = 0; i < m + 1; i++) {
            for (int x : begins.get(i)) {
                notTaken.add(x);
            }

            if (ends.get(i - 1).isEmpty()) continue;

            for (int x : ends.get(i - 1)) {
                if (taken.containsKey(x)) {
                    max = Math.max(i - taken.get(x), max);
                    taken.remove(x);
                }
                notTaken.remove(x);
            }

            for (int x : notTaken) {
                if (!taken.containsKey(x)) {
                    taken.put(x, i);
                }
            }
            notTaken.clear();
        }

        out.println(max);
    }
}
