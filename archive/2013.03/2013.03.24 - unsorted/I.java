package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class I {

    class Point {
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double x;
        double y;

        Point add(Point b) {
            return new Point(x + b.x, y + b.y);
        }

        Point multiply(double t) {
            return new Point(x * t, y * t);
        }

        public Point subtract(Point b) {
            return new Point(x - b.x, y - b.y);
        }

        double distance(Point b) {
            return sqr(x - b.x) + sqr(y - b.y);
        }

        private double sqr(double v) {
            return v * v;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n1 = in.nextInt();
        Point[] a = new Point[n1];
        for (int i = 0; i < n1; i++) {
            a[i] = new Point(in.nextInt(), in.nextInt());
        }

        int n2 = in.nextInt();
        Point[] b = new Point[n2];
        for (int i = 0; i < n2; i++) {
            b[i] = new Point(in.nextInt(), in.nextInt());
        }

        double min = 1e9;

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                min = Math.min(min, distance(a[i], a[(i + 1) % n1], b[j]));
            }
        }

        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n1; j++) {
                min = Math.min(min, distance(b[i], b[(i + 1) % n2], a[j]));
            }
        }

        out.println(min / 2);
    }

    double distance(Point a, Point b, Point x) {
        double l = 0, r = 1;
        for (int i = 0; i < 100; ++i) {
            Point m1 = a.add(b.subtract(a).multiply(l + (r - l) / 3));
            Point m2 = a.add(b.subtract(a).multiply(l + (r - l) * 2 / 3));

            if (x.distance(m1) > x.distance(m2)) {
                l += (r - l) / 3;
            } else {
                r = l + (r - l) * 2 / 3;
            }

        }

        return Math.sqrt(x.distance(

                a.add(

                        b.subtract(a).multiply(l)

                     )

                                   ));
    }
}
