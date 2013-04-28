package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

import java.util.*;

public class J {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[2][n];
        a[0] = in.nextIntArray(n);
        a[1] = in.nextIntArray(n);

        int[] min = new int[k];
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            min[a[0][i] - 1] = AMath.min(min[a[0][i] - 1], a[1][i]);
        }
        int sum = 0;
        for (int i : min) {
            sum += i;
        }
        out.println(sum);
    }
}
