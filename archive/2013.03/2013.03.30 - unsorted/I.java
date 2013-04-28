package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class I {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        if(x1 == 0 && y1 ==0 && x2 == 0 && y2 ==0) {
            throw new UnknownError();
        }

        Line2D.Double l1 = new Line2D.Double(x1, y1, x2, y2);

        Point2D.Double a = new Point2D.Double(in.nextInt(), in.nextInt());
        Point2D.Double b = new Point2D.Double(in.nextInt(), in.nextInt());

        Line2D.Double l2 = new Line2D.Double(a, b);
        out.print("Case " + testNumber + ": ");
        if(l1.intersectsLine(l2)) {
            out.printf("%.3f\n", Math.min(
               a.distance(x1, y1) + a.distance(x2, y2),
               b.distance(x1, y1) + b.distance(x2, y2)
                              ) / 2);
        }
        else {
            out.printf("%.3f\n", new Point2D.Double(x1, y1).distance(x2, y2) / 2);
        }

    }
}
