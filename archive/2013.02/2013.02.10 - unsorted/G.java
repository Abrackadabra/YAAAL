package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;

public class G {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] d = in.nextIntArray(n);

        Arrays.sort(d);

        double min = d[0] / 2.0;

        for (int i = 0; i < n - 1; i++) {
            min = Math.min(min, (d[i + 1] - d[i]) / 2.0);
        }

        out.println(d[n - 1] / 2.0 / min + 1);
    }
}
