package chelper;

import java.util.ArrayList;
import java.util.HashSet;

public class XorTravelingSalesman {
	public int maxProfit(int[] cityValues, String[] roads) {
        int n = cityValues.length;

        ArrayList<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                if (roads[i].charAt(j) == 'Y')
                    edges[i].add(j);
            }
        }

        HashSet<Integer>[] possibilities = new HashSet[n];
        for (int i = 0; i < n; i++)
            possibilities[i] = new HashSet<Integer>();

        possibilities[0].add(cityValues[0]);
        while (true) {
            boolean added = false;
            for (int i = 0; i < n; i++) {
                for (Integer p : possibilities[i]) {
                    for (Integer j : edges[i]) {
                        int q = p ^ cityValues[j];
                        if (!possibilities[j].contains(q)) {
                            added = true;
                            possibilities[j].add(q);
                        }
                    }
                }
            }
            if (!added) break;
        }
        int max = -1;
        for (HashSet<Integer> h : possibilities) {
            for (Integer q : h) {
                if (q > max)
                    max = q;
            }
        }
        return max;
	}
}
