package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.math.*;

public class Rooks {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        BigInteger[][] c = new BigInteger[110][110];

        for (int n = 0; n <= 100; ++n) {
            c[n][0] = BigInteger.ONE;
            c[n][n] = BigInteger.ONE;
            for (int k = 1; k < n; ++k) {
                c[n][k] = c[n - 1][k].add(c[n - 1][k - 1]);
            }
        }



        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();

        BigInteger[][][] dp = new BigInteger[11][11][101];
        for(int i = 0; i <=10;++i){
            for(int j = 0; j <=10; ++j){
                for(int sum = 0; sum <=100; ++sum){
                    dp[i][j][sum] = BigInteger.ZERO;
                }
            }
        }
        for(int b = 1; b <= m; ++b)
            dp[1][b][b] = BigInteger.ONE;
        for(int a = 2; a <= n; ++a ){
            for(int b = 1; b <= m; ++b){
                for(int sum = 0; sum <= a * b; ++sum){
                    for(int oldColumns = 0; oldColumns <= sum && oldColumns <= b; ++oldColumns){
                        for(int newColumns = 0; newColumns + oldColumns <= sum && newColumns + oldColumns <= b; ++newColumns){
                            if(newColumns + oldColumns == 0)
                                continue;
                            dp[a][b][sum] = dp[a][b][sum].add(dp[a - 1][b - newColumns][sum - newColumns - oldColumns].multiply(c[b][newColumns]).multiply(c[b - newColumns][oldColumns]));
                        }
                    }
                }
            }
        }

        BigInteger ans = BigInteger.ZERO;

        for (int i = 1; i <= n; ++i) { //stol b
            for (int j = 1; i + j <= n; ++j) { // stol c
                for (int a = 1; a <= m; ++a) { // str b
                    for (int b = 1; b + a <= m; ++b) { // str c

                        if (i * a < k) {
                            continue;
                        }
                        if (j * b < l) {
                            continue;
                        }

                        ans = ans.add(c[n][i].multiply(c[n - i][j]).multiply(c[m][a]).multiply(c[m - a][b]).multiply(dp[i][a][k]).multiply(dp[j][b][l]));
                        //ans += c[n][i] * c[n - i][j] * c[m][a] * c[m - a][b] * c[i * a][k] * c[j * b][l];
                    }
                }
            }
        }

        out.println(ans);
    }
}
