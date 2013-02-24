package chelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TheFrog {
    double eps = 1e-7;

    public double getMinimum(int D, int[] L, int[] R) {
        int n = L.length;
        Set<Double> candidates = new TreeSet<Double>();
        int[][] lands = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 50000; j++) {
                candidates.add(L[i] * 1d / j);
                candidates.add(R[i] * 1d / j);
            }
            lands[i][0] = L[i];
            lands[i][1] = R[i];
        }

        Arrays.sort(lands, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int prev = 0;

        candidates.remove(0.0);

        for (Double candidate : candidates) {
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (lands[i][0] >= D && landsBetween(candidate, prev, lands[i][0])) {
                    break;
                }
                if (landsBetween(candidate, lands[i][0], lands[i][1])) {
                    ok = false;
                }
            }
            if (ok) {
                return candidate;
            }
        }
        return 0.0;
    }

    boolean landsBetween(double x, int l, int r) {
        double a = l / x;
        if (Math.abs(a - Math.round(a)) < eps) {
            a = Math.round(a);
        } else {
            a = Math.floor(a);
        }
        double b = r / x;
        if (Math.abs(b - Math.round(b)) < eps) {
            b = Math.round(b);
        } else {
            b = Math.ceil(b);
        }
        return b - a >= 1.5;
    }
}
