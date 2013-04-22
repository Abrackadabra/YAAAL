package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;

public class L {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int u = in.nextInt();
        int[] a = in.nextIntArray(n);

        Arrays.sort(a);

        if (u >= n) {
            out.println(-1);
        } else {
            out.println(a[u]);
        }
    }
}
