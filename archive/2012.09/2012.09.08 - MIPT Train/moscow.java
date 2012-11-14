package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class moscow {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] radius = new int[n];
        int[] type = new int[n];

        for (int i = 0; i < n; i++) {
            radius[i] = in.nextInt();
            type[i] = in.nextInt();
        }

        double[] angles = new double[m];

        for (int i = 0; i < m; i++) {
            angles[i] = in.nextDouble() / 360e6 * 2 * Math.PI;
        }

        int a = in.nextInt() - 1;
        double aAngle = angles[in.nextInt() - 1];
        int b = in.nextInt() - 1;
        double bAngle = angles[in.nextInt() - 1];

        double angleToLeft = aAngle - bAngle;
        while (angleToLeft < 0) angleToLeft += 2 * Math.PI;
        double angleToRight = bAngle - aAngle;
        while (angleToRight < 0) angleToRight += 2 * Math.PI;

        double min = 1e99;

        for (int i = 0; i < n; i++) {
            if (type[i] <= 0) {
                double res = 0.0;
                res += Math.abs(radius[a] - radius[i]);
                res += Math.abs(radius[b] - radius[i]);

                res += angleToLeft * radius[i];

                min = Math.min(min, res);
            }
            if (type[i] >= 0) {
                double res = 0.0;
                res += Math.abs(radius[a] - radius[i]);
                res += Math.abs(radius[b] - radius[i]);

                res += angleToRight * radius[i];

                min = Math.min(min, res);
            }
        }

        out.println(min);
	}
}
