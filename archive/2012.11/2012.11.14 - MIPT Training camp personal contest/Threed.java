package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.*;

public class Threed {
    class Point {
        double x, y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    double eps = 1e-9;

    ArrayList<Point> a[] = new ArrayList[3];

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        for (int i = 0; i < 3; ++i) {
            int cur = in.nextInt();
            a[i] = new ArrayList<Point>();
            for (int j = 0; j < cur; ++j) {
                a[i].add(new Point(in.nextDouble(), in.nextDouble()));
            }
        }

        boolean flag = true;

        for (int i = 0; i < 3 && flag; ++i) {
            for (int j = 0; j < a[i].size() && flag; ++j) {
                Point now = a[i].get(j);
                ArrayList<Point> points = a[(i + 1) % 3];

                double l = Double.MAX_VALUE;
                double r = Double.MIN_VALUE;

                for (int w = 0; w < points.size(); ++w) {
                    if (points.get(w).x == now.y) {
                        l = Math.min(l, points.get(w).y);
                        r = Math.max(r, points.get(w).y);
                    } else {
                        Point nex = points.get((w + 1) % points.size());
                        if ((points.get(w).x - now.y) * (nex.x - now.y) < 0) {
                            double z = (now.y - points.get(w).x) * nex.y + (nex.x - now.y) * points.get(w).y;
                            z /= (nex.x - points.get(w).x);
                            l = Math.min(l, z);
                            r = Math.max(r, z);
                        }
                    }
                }

                if (l == Double.MAX_VALUE) {
                    flag = false;
                    break;
                }

                Double L = Double.MAX_VALUE;
                Double R = Double.MIN_VALUE;

                points = a[(i + 2) % 3];

                for (int w = 0; w < points.size(); ++w) {
                    if (points.get(w).y == now.x) {
                        L = Math.min(L, points.get(w).x);
                        R = Math.max(R, points.get(w).x);
                    } else {
                        Point nex = points.get((w + 1) % points.size());
                        if ((points.get(w).y - now.x) * (nex.y - now.x) < 0) {
                            double z = (now.x - points.get(w).y) * nex.x + (nex.y - now.x) * points.get(w).x;
                            z /= (nex.y - points.get(w).y);
                            L = Math.min(L, z);
                            R = Math.max(R, z);
                        }
                    }
                }

                if (L == Long.MAX_VALUE) {
                    flag = false;
                    break;
                }

                l = Math.max(l, L);
                r = Math.min(r, R);

                if (l > r + eps) {
                    flag = false;
                }
            }
        }

        if (!flag) {
            out.println("No");
            return;
        }

        out.println("Yes");

    }
}
