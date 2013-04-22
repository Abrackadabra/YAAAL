package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.awt.geom.Line2D;

public class H {
    int[][] a;
    int max = -1;
    String ans = "";
    double eps = 1e-3;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        a = new int[5][5];

        for (int i = 0; i < 5; i++) {
            String s = in.nextString();
            for (int j = 0; j < 5; j++) {
                if (s.charAt(j) == 'A') {
                    a[j][i] = 1;
                }
                if (s.charAt(j) == 'P') {
                    a[j][i] = 2;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (a[j][k] == 1) {
                        go(i, -1, j, k);
                        go(i, 5, j, k);
                    }
                }
            }
        }

        out.println(max);
        out.println(ans);
    }

    void go(int x0, int y0, int x1, int y1) {
        int x = calc(x0, y0, x1, y1);

        if (x > max) {
            max = x;
            ans = (y0 + 2) + " " + (x0 + 1) + "\n" + (y1 + 2) + " " + (x1 + 1);
        }
    }

    int calc(int x0, int y0, int x1, int y1) {
        Line2D line = new Line2D.Double(x0 + 0.5, y0 + 0.5, x1 + 0.5, y1 + 0.5);

        int res = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (line.intersects(i + eps, j + eps, 1 - 2 * eps, 1 - 2 * eps)) {
                    if (a[i][j] == 2) {
                        return 0;
                    }
                    if (a[i][j] == 1) {
                        res++;
                    }
                }
            }
        }

        return res;
    }
}
