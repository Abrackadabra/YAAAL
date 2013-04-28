package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long a = 2;
        long b = 1;
        long MOD = 1000000009;


        for (int i = 0; i < n; i++) {
            a = (a * 2) % MOD;
            b = (b * 3) % MOD;
        }

        long res = (b - a + 1 + MOD) % MOD;

        out.println(res);
    }
}
