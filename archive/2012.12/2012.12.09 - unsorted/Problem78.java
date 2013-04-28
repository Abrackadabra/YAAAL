package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.util.MiscUtils;

import java.util.*;

public class Problem78 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextIntArray(n);
        
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1 << 25);
        }
        dp[0][0] = a[0][0];

        while (true) {
            boolean same = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        if (!MiscUtils.rangeCheck(0, n - 1, x) || !MiscUtils.rangeCheck(0, n - 1, y)) {
                            continue;
                        }

                        if (dp[i][j] > dp[x][y] + a[i][j]) {
                            same = false;
                            dp[i][j] = dp[x][y] + a[i][j];
                        }
                    }
                }
            }

            if (same) break;
        }

        out.println(dp[n - 1][n - 1]);
    }

    int[][] directions = {
                                 {0, 1},
                                 {1, 0},
                                 {0, -1},
                                 {-1, 0},
    };
}
