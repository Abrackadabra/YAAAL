package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import abrackadabra.math.AMath;

public class Grasshopper {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int g = AMath.gcd(a, AMath.gcd(b, c));

        if (a == 0 && b == 0 && c == 0) {
            out.println(0);
            return;
        }

        double ans = 1.0 / g;
        if (a >= 0 && b >= 0 && c >= 0) {
            ans /= 2;
        }
        if (a <= 0 && b <= 0 && c <= 0) {
            ans /= 2;
        }

        out.println(ans);


    }
}
