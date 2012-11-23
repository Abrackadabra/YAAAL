package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Dice {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][][] dp = new int[n + 1][m + 1][6];
        int[][][] dpNo = new int[n + 1][m + 1][3];
        int[][] a= new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                a[i][j] = in.nextInt();
            }
        }

        for(int i = 0; i <= n; ++i){
            Arrays.fill(dpNo[i][0], Integer.MIN_VALUE);
        }
        for(int j = 0; j <= m ;++j){
            Arrays.fill(dpNo[0][j], Integer.MIN_VALUE);
        }

        Arrays.fill(dpNo[0][1], 0);
        for(int i = 1; i <= n; ++i){
            for(int j = 1; j<=m;++j){
                for(int k = 0; k < 6; ++k){
                    dp[i][j][k] = Math.max(dpNo[i - 1][j][Math.min(k, 5 - k)], dpNo[i][j-1][Math.min(k, 5 - k)]) + (k + 1) * a[i-1][j-1];
                }
                for(int k = 0; k < 3 ;++k){
                    dpNo[i][j][k] = Integer.MIN_VALUE;
                    for(int l = 0; l < 6; ++l){
                        if (k == l || k == 5 - l) {
                            continue;
                        }
                        dpNo[i][j][k] = Math.max(dpNo[i][j][k], dp[i][j][l]);
                    }
                }
            }
        }



        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < 6; ++i){
            ans = Math.max(dp[n][m][i], ans);
        }

        out.println(ans);

	}
}
