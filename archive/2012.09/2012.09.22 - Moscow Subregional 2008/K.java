package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.Arrays;

public class K {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt(), colors = in.nextInt();

        short[][][] dp = new short[n][n][colors];
        int[] initial = new int[n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i][i], (short) 1);
            initial[i] = in.nextInt() - 1;
            dp[i][i][initial[i]] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                int min = 1000;

                Arrays.fill(dp[i][j], (short) 1000);
                for (int pivot = i; pivot <= j; pivot++) {
                    int color = initial[pivot];
                    int pos = 0;
                    if (pivot - 1 >= i) pos += dp[i][pivot - 1][color];
                    if (pivot + 1 <= j) pos += dp[pivot + 1][j][color];
                    dp[i][j][color] = (short) Math.min(dp[i][j][color],
                            pos
                            );
                    min = Math.min(min, dp[i][j][color]);
                }

                for (int color = 0; color < colors; color++) {
                    dp[i][j][color] = (short) Math.min(dp[i][j][color], min + 1);
                }
            }
        }

        /*for (int color = 0; color < colors; color++) {
            out.println("c: " + color);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print(dp[i][j][color] + " ");
                }
                out.println();
            }
        }*/

        int min = Integer.MAX_VALUE;
        for (int color = 0; color < colors; color++) {
            if (dp[0][n - 1][color] < min)
                min = dp[0][n - 1][color];
        }
        out.println(min);
	}
}
