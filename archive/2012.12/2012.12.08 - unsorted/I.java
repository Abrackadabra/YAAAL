package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class I {
    HashMap<Integer, HashSet<Integer>> edges;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int n, m;
            try {
                n = in.nextInt();
                m = in.nextInt();
            } catch (Exception e) {
                return;
            }

            edges = new HashMap<Integer, HashSet<Integer>>();

            for (int i = 1; i <= n; i++) {
                edges.put(i, new HashSet<Integer>());
                edges.put(-i, new HashSet<Integer>());
            }

            for (int i = 0; i < m; i++) {
                int a = in.nextInt(), b = in.nextInt();
                edges.get(-a).add(b);
                edges.get(-b).add(a);
            }

            boolean ok = !isBad(1);

            for (int i = 2; i <= n && ok; i++) {
                if (isBad(i) && isBad(-i)) {
                    ok = false;
                }
            }

            out.println(ok ? "yes" : "no");
        }
    }

    boolean isBad(int start) {
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(start);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int t = queue.poll();
            for (int q : edges.get(t)) {
                if (!visited.contains(q)) {
                    if (q == -start) {
                        return true;
                    }
                    visited.add(q);
                    queue.add(q);
                }
            }
        }

        return false;
    }
}
