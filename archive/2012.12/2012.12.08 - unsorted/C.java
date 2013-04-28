package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();

        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> ways =
                new HashMap<Integer, HashMap<Integer, HashSet<Integer>>>();

        for (int i = 0; i < k; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1, t = in.nextInt() - 1;

            if (!ways.containsKey(a)) {
                ways.put(a, new HashMap<Integer, HashSet<Integer>>());
            }
            if (!ways.get(a).containsKey(t)) {
                ways.get(a).put(t, new HashSet<Integer>());
            }
            ways.get(a).get(t).add(b);

            if (!ways.containsKey(b)) {
                ways.put(b, new HashMap<Integer, HashSet<Integer>>());
            }
            if (!ways.get(b).containsKey(t)) {
                ways.get(b).put(t, new HashSet<Integer>());
            }
            ways.get(b).get(t).add(a);
        }

        HashSet<Integer>[] roadsToReach = new HashSet[n];
        int[] changes = new int[n];

        //from, road
        HashMap<Integer, Integer>[] prev = new HashMap[n];

        for (int i = 0; i < n; i++) {
            roadsToReach[i] = new HashSet<Integer>();
            changes[i] = n + 2;
            prev[i] = new HashMap<Integer, Integer>();
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        changes[0] = 0;

        while (true) {
            //go

            HashSet<Integer> newQueue = new HashSet<Integer>();
            while (!queue.isEmpty()) {
                int v = queue.poll();

                if (!ways.containsKey(v)) {
                    continue;
                }

                for (Map.Entry<Integer, HashSet<Integer>> e : ways.get(v).entrySet()) {
                    int road = e.getKey();
                    if (roadsToReach[road].contains(road)) {
                        continue;
                    }
                    for (int q : e.getValue()) {
                        if (changes[q] > changes[v] + 1) {
                            changes[q] = changes[v] + 1;
                            newQueue.add(q);
                            roadsToReach[q] = new HashSet<Integer>();

                            prev[q] = new HashMap<Integer, Integer>();
                        }
                        if (changes[q] == changes[v] + 1) {
                            roadsToReach[q].add(road);
                            if (!prev[q].containsKey(road)) {
                                prev[q].put(road, v);
                            }
                        }
                    }
                }
            }
            for (int q : newQueue) {
                queue.add(q);
            }

            //relax

            while (!queue.isEmpty()) {
                int v = queue.poll();

                if (!ways.containsKey(v)) {
                    continue;
                }

                for (int road : roadsToReach[v]) {
                    if (!ways.get(v).containsKey(road)) {
                        continue;
                    }
                    for (int q : ways.get(v).get(road)) {
                        if (changes[q] > changes[v]) {
                            changes[q] = changes[v];
                            roadsToReach[q] = new HashSet<Integer>();

                            prev[q] = new HashMap<Integer, Integer>();
                        }
                        if (changes[q] == changes[v]) {
                            roadsToReach[q].add(road);
                            if (!newQueue.contains(q)) {
                                queue.add(q);
                                newQueue.add(q);
                            }
                            roadsToReach[q].add(road);
                            if (!prev[q].containsKey(road)) {
                                prev[q].put(road, v);
                            }
                        }
                    }
                }
            }
            for (int q : newQueue) {
                queue.add(q);
            }
            if (queue.isEmpty()) {
                break;
            }
        }

        changes[0] = 1;

        Stack<String> path = new Stack<String>();
        int t = n - 1;
        int prevRoad = -1;

        for (Map.Entry<Integer, HashSet<Integer>> e : ways.get(t).entrySet()) {
            for (int q : e.getValue()) {
                if (changes[q] == changes[t]) {
                    prevRoad = e.getKey();
                }
            }
        }

        while (t != 0) {
            int next = -1, nextRoad = -1;
            for (Map.Entry<Integer, HashSet<Integer>> e : ways.get(t).entrySet()) {
                for (int q : e.getValue()) {
                    if (changes[q] < changes[t]) {
                        next = q;
                        nextRoad = e.getKey();
                    }
                }
            }

            if (next != -1) {
                path.add((next + 1) + ":" + (nextRoad + 1));
                prevRoad = nextRoad;
                t = next;
                continue;
            }

            if (!prev[t].containsKey(prevRoad))
            for (int road : prev[t].keySet()) {
                if (changes[t] == changes[prev[t].get(road)]) {
                    prevRoad = road;
                    break;
                }
            }

            path.add((prev[t].get(prevRoad) + 1) + ":" + (prevRoad + 1));

            t = prev[t].get(prevRoad);
        }
        out.println((path.size() + 1) + " " + (changes[n - 1] - 1));
        while (!path.isEmpty()) {
            out.print(path.pop() + " ");
        }
    }
}
