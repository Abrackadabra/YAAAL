package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Security {
    int n;
    ArrayList<HashSet<Integer>> graph;
    int[] mt;
    boolean[] used;

    boolean try_kuhn(int v) {
        if (used[v] || forbiddenA[v]) return false;
        used[v] = true;
        for (int to : graph.get(v)) {
            if (forbiddenB[to]) continue;
            if (mt[to] == -1 || try_kuhn(mt[to])) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }

    int kuhn() {
        Arrays.fill(mt, -1);

        for (int v = 0; v < n; ++v) {
            used = new boolean[n];
            try_kuhn(v);
        }

        int res = 0;

        for (int i : mt)
            if (i != -1)
                res++;

        return res;
    }

    boolean[] forbiddenA;
    boolean[] forbiddenB;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        graph = new ArrayList<HashSet<Integer>>();

        n = in.nextInt();

        String a = in.nextString();
        String b = in.nextString();

        int l = a.length() / n;

        for (int i = 0; i < n; i++) {
            HashSet<Integer> q = new HashSet<Integer>();
            for (int j = 0; j < n; j++) {
                boolean ok = true;
                for (int x = 0; x < l; x++) {
                    if (!same(a.charAt(i * l + x), b.charAt(j * l + x))) {
                        ok = false;
                    }
                }

                if (ok) {
                    q.add(j);
                }
            }
            graph.add(q);
        }

        mt = new int[n];

        forbiddenA = new boolean[n];
        forbiddenB = new boolean[n];

        out.printCase(testNumber);

        if (kuhn() != n) {
            out.println("IMPOSSIBLE");
            return;
        }

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            forbiddenA[i] = true;
            int good = -1;
            for (int j : graph.get(i)) {
                if (forbiddenB[j]) continue;

                forbiddenB[j] = true;

                if (kuhn() == n - i - 1) {
                    good = j;
                    break;
                }

                forbiddenB[j] = false;
            }
            res[i] = good;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                out.print(mix(a.charAt(i * l + j), b.charAt(res[i] * l + j)));
            }
        }
        out.println();
    }

    boolean same(char a, char b) {
        return a == b || a == '?' || b == '?';
    }

    char mix(char a, char b) {
        if (a == b && a == '?') return 'a';
        if (a == '?') return b;
        if (b == '?') return a;
        return a;
    }
}
