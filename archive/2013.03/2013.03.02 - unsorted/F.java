package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;

public class F {
    int[] people;
    int[] already;
    int[] add;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        if (n == 0 && m == 0) {
            throw new UnknownError();
        }

        people = new int[n];
        already = new int[n];
        add = new int[n];

        for (int i = 0; i < n; i++) {
            people[i] = in.nextInt();
            already[i] = in.nextInt();
            add[i] = in.nextInt();
        }

        int[][] dp = new int[n][m + 1];

        for (int i = 0; i <= m; i++) {
            dp[n - 1][i] = calc(n - 1, i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int curMoney = 0; curMoney <= m; curMoney++) {
                for (int prevMoney = 0; prevMoney <= m - curMoney; prevMoney++) {
                    int totalMoney = curMoney + prevMoney;
                    int totalResult = calc(i, curMoney) + dp[i + 1][prevMoney];
                    if (totalResult >= dp[i][totalMoney]) {
                        dp[i][totalMoney] = totalResult;
                    }
                }
            }
        }

        int[] moneyDistribution = new int[n];

        int moneyLeft = m;
        for (int i = 0; i < n - 1; i++) {
            int need = dp[i][moneyLeft];
            int money = -1;
            for (int j = 0; j <= moneyLeft; j++) {
                if (dp[i + 1][moneyLeft - j] + calc(i, j) == need) {
                    money = j;
                }
            }
            moneyDistribution[i] = money;
            moneyLeft -= money;
        }
        moneyDistribution[n - 1] = moneyLeft;

        out.print("Case " + testNumber + ": ");
        out.println(dp[0][m]);

        ArrayList<String> strings = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            strings.add(i + ":" + moneyDistribution[i]);
        }

        out.printSeparated(strings.toArray());
        out.println();
    }

    int calc(int i, int x) {
        long numerator = people[i] * (10L * x * add[i] + already[i] * (101 + 10L * x));
        long denominator = 100 * (101 + 10 * x);
        int ans = (int) (numerator / denominator);
        if (numerator % denominator * 2 >= denominator) {
            ans++;
        }
        return ans;
    }
}
