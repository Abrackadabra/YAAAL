package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class DeadPixels {
    int[] table = new int[50000000];

    int w, h;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        for (int i = 0; i < 32; i++) {
            masks[i] = (1L << i);
        }

        Arrays.fill(table, 0);

        w = in.nextInt();
        h = in.nextInt();

        get(w - 1, h - 1);

        int p = in.nextInt();
        int q = in.nextInt();

        int n = in.nextInt();

        int x = in.nextInt();
        int y = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();

        Set<Integer> dots = new TreeSet<Integer>();

        for (int i = 0; i < n; i++) {
            dots.add(x + y * w);

            int nx = (x * a + y * b + 1) % w;
            int ny = (x * c + y * d + 1) % h;

            x = nx;
            y = ny;
        }

        int ans = (w - p + 1) * (h - q + 1);

        for (Integer dot : dots) {
            int startX = dot % w;
            int startY = dot / w;


            for (x = Math.min(startX, w - p); x >= 0 && startX - x < p; x--) {
                //if (get(x, startY)) break;
                for (y = Math.min(startY, h - q); y >= 0 && startY - y < q; y--) {
                    if (get(x, y)) break;
                    set(x, y);
                    ans--;
                }
            }
        }

        out.printCase(testNumber);
        out.println(ans);
    }

    long[] masks = new long[32];

    boolean get(int x, int y) {
        int bit = x + y * w;
        return (table[bit / 32] & masks[bit % 32]) > 0;
    }

    void set(int x, int y) {
        int bit = x + y * w;
        table[bit / 32] |= masks[bit % 32];
    }
}
