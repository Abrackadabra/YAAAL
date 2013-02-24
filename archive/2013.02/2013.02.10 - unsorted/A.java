package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class A {
    int n;
    boolean[] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        int enough = in.nextInt();

        a = new boolean[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() >= enough;
        }


        int q = in.nextInt();

        for (int i = 0; i < q; i++) {
            int[] res = calc(in.nextInt());

            out.println(res[0] + " " + res[1]);
        }
    }

    Map<Integer, int[]> map = new HashMap<Integer, int[]>();

    int[] calc(int p) {
        if (map.containsKey(p)) {
            return map.get(p);
        }

        int[] res = new int[2];

        for (int j = p; true; j += p) {
            if (j >= n) {
                res[0] += a[n - 1] ? 1 : 0;
                res[1] += a[n - 1] ? 0 : 1;
                break;
            }
            res[0] += a[j - 1] ? 1 : 0;
            res[1] += a[j - 1] ? 0 : 1;
        }

        map.put(p, res);

        return res;
    }
}
