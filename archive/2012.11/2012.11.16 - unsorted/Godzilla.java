package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Godzilla {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();

        HashMap<Integer, int[]> edgesMap = new HashMap<Integer, int[]>();

        for (int i = 0; i < m; i++) {
            edgesMap.put(i, new int[] {in.nextInt() - 1, in.nextInt() - 1});
        }

        int k = in.nextInt();

        ArrayList<int[]> eaten = new ArrayList<int[]>();

        for (int i = 0; i < k; i++) {
            int t = in.nextInt() - 1;

            eaten.add(edgesMap.get(t));

            edgesMap.remove(t);
        }

        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }

        for (Map.Entry<Integer, int[]> e : edgesMap.entrySet()) {
            int[] a = e.getValue();
            edges.get(a[0]).add(a[1]);
            edges.get(a[1]).add(a[0]);
        }

        ArrayList<HashSet<Integer>> dsus = new ArrayList<HashSet<Integer>>();
        int dsuNum = 0;

        int[] vertexDsu = new int[n];
        Arrays.fill(vertexDsu, -1);

        for (int i = 0; i < n; i++) {
            if (vertexDsu[i] != -1) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(i);
            HashSet<Integer> set = new HashSet<Integer>();
            set.add(i);
            int num = dsuNum++;
            vertexDsu[i] = num;

            while (!queue.isEmpty()) {
                int t = queue.poll();
                for (int q : edges.get(t)) {
                    if (vertexDsu[q] != -1) {
                        continue;
                    }
                    vertexDsu[q] = num;
                    queue.add(q);
                }
            }

            dsus.add(set);
        }

        Stack<Integer> results = new Stack<Integer>();

        for (int i = eaten.size() - 1; i >= 0; i--) {
            int[] a = eaten.get(i);

            results.add(dsuNum);

            if (vertexDsu[a[0]] != vertexDsu[a[1]]) {
                if (dsus.get(vertexDsu[a[0]]).size() >= dsus.get(vertexDsu[a[1]]).size()) {
                    for (int j : dsus.get(vertexDsu[a[1]])) {
                        vertexDsu[j] = vertexDsu[a[0]];
                        dsus.get(vertexDsu[a[0]]).add(j);
                    }
                } else {
                    for (int j : dsus.get(vertexDsu[a[0]])) {
                        vertexDsu[j] = vertexDsu[a[1]];
                        dsus.get(vertexDsu[a[1]]).add(j);
                    }
                }
                dsuNum--;
            }
        }

        while (!results.isEmpty()) {
            out.println(results.pop());
        }
    }
}
