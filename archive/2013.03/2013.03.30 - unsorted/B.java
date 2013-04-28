package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

public class B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int s = in.nextInt();
        int c = in.nextInt();
        int p = in.nextInt();
        int l = in.nextInt();
        if(s == 0)
            throw new UnknownError();
        out.print("Case " + testNumber + ": ");
        int ans = (s - p) % s;
        l += (s - p) % s;

        int g = AMath.gcd(s, c);

        int r = l % c;

        if(l % g != 0){
            out.println("Never");
            return;
        }

        for(int first = 0; ; ++first){
            if((r + first * s) % (c) == 0){
                out.print(first);
                out.println(" " + ans + "/" + s);
                return;
            }

        }
    }
}
