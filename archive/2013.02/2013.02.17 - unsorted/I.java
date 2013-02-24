package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class I {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x + y == 0) {
                break;
            }
            out.println(solve(x, y));
        }
    }

    int[][] dp;
    final int N = 1001;

    public I() {
        dp = new int[N][N];
        for (int l = 2; l < N; l++) {
            for (int i = 20; i < N - l + 1; i++) {
                int ans = Integer.MAX_VALUE;
                for (int j = i; j < i + l - 1; j++) {
                    int cost = Math.max(dp[i][j], dp[j + 1][i + l - 1]) + j;
                    if (cost < ans) {
                        ans = cost;
                    }
                }
                dp[i][i + l - 1] = ans;
            }
        }
    }

    int solve(int x, int y) {
        return dp[x][y];
    }
}
