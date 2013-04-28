package chelper;

import java.util.Arrays;

public class PolygonTraversal {
    boolean[] free;
    boolean[] bad;
    int n;

    public long count(int n, int[] points) {
        this.n = n;
        free = new boolean[n];
        Arrays.fill(free, true);
        bad = new boolean[n];
        for (int i = 0; i < points.length; i++) {
            points[i]--;
            free[points[i]] = false;
        }
        bad[(points[0] + 1) % n] = true;
        bad[(points[0] - 1 + n) % n] = true;
        int left = n - points.length;

        return go(points[points.length - 1], left);
    }

    long go(int cur, int left) {
        if (left == 0) {
            return bad[cur] ? 0 : 1;
        }

        long res = 0;

        for (int start = 0; start < n; start++) {
            if (!free[start] || free[(start - 1 + n) % n]) {
                continue;
            }

            int length = 0;
            for (; true; length++) {
                if (!free[(start + length) % n]) {
                    break;
                }
            }

            if (start == (cur + 1) % n) {
                continue;
            }
            if ((start + length) % n == cur) {
                continue;
            }

            for (int i = 0; i < length / 2; i++) {
                int l = (start + i) % n;
                int r = (start + length - 1 - i + n) % n;
                if (bad[l] == bad[r]) {
                    free[l] = false;
                    res += go(l, left - 1) * 2;
                    free[l] = true;
                } else {
                    free[l] = false;
                    res += go(l, left - 1);
                    free[l] = true;
                    free[r] = false;
                    res += go(r, left - 1);
                    free[r] = true;
                }
            }

            if (length % 2 == 1) {
                int p = (start + length / 2) % n;
                free[p] = false;
                res += go(p, left - 1);
                free[p] = true;
            }
        }

        return res;
    }
}
