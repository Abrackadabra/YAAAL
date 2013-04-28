package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;

public class J {
    ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

    int[] demand;
    int[] capacity;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<Integer>());
        }

        demand = new int[n + 1];
        capacity = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int p = in.nextInt();
            edges.get(p).add(i);
            demand[i] = in.nextInt();
            capacity[i] = in.nextInt();
        }

        int ans = 0;

        for (int i : edges.get(0)) {
            ans += calc(i)[capacity[i]];
        }

        out.println(ans);
    }

    int[] calc(int v) {
        ArrayList<int[]> dps = new ArrayList<int[]>();
        for (Integer i : edges.get(v)) {
            dps.add(calc(i));
        }

        int[] temp = new int[101];
        for (int i = demand[v]; i <= capacity[v]; i++) {
            temp[i] = 1;
        }
        dps.add(temp);

        int[] res = dps.get(0);
        for (int i = 1; i < dps.size(); i++) {
            int[] dp = dps.get(i);

            int[] ans = new int[101];

            for (int prev = 0; prev <= 100; prev++) {
                for (int cur = 0; cur <= 100; cur++) {
                    if (prev + cur > capacity[v]) {
                        break;
                    }

                    if (res[prev] + dp[cur] > ans[prev + cur]) {
                        ans[prev + cur] = res[prev] + dp[cur];
                    }
                }
            }

            res = ans;
        }

        return res;
    }
}
