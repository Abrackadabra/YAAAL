package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;

public class CardGame {
    int mod = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        Arrays.sort(a);

        int[] C = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            C[i] = i;
        }

        for (int phi = 2; phi < k; phi++) {
            int[] nextC = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                nextC[i] = (nextC[i - 1] + C[i - 1]) % mod;
            }
            C = nextC;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = (int) (((ans + (long)C[i] * a[i]) % mod + mod) % mod);
        }

        out.printCase(testNumber);
        out.println(ans);
    }
}
