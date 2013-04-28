package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class FindRect {
    int w, h;
    int[][] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        w = in.nextInt();
        h = in.nextInt();

        a = new int[w][h];

        for (int y = 0; y < h; y++) {
            String s = in.nextString();
            for (int x = 0; x < w; x++) {
                if (x > 0) {
                    a[x][y] += a[x - 1][y];
                }
                if (y > 0) {
                    a[x][y] += a[x][y - 1];
                }
                if (x > 0 && y > 0) {
                    a[x][y] -= a[x - 1][y - 1];
                }
                if (s.charAt(x) == '1') {
                    a[x][y]++;
                }
            }
        }

        Square best = null;

        for (int i = 0; i < w - 14; i++) {
            for (int j = 0; j < h - 14; j++) {
                Square square = new Square(i, j);

                if (best == null || square.sum > best.sum) {
                    best = square;
                }
            }
        }

        //out.println(squares);

        int x1 = best.x;
        int y1 = best.y;
        int x2 = x1 + 14;
        int y2 = y1 + 14;

        for (int q = 0; q < 10000; q++) {
            double bestRatio = -1;
            int[] increase = null;

            if (x1 > 0) {
                int sum = sum(x1 - 1, y1, x1 - 1, y2);
                int area = y2 - y1 + 1;

                double ratio = sum * 1.0 / area;
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    increase = new int[] {-1, 0, 0, 0};
                }
            }

            if (x2 < w - 1) {
                int sum = sum(x2 + 1, y1, x2 + 1, y2);
                int area = y2 - y1 + 1;

                double ratio = sum * 1.0 / area;
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    increase = new int[] {0, 1, 0, 0};
                }
            }

            if (y1 > 0) {
                int sum = sum(x1, y1 - 1, x2, y1 - 1);
                int area = x2 - x1 + 1;

                double ratio = sum * 1.0 / area;
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    increase = new int[] {0, 0, -1, 0};
                }
            }

            if (y2 < h - 1) {
                int sum = sum(x1, y2 + 1, x2, y2 + 1);
                int area = x2 - x1 + 1;

                double ratio = sum * 1.0 / area;
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    increase = new int[] {0, 0, 0, 1};
                }
            }

            if (bestRatio < 0.5) break;

            x1 += increase[0];
            x2 += increase[1];
            y1 += increase[2];
            y2 += increase[3];
        }

        out.println((x1 + 1) + " " + (x2 + 1) + " " + (y1 + 1) + " " + (y2 + 1));
    }

    int sum(int x1, int y1, int x2, int y2) {
        int res = a(x2, y2);
        if (x1 > 0) {
            res -= a(x1 - 1, y2);
        }
        if (y1 > 0) {
            res -= a(x2, y1 - 1);
        }
        if (x1 > 0 && y1 > 0) {
            res += a(x1 - 1, y1 - 1);
        }
        return res;
    }

    int a(int x, int y) {
        return a[Math.max(Math.min(x, w - 1), 0)][Math.max(Math.min(y, h - 1), 0)];
    }

    class Square implements Comparable<Square> {
        int x, y;
        int sum;

        Square(int x, int y) {
            this.x = x;
            this.y = y;

            sum = sum(x, y, x + 14, y + 14);
        }

        @Override
        public int compareTo(Square o) {
            if (sum > o.sum) {
                return -1;
            }
            if (sum < o.sum) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "[" + x + " " + y + " = " + sum + "]";
        }
    }
}