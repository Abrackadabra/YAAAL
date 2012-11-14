package chelper;

public class Stamp {
	public int getMinimumCost(String desiredColor, int stampCost, int pushCost) {
        int n = desiredColor.length();
        int[] desired = new int[n];
        for (int i = 0; i < n; i++) {
            if (desiredColor.charAt(i) == 'R') desired[i] = 0;
            if (desiredColor.charAt(i) == 'G') desired[i] = 1;
            if (desiredColor.charAt(i) == 'B') desired[i] = 2;
            if (desiredColor.charAt(i) == '*') desired[i] = -1;
        }
        long ans = Integer.MAX_VALUE;
        for (int l = 1; l <= n; l++) {
            int[][][] dp = new int[200][200][3];
            for (int i = 0; i < 200; i++) {
                for (int j = 0; j < 200; j++) {
                    for (int k = 0; k < 3; k++) {
                        dp[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                boolean ok = true;
                for (int j = 0; j < l; j++) {
                    if (desired[j] != i && desired[j] != -1)
                        ok = false;
                }
                if (!ok) continue;
                for (int j = 0; j < l; j++)
                    dp[l - 1][j][i] = 1;
            }

            for (int i = 1; i < n; i++) {
                for (int color = 0; color < 3; color++) {
                    boolean ok = true;
                    for (int j = 0; j < l; j++) {
                        if (j + i >= n || (desired[i + j] != color && desired[j + i] != -1))
                            ok = false;
                    }
                    if (!ok) continue;

                    int min = Integer.MAX_VALUE;
                    for (int anotherColor = 0; anotherColor < 3; anotherColor++) {
                        for (int left = i - 1; left >= 0; left--)
                            min = Math.min(min, dp[i - 1][left][anotherColor]);
                    }

                    for (int right = i; right < i + l; right++) {
                        min = Math.min(min, dp[right][i][color]);
                    }

                    if (min == Integer.MAX_VALUE) continue;

                    min++;

                    for (int left = i; left < i + l; left++) {
                        dp[i + l - 1][left][color] = Math.min(dp[i + l - 1][left][color], min);
                    }
                }
            }

            for (int left = 0; left < n; left++) {
                for (int color = 0; color < 3; color++) {
                    if (dp[n - 1][left][color] == Integer.MAX_VALUE) continue;
                    ans = Math.min(ans, dp[n - 1][left][color] * pushCost + l * stampCost);
                }
            }
        }
        return (int)ans;
	}
}
