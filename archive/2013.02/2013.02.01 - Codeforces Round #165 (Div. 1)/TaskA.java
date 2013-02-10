package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int max = -1;

        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int a = in.nextInt();

            max = Math.max(max, k + 1);

            while (a > 1) {
                a = (a + 3) / 4;
                k++;
            }

            max = Math.max(max, k);
        }

        out.println(max);
    }
}
