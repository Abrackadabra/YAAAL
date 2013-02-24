package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class I {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double m1 = in.nextDouble();
        double m2 = in.nextDouble();
        double dx1 = in.nextDouble();
        double dy1 = in.nextDouble();
        double px = in.nextDouble();
        double py = in.nextDouble();

        double x = (px - dx1) / m1;
        double y = (py - dy1) / m1;

        double dx2 = px - x * m2;
        double dy2 = py - y * m2;

        out.println(dx2 + " " + dy2);
    }
}
