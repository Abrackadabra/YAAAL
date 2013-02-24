package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();
        int s = in.nextInt();

        for (int i = 0; i < n; i++) {
            l -= 20 + in.nextInt();
        }

        out.printYesNo(l >= 20 + s, true);
    }
}
