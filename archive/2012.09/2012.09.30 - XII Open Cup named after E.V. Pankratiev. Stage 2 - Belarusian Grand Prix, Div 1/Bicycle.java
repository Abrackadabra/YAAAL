package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Bicycle {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        HashSet<Line> lines = new HashSet<Line>();
        HashSet<Point> points = new HashSet<Point>();
        long x0 = in.nextLong(), y0 = in.nextLong(), x1 = in.nextLong(), y1 = in.nextLong();
        lines.add(new Line(x0, y0, x1, y0));
        lines.add(new Line(x0, y1, x1, y1));
        lines.add(new Line(x0, y0, x0, y1));
        lines.add(new Line(x1, y0, x1, y1));
        x0 = in.nextLong();
        y0 = in.nextLong();
        x1 = in.nextLong();
        y1 = in.nextLong();
        lines.add(new Line(x0, y0, x1, y0));
        lines.add(new Line(x0, y1, x1, y1));
        lines.add(new Line(x0, y0, x0, y1));
        lines.add(new Line(x1, y0, x1, y1));

        for (Line a : lines) {
            for (Line b : lines) {
                intersect(a, b, points);
            }
        }
        Point start = new Point(in.nextLong(), in.nextLong());
        Point finish = new Point(in.nextLong(), in.nextLong());
        points.add(start);
        points.add(finish);

        HashMap<Point, HashMap<Point, Long>> map = new HashMap<Point, HashMap<Point, Long>>();
        for (Point a : points) {
            map.put(a, new HashMap<Point, Long>());
            for (Point b : points) {
                map.get(a).put(b, Long.MAX_VALUE);
            }
        }

        for (Point a : points) {
            for (Point b : points) {
                for (Line l : lines) {
                    if (l.contains(a) && l.contains(b)) {
                        map.get(a).put(b, distance(a, b));
                    }
                }
            }
        }

        for (int i = 0; i < points.size(); i++) {
            for (Point a : points) {
                for (Point b : points) {
                    for (Point c : points) {
                        if (map.get(a).get(c) < Long.MAX_VALUE && map.get(c).get(b) < Long.MAX_VALUE) {
                            long res = map.get(a).get(b);
                            res = Math.min(res, map.get(a).get(c) + map.get(c).get(b));
                            map.get(a).put(b, res);
                            map.get(b).put(a, res);
                        }
                    }
                }
            }
        }

        out.println(map.get(start).get(finish) == Long.MAX_VALUE ? -1 : map.get(start).get(finish));
    }

    long distance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    void intersect(Line a, Line b, HashSet<Point> points) {
        ArrayList<Long> X = new ArrayList<Long>();
        ArrayList<Long> Y = new ArrayList<Long>();
        X.add(a.x0);
        X.add(a.x1);
        X.add(b.x0);
        X.add(b.x1);
        Y.add(a.y0);
        Y.add(a.y1);
        Y.add(b.y0);
        Y.add(b.y1);

        for (long x : X) {
            for (long y : Y) {
                Point p = new Point(x, y);
                if (a.contains(p) && b.contains(p)) {
                    points.add(p);
                }
            }
        }
    }
}

class Line {
    long x0, y0, x1, y1;

    Line(long x0, long y0, long x1, long y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (x0 != line.x0) return false;
        if (x1 != line.x1) return false;
        if (y0 != line.y0) return false;
        if (y1 != line.y1) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (x0 ^ (x0 >>> 32));
        result = 31 * result + (int) (y0 ^ (y0 >>> 32));
        result = 31 * result + (int) (x1 ^ (x1 >>> 32));
        result = 31 * result + (int) (y1 ^ (y1 >>> 32));
        return result;
    }

    boolean contains(Point a) {
        if (x0 == x1) {
            return a.x == x0 && a.y >= Math.min(y0, y1) && a.y <= Math.max(y0, y1);
        }
        if (y0 == y1) {
            return a.y == y0 && a.x >= Math.min(x0, x1) && a.x <= Math.max(x0, x1);
        }
        return false;
    }
}

class Point {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }
}