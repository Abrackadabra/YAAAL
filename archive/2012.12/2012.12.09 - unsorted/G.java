package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class G {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = in.nextIntArray(n);
        Arrays.sort(a);

        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = a[i];
            if (i > 0) {
                sum[i] += sum[i - 1];
            }
        }

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < n - m + 1; i++) {
            int s = sum[i + m - 1];
            if (i > 0) s -= sum[i - 1];

            s -= a[i] * m;
            if (s < min1) {
                min1 = s;
                min2 = a[i];
            }
            if (s == min1 && a[i] < min2) {
                min2 = a[i];
            }
        }

        out.println(min2 + " " + min1);
    }
}
