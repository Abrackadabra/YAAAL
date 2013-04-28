package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        d = in.nextInt();

        if (n == 0 && d == 0) {
            throw new UnknownError();
        }

        a = in.nextIntArray(n);
        b = a.clone();

        Arrays.sort(b);

        set = new TreeSet<Line>();
        setByFrom = new TreeSet<Line>();

        Map<Integer, Integer> sorted = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            sorted.put(b[i], i);
        }
        for (int i = 0; i < n; i++) {
            a[i] = sorted.get(a[i]);
            b[a[i]] = i;
        }

        if (b[0] > b[n - 1]) {
            for (int i = 0; i < n; i++) {
                a[i] = n - 1 - a[i];
                b[a[i]] = i;
            }
        }

        for (int i = 1; i < n; i++) {
            int dist = Math.abs(b[i] - b[i - 1]);
            if (dist > d) {
                out.println(-1);
                return;
            }
        }

        x = new int[n];

        for (int i = b[0]; i >= 0; i--) {
            x[i] = i - b[0] - 1;
        }
        for (int i = b[0] + 1; i < n; i++) {
            x[i] = d * (i - b[0] + 1) - 1;
        }
        for (int i = b[n - 1] + 1; i < n; i++) {
            x[i] = x[i - 1] + 1;
        }

        for (int i = 1; i < n; i++) {
            int left = Math.min(b[i], b[i - 1]);
            int right = Math.max(b[i], b[i - 1]);

            int val = x[left] + d;

            run(right, 0, val);
        }

        out.println(x[b[n - 1]] - x[b[0]]);

        //out.println(Arrays.toString(x));
    }

    int[] x, a, b;
    int n, d;

    SortedSet<Line> set = new TreeSet<Line>();
    SortedSet<Line> setByFrom = new TreeSet<Line>(
            new Comparator<Line>() {
                @Override
                public int compare(Line o1, Line o2) {
                    if (o1.from != o2.from) {
                        return Integer.compare(o1.from, o2.from);
                    }
                    return Integer.compare(o1.val, o2.val);
                }
            }
    );

    void run(int from, int to, int val) {
        while (!setByFrom.isEmpty()) {
            Line line = setByFrom.first();
            if (line.from > from) {
                break;
            }
            setByFrom.remove(line);
            set.remove(line);
        }

        for (int i = from; i >= to; i--) {
            int toSet = val - (from - i);
            if (toSet < x[i]) {
                x[i] = toSet;

                if (a[i] != n - 1) {
                    int t = b[a[i] + 1];
                    if (t > from) {
                        int v = x[i] + d - t;
                        Line line = new Line(v, t);
                        set.add(line);
                        setByFrom.add(line);
                    }
                }

                if (a[i] != 0) {
                    int t = b[a[i] - 1];
                    if (t > from) {
                        int v = x[i] + d - t;
                        Line line = new Line(v, t);
                        set.add(line);
                        setByFrom.add(line);
                    }
                }
            }
        }

        if (!set.isEmpty()) {
            Line line = set.first();
            run(line.from, from + 1, line.val + line.from);
        }
    }
}

class Line implements Comparable<Line> {
    int val, from;

    @Override
    public int compareTo(Line o) {
        if (val != o.val) {
            return Integer.compare(val, o.val);
        }
        return -Integer.compare(from, o.from);
    }

    Line(int val, int from) {
        this.val = val;
        this.from = from;
    }
}
