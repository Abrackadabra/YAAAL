package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Threed {
    int getMin(ArrayList<Integer> a) {
        int res = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < res) {
                res = a.get(i);
            }
        }
        return res;
    }

    int getMax(ArrayList<Integer> a) {
        int res = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) > res) {
                res = a.get(i);
            }
        }
        return res;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        ArrayList<Integer> x0 = new ArrayList<Integer>();
        ArrayList<Integer> x1 = new ArrayList<Integer>();
        ArrayList<Integer> y0 = new ArrayList<Integer>();
        ArrayList<Integer> y1 = new ArrayList<Integer>();
        ArrayList<Integer> z0 = new ArrayList<Integer>();
        ArrayList<Integer> z1 = new ArrayList<Integer>();

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            x0.add(in.nextInt());
            y0.add(in.nextInt());
        }
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            z0.add(in.nextInt());
            y1.add(in.nextInt());
        }
        n = in.nextInt();
        for (int i = 0; i < n; i++) {
            z1.add(in.nextInt());
            x1.add(in.nextInt());
        }

        if (getMin(x0) != getMin(x1) || getMax(x0) != getMax(x1) ||
            getMin(y0) != getMin(y1) || getMax(y0) != getMax(y1) ||
            getMin(z0) != getMin(z1) || getMax(z0) != getMax(z1) ) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }
}
