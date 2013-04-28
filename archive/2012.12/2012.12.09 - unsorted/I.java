package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class I {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int max = (int) (20000000 * 1.1);
        int[] a = new int[max];
        int[] prev = new int[max];
        Arrays.fill(a, Integer.MAX_VALUE);
        a[1] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int t = queue.poll();

            int x = t / 2;
            if (a[x] > a[t] + 1) {
                queue.add(x);
                a[x] = a[t] + 1;
                prev[x] = t;
            }

            x = t * 3;
            if (x < max && a[x] > a[t] + 1) {
                queue.add(x);
                a[x] = a[t] + 1;
                prev[x] = t;
            }
        }

        int x = in.nextInt();

        char[] res = new char[a[x]];
        for (int i = a[x] - 1; i >= 0; i--) {
            if (prev[x] < x) {
                res[i] = '3';
            } else {
                res[i] = '2';
            }
            x = prev[x];
        }
        out.println(new String(res));
    }
}
