package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class cube {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        ArrayList<int[]> coords = new ArrayList<int[]>();
        ArrayList<String> names = new ArrayList<String>();
        coords.add(new int[]{0, 0, 0});
        names.add("A");
        coords.add(new int[]{0, 1, 0});
        names.add("B");
        coords.add(new int[]{1, 1, 0});
        names.add("C");
        coords.add(new int[]{1, 0, 0});
        names.add("D");
        coords.add(new int[]{0, 0, 1});
        names.add("A1");
        coords.add(new int[]{0, 1, 1});
        names.add("B1");
        coords.add(new int[]{1, 1, 1});
        names.add("C1");
        coords.add(new int[]{1, 0, 1});
        names.add("D1");

        HashMap<String, int[]> inputs = new HashMap<String, int[]>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String inp = names.get(i) + names.get(j);
                inputs.put(inp, new int[]{i, j});
            }
        }

        String s = in.nextString();
        String w = in.nextString();

        int a = inputs.get(s)[0];
        int b = inputs.get(s)[1];
        int c = inputs.get(w)[0];
        int d = inputs.get(w)[1];

        double eps = 1e-7;

        for (double x = 0.0; x <= 1.0; x += 0.5) {
            for (double y = 0.0; y <= 1.0; y += 0.5) {
                for (double z = 0.0; z <= 1.0; z += 0.5) {
                    boolean ok1 = false;
                    boolean ok2 = false;

                    int[] aa = coords.get(a);
                    int[] bb = coords.get(b);
                    int[] cc = coords.get(c);
                    int[] dd = coords.get(d);

                    double al = pif3d(x, y, z, aa[0], aa[1], aa[2]);
                    double bl = pif3d(x, y, z, bb[0], bb[1], bb[2]);
                    double cl = pif3d(x, y, z, cc[0], cc[1], cc[2]);
                    double dl = pif3d(x, y, z, dd[0], dd[1], dd[2]);

                    if (al < eps || bl < eps) ok1 = true;
                    if (cl < eps || dl < eps) ok2 = true;

                    double dx = 2 * x - aa[0];
                    double dy = 2 * y - aa[1];
                    double dz = 2 * z - aa[2];

                    if (pif3d(bb[0], bb[1], bb[2], dx, dy, dz) < eps) ok1 = true;

                    dx = 2 * x - cc[0];
                    dy = 2 * y - cc[1];
                    dz = 2 * z - cc[2];

                    if (pif3d(dd[0], dd[1], dd[2], dx, dy, dz) < eps) ok2 = true;

                    if (ok1 && ok2) {
                        out.println("Yes");
                        return;
                    }
                }
            }
        }

        out.println("No");
    }

    double pif3d(double x, double y, double z, double x1, double y1, double z1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1) + (z - z1) * (z - z1));
    }
}
