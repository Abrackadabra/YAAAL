package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class B {
    List<Collection<Integer>> edges = new ArrayList<Collection<Integer>>();

    Set<Integer> cutPoints = new HashSet<Integer>();

    boolean[] visited;

    int n;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        visited = new boolean[n];
        tin = new int[n];
        fup = new int[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsFindCutPoints(i, -1);
            }
        }

        Arrays.fill(visited, false);

        weights = new int[n];
        for (int i = 0; i < n; i++) {
            newEdges.add(new HashSet<Integer>());
            bridges.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < n; i++) {
            dfsBuild(i, -1);
        }

        Arrays.fill(visited, false);

        for (int i = 0; i < n; i++) {
            if (!visited[i] && weights[i] > 0) {
                dfsBridges(i, -1);
            }
        }

        for (int i = 0; i < n; i++) {
            newEdges.get(i).removeAll(bridges.get(i));
        }

        Arrays.fill(visited, false);
        visited2 = new boolean[n];
        counted = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i] && weights[i] > 0) {
                total = dfsTotal(i);
                dfsBridges(i);
                bestV = -1;
                bestRes = Long.MAX_VALUE;
                count(i);
                if (bestV != -1) {
                    answer.add(bestV);
                }
            }
        }

        out.println(answer.size());
        for (Integer i : answer) {
            out.print((i + 1) + " ");
        }
    }

    void count(int v) {
        if (counted[v]) {
            return;
        }
        counted[v] = true;
        long res = 0;
        int left = total - 1;
        boolean hasLeft = false;
        for (Integer to : newEdges.get(v)) {
            hasLeft = true;
            count(to);
        }
        for (Integer to : bridges.get(v)) {
            count(to);
            int c = bridgeCount.get(v * n + to);
            res += (long) c * (c - 1) / 2;
            left -= c;
        }
        if (hasLeft) {
            res += (long) left * (left - 1) / 2;
        }
        if (cutPoints.contains(v) && (res < bestRes || (res == bestRes && v < bestV))) {
            bestRes = res;
            bestV = v;
        }
    }

    int dfsBridges(int v) {
        if (visited2[v]) {
            return 0;
        }
        visited2[v] = true;
        int res = 0;
        for (Integer to : newEdges.get(v)) {
            if (visited2[to]) {
                continue;
            }
            res += dfsBridges(to);
        }
        for (Integer to : bridges.get(v)) {
            if (visited2[to]) {
                continue;
            }
            int val = dfsBridges(to);
            bridgeCount.put(v * n + to, val);
            bridgeCount.put(to * n + v, total - val);
            res += val;
        }
        if (weights[v] > 0) {
            res += weights[v];
        } else {
            res++;
        }
        return res;
    }

    boolean[] counted, visited2;
    int total;
    int bestV;

    long bestRes;

    int dfsTotal(int v) {
        if (visited[v]) {
            return 0;
        }
        visited[v] = true;
        int res = 0;
        for (Integer to : newEdges.get(v)) {
            if (visited[to]) {
                continue;
            }
            res += dfsTotal(to);
        }
        for (Integer to : bridges.get(v)) {
            if (visited[to]) {
                continue;
            }
            res += dfsTotal(to);
        }
        if (weights[v] > 0) {
            res += weights[v];
        } else {
            res++;
        }
        return res;
    }

    Map<Integer, Integer> bridgeCount = new HashMap<Integer, Integer>();

    SortedSet<Integer> answer = new TreeSet<Integer>();

    int[] tin, fup;
    int timer = 0;

    void dfsFindCutPoints(int v, int p) {
        visited[v] = true;
        tin[v] = fup[v] = timer++;
        int children = 0;
        for (Integer to : edges.get(v)) {
            if (to == p) {
                continue;
            }
            if (visited[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfsFindCutPoints(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] >= tin[v] && p != -1) {
                    cutPoints.add(v);
                }
                children++;
            }
        }
        if (p == -1 && children > 1) {
            cutPoints.add(v);
        }
    }

    int[] weights;
    List<Collection<Integer>> newEdges = new ArrayList<Collection<Integer>>();
    List<Collection<Integer>> bridges = new ArrayList<Collection<Integer>>();

    void dfsBuild(int v, int p) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;

        if (cutPoints.contains(v)) {
            for (Integer to : edges.get(v)) {
                if (cutPoints.contains(to)) {
                    newEdges.get(v).add(to);
                }
                dfsBuild(to, -1);
            }
        } else {
            if (p == -1) {
                p = v;
            }
            weights[p]++;

            for (Integer to : edges.get(v)) {
                if (cutPoints.contains(to)) {
                    newEdges.get(to).add(p);
                    newEdges.get(p).add(to);
                    dfsBuild(to, -1);
                } else {
                    dfsBuild(to, p);
                }
            }
        }
    }

    void dfsBridges(int v, int p) {
        visited[v] = true;
        tin[v] = fup[v] = timer++;
        for (Integer to : newEdges.get(v)) {
            if (to == p) {
                continue;
            }
            if (visited[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfsBridges(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    bridges.get(v).add(to);
                    bridges.get(to).add(v);
                }
            }
        }
    }
}
