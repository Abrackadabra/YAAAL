package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Sum {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] x = new int[n * 2];

        for (int i = 0; i < n * 2; ++i) {
            x[i] = in.nextInt() % n;
        }

        ArrayList<Integer> v = solve(n, x);
        Collections.sort(v);
        int sum = 0;
        for (int i : v) {
            sum += x[i];
            sum %= n;
            out.print(i + 1 + " ");
        }
        if (sum % n != 0) {
            throw new AssertionError("wrong");
        }
        if (v.size() != n) {
            throw new AssertionError("wrong size");
        }


    }

    public ArrayList<Integer> solve(int n, int[] x) {
        ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> currentBad = new ArrayList<Integer>();

        for (int i = 0; i < 9; ++i) {
            currentBad.add(i);
        }
        int start = 9;
        while (true) {
            if (start + 10 > x.length) {
                break;
            }
            for (int i = 0; i < 10; ++i, ++start) {
                currentBad.add(start);
            }
            boolean[] result = findGood(currentBad, x);
            ArrayList<Integer> newGroup = new ArrayList<Integer>();
            ArrayList<Integer> nextBad = new ArrayList<Integer>();

            for (int i = 0; i < 19; ++i) {
                if (result[i]) {
                    newGroup.add(currentBad.get(i));
                } else {
                    nextBad.add(currentBad.get(i));
                }
            }
            groups.add(newGroup);
            currentBad = nextBad;
        }

        if (n == 10) {
            return groups.get(0);
        }

        int[] y = new int[groups.size()];
        for (int i = 0; i < y.length; ++i) {
            for (int j = 0; j < 10; ++j) {
                y[i] += x[groups.get(i).get(j)];
            }
        }
        for (int i = 0; i < y.length; ++i) {
            y[i] = y[i] / 10;
        }
        ArrayList<Integer> indexes = solve(n / 10, y);

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i : indexes) {
            result.addAll(groups.get(i));
        }

        return result;
    }

    boolean[] findGood(ArrayList<Integer> a, int[] x) {
        int mod = 10;
        if (a.size() != 19) {
            throw new IllegalArgumentException("burn");
        }

        boolean[] result = new boolean[19];

        int[][] dp = new int[mod][11];
        for (int i = 0; i < mod; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = -2;

        for (int index = 0; index < a.size(); index++) {
            int cur = x[a.get(index)] % mod;
            for (int c = 9; c >= 0; c--) {
                for (int m = 0; m < mod; m++) {
                    if (dp[m][c] == -1) {
                        continue;
                    }
                    int next = (m + cur) % mod;
                    if (dp[next][c + 1] == -1) {
                        dp[next][c + 1] = index;
                    }
                }
            }
        }

        if (dp[0][10] == -1) {
            throw new IllegalArgumentException("you are wrong");
        }

        int c = 10;
        int t = 0;
        int cur = dp[t][c];
        while (cur != -2) {
            //result.add(a.get(cur));
            result[cur] = true;
            t = (t - x[a.get(cur)] % mod + mod) % mod;
            c--;
            cur = dp[t][c];
        }
        int sum = 0;
        for (int i = 0; i < 19; ++i)
            if (result[i]){
                sum += x[a.get(i)];
                sum %= 10;
            }
        if (sum != 0) {
            throw new AssertionError("baad");
        }
        return result;
    }
}
