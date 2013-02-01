package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.util.MiscUtils;

import java.util.*;

public class FindTheMin {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.printCase(testNumber);

        SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        TreeSet<Integer> good = new TreeSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();

        int n = in.nextInt();
        int k = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int r = in.nextInt();

        for (int i = 0; i < k * 2; i++) {
            good.add(i);
        }

        for (int i = 0; i < k; i++) {
            if (!map.containsKey(a)) {
                map.put(a, 0);
                good.remove(a);
            }
            map.put(a, map.get(a) + 1);
            queue.add(a);
            a = (int) (((long)b * a + c) % r);
        }

        n -= k;

        int last = -1;

        for (int i = 0; i < n; i++) {
            last = good.first();

            good.remove(last);

            map.put(last, 1);

            queue.add(last);

            int f = queue.poll();

            map.put(f, map.get(f) - 1);
            if (map.get(f) == 0) {
                map.remove(f);
                good.add(f);
            }
        }

        MiscUtils.assertMF(last >= 0);

        out.println(last);
        System.out.println(last);
    }
}
