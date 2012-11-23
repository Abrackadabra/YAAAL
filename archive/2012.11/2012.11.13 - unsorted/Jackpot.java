package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Jackpot {
    HashSet<Integer>[] dsus;

    HashMap<Integer, Integer> colorDsu;

    int[] vertexDsu;
    int[] dsuColor;
    int   n, m;

    public void solve(int testNumber, Reader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < n + 5; i++) {
            edges.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        dsus = new HashSet[n + 10];
        int dsuNumber = 0;

        dsuColor = new int[n + 10];
        Arrays.fill(dsuColor, Integer.MAX_VALUE);

        colorDsu = new HashMap<Integer, Integer>();

        vertexDsu = new int[n + 10];
        Arrays.fill(vertexDsu, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            HashSet<Integer> childrenDsus = new HashSet<Integer>();

            for (int j : edges.get(i)) {
                if (vertexDsu[j] == Integer.MAX_VALUE) {
                    continue;
                }
                childrenDsus.add(vertexDsu[j]);
            }

            HashSet<Integer> a = new HashSet<Integer>();
            a.add(i);
            dsus[dsuNumber] = a;
            dsuColor[dsuNumber] = i;
            colorDsu.put(i, dsuNumber);
            vertexDsu[i] = dsuNumber;
            dsuNumber++;

            if (childrenDsus.size() == 0) {
                continue;
            }

            HashSet<Integer> childrenColors = new HashSet<Integer>();

            int minColor = Integer.MAX_VALUE;

            for (int d : childrenDsus) {
                int c = dsuColor[d];
                childrenColors.add(c);
                if (Math.abs(c) < Math.abs(minColor)) {
                    minColor = c;
                }
            }

            boolean ok = true;

            int minOur = Integer.MAX_VALUE, maxEnemy = -1;

            HashSet<Integer> enemies = new HashSet<Integer>();

            for (int c : childrenColors) {
                if (childrenColors.contains(-c)) {
                    ok = false;
                }
                if (Math.abs(c % 2) == i % 2) {
                    if (Math.abs(c) < minOur) {
                        minOur = c;
                    }
                } else {
                    if (Math.abs(c) > maxEnemy) {
                        maxEnemy = c;
                    }
                    enemies.add(c);
                }
            }

            if (Math.abs(minOur) < Math.abs(maxEnemy) || enemies.size() > 1) {
                ok = false;
            }

            if (!ok) {
                out.print(i % 2 == 0 ? "Alice" : "Bob");
                out.println(" " + i);
                return;
            }

            HashSet<Integer> thisColorDsus = new HashSet<Integer>();
            HashSet<Integer> anotherColorDsus = new HashSet<Integer>();
            anotherColorDsus.add(vertexDsu[i]);

            for (int c : childrenColors) {
                if (colorDsu.containsKey(c)) {
                    thisColorDsus.add(colorDsu.get(c));
                }
                if (colorDsu.containsKey(-c)) {
                    anotherColorDsus.add(colorDsu.get(-c));
                }
            }

            if (thisColorDsus.size() > 0) {
                int newDsu = merge(thisColorDsus);
                dsuColor[newDsu] = minColor;
                colorDsu.put(minColor, newDsu);
            }

            if (anotherColorDsus.size() > 0) {
                int newDsu = merge(anotherColorDsus);
                dsuColor[newDsu] = -minColor;
                colorDsu.put(-minColor, newDsu);
            }
        }

        out.println("Tie");
    }

    int merge(HashSet<Integer> a) {
        int best = -1;
        for (int d : a) {
            if (best == -1 || dsus[d].size() > dsus[best].size()) {
                best = d;
            }
        }
        for (int d : a) {
            if (d == best) {
                continue;
            }
            for (int q : dsus[d]) {
                vertexDsu[q] = best;
                dsus[best].add(q);
            }
            dsus[d] = null;
        }
        return best;
    }
}
