package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;

public class H {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        ArrayList<Double> a = get(in);
        ArrayList<Double> b = get(in);

        boolean ok = true;

        for (int i = 0; i < 3; i++) {
            if (Math.abs(a.get(i) - b.get(i)) > eps) {
                ok = false;
            }
        }

        out.println(ok ? "YES" : "NO");
    }

    double eps = 1e-9;

    ArrayList <Double> get(InputReader in) {
        int[] x = new int[3];
        int[] y = new int[3];

        for (int i = 0; i < 3; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        double a = Math.hypot(x[0] - x[1], y[0] - y[1]);
        double b = Math.hypot(x[0] - x[2], y[0] - y[2]);
        double c = Math.hypot(x[2] - x[1], y[2] - y[1]);

        ArrayList<Double> res = new ArrayList<Double>();
        res.add(a);
        res.add(b);
        res.add(c);

        Collections.sort(res);

        for (int i = 2; i >= 0; i--) {
            res.set(i, res.get(i) / res.get(0));
        }

        return res;
    }
}
