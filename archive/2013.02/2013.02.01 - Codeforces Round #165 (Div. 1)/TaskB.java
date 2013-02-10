package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt(), m = in.nextInt();

        int[] dp = new int[m];

        for (int i = 0; i < n; i++) {
            int a = in.nextInt() - 1;
            double useless = in.nextDouble();

            for (int j = a; j >= 0; j--) {
                dp[a] = Math.max(dp[a], dp[j] + 1);
            }

            for (int j = a + 1; j < m; j++) {
                dp[j] = Math.max(dp[j], dp[j - 1]);
            }
        }

        out.println(n - dp[m - 1]);
    }
}
