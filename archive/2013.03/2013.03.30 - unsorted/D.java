package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt(), b = in.nextInt();

        if (a == 0 && b == 0) throw new UnknownError();

        int ans = 0;

        for (int i = 1; i * (i + 1) / 2 + 1 <= b; i++) {
            int sq = (i + 1) * i / 2 + 1;

            int q = (int) Math.round(Math.sqrt(sq));

            if (q * q == sq && sq > a && sq < b) {
                ans++;
            }
        }

        out.println("Case " + (testNumber) + ": " + ans);
    }
}
