package chelper;

import java.util.*;

public class TheMagicMatrix {
    long mod = 1234567891;

    public int find(int n, int[] givenRows, int[] givenCols, int[] values) {
        long ans = 0;

        for (int i = 0; i < 10; i++) {
            ans = (ans + find(n, givenRows, givenCols, values, i)) % mod;
        }

        return (int) ans;
    }

    private long find(int n, int[] givenRows, int[] givenCols, int[] values, int sum) {
        int[] rowSums = new int[n];
        int[] colSums = new int[n];

        List<Set<Integer>> rows = new ArrayList<Set<Integer>>();
        List<Set<Integer>> cols = new ArrayList<Set<Integer>>();

        for (int i = 0; i < n; i++) {
            rows.add(new HashSet<Integer>());
            cols.add(new HashSet<Integer>());
            for (int j = 0; j < n; j++) {
                rows.get(i).add(j);
                cols.get(i).add(j);
            }
        }

        for (int i = 0; i < givenRows.length; i++) {
            int row = givenRows[i];
            int col = givenCols[i];

            rowSums[row] = (rowSums[row] + values[i]) % 10;
            colSums[col] = (colSums[col] + values[i]) % 10;

            rows.get(row).remove(col);
            cols.get(col).remove(row);
        }

        Set<Integer> toSet = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            if (rows.get(i).size() == 1) {
                toSet.add(i * n + first(rows.get(i)));
            }
            if (cols.get(i).size() == 1) {
                toSet.add(first(cols.get(i)) * n + i);
            }
        }

        while (!toSet.isEmpty()) {
            int t = first(toSet);
            toSet.remove(t);
            int row = t / n;
            int col = t % n;

            int rVal = -1;
            int cVal = -1;
            if (rows.get(row).size() == 1) {
                rVal = (10 - rowSums[row]) % 10;
            }
            if (cols.get(col).size() == 1) {
                cVal = (10 - colSums[col]) % 10;
            }
            if (rVal != -1 && cVal != -1 && rVal != cVal) {
                return 0;
            }

            int val = rVal != -1 ? rVal : cVal;

            rowSums[row] = (rowSums[row] + val) % 10;
            colSums[col] = (colSums[col] + val) % 10;

            rows.get(row).remove(col);
            cols.get(col).remove(row);

            if (rows.get(row).size() == 1) {
                toSet.add(row * n + first(rows.get(row)));
            }

            if (cols.get(col).size() == 1) {
                toSet.add(first(cols.get(col)) * n + col);
            }
        }

        long ans = 1;

        for (int row = 0; row < n; row++) {

        }

        return ans;
    }

    boolean clean(int n, int[] givenRows, int[] givenCols, int[] values, int sum) {
        return true;
    }

    int first(Set<Integer> set) {
        for (int i : set) {
            return i;
        }
        return -1;
    }
}
