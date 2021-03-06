package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Problem77 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int max = 500;
        int[] dp = new int[max];
        dp[0] = 1;
        ArrayList<Integer> primes = abrackadabra.math.Math.getPrimes(max);
        for (int p : primes) {
            for (int i = p; i < max; i++) {
                dp[i] += dp[i - p];
            }
        }

        for (int i = 0; i < max; i++) {
            System.out.println(i + " " + dp[i]);
        }
    }
}
