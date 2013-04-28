package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        if(n == 0)
            throw new UnknownError();

        int[][] d = new int[n][];
        for(int i = 0; i < n ;++i){
            d[i] = in.nextIntArray(n);
            for(int j = 0; j < n; ++j) {
                if(d[i][j] == 0 && i != j){
                    d[i][j] = 10000;
                }
            }
        }


        int m = in.nextInt();
        int[] fixed = in.nextIntArray(m);
        int[] len = new int[m];

        for(int i = 1; i < m; ++i){
            len[i] = len[i - 1] + d[fixed[i - 1]][fixed[i]];
        }

        for(int k = 0; k < n; ++k)
            for(int i = 0; i < n; ++i)
                for(int j = 0; j < n; ++j){
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }

        int[] dp = new int[m];

        for(int i = 1; i < m; ++i){
            dp[i] = Integer.MAX_VALUE;

            for(int j = 0; j < i; ++j){
                if(len[i - 1] - len[j] == d[fixed[j]][fixed[i - 1]]){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < m; ++i){
            if(len[m - 1] - len[i] == d[fixed[i]][fixed[m - 1]]) {
                ans = Math.min(dp[i], ans);
            }
        }

        out.println("Case " + testNumber + ": " + ans);
    }
}
