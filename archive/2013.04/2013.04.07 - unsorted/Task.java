package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class Task {
    int w, h, x, y, s, r;
    
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int w = random.nextInt(10) + 10;
            int h = random.nextInt(10) + 10;
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int s = random.nextInt(100) + 1;
            int r = random.nextInt(360);

            double[] a = stupid(w, h, x, y, s, r);
            double[] b = leha(w, h, x, y, s, r);
            if (Math.hypot(a[0] - b[0], a[1] - b[1]) > eps * 100) {
                System.out.println(w + " " + h + " " + x + " " + y + " " + s + " " + r);
                System.out.println(Arrays.toString(a));
                System.out.println(Arrays.toString(b));
                System.out.println();
            } else {
                System.out.println("Ok");
                System.out.println();
            }
        }

    }

    double[] leha(int w, int h, int ox, int oy, int s, int r) {
        double ds = s / 100.0;
        double phi = r / 180.0 * Math.PI;

        double A = -ox;
        double B = - oy;

        double x = (A * (ds * Math.cos(phi) - 1) + B * ds * Math.sin(phi)) / (1 + ds * ds - 2 * ds * Math.cos(phi));
        double y = (B * (ds * Math.cos(phi) - 1) - A * ds * Math.sin(phi)) / (1 + ds * ds - 2 * ds * Math.cos(phi));

        //  out.println(A + " " + B);

        return new double[]{x, y};
    }

    double[] stupid(int w, int h, int x, int y, int s, int r) {
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.s = s;
        this.r = r;

        if (w == h && h == x && x == y && y == s && s == r && r == 0) {
            throw new UnknownError();
        }

        double dx = w / 2.0, dy = h / 2.0;
        double ds = Math.min(w, h) / 4.0;

        while (ds > eps) {
            double d = calcDistance(dx, dy);

            double nd = d;
            double nx = -1, ny = -1;

            for (int[] direction : directions) {
                double xx = dx + direction[0] * ds;
                double yy = dy + direction[1] * ds;

                if (calcDistance(xx, yy) < nd) {
                    nd = calcDistance(xx, yy);
                    nx = xx;
                    ny = yy;
                }
            }

            if (nx == -1) {
                ds /= 2;
            } else {
                dx = nx;
                dy = ny;
            }
        }

        return new double[]{dx, dy};
    }

    int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 1},
            {1, 0},
            {1, -1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
    };

    double eps = 1e-6;

    double calcDistance(double tx, double ty) {

        double d = Math.hypot(tx, ty);
        double a = Math.asin(ty / d);

        a += r / 360.0 * 2 * Math.PI;
        d *= s / 100.0;

        double x = this.x + Math.cos(a) * d;
        double y = this.y + Math.sin(a) * d;

        double z = Math.hypot(x - tx, y - ty);
        return z;
    }
}
