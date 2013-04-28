package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class L {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] a = in.nextString().toCharArray();

        for (int i = 0; i < a.length / 2; i++) {
            if (a[a.length - 1 - i] != a[i]) {
                a[a.length - 1 - i] = a[i];
            }
        }

        out.println(new String(a));
    }
}
