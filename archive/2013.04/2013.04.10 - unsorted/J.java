package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.math.BigInteger;

public class J {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        BigInteger x = new BigInteger(in.nextString());
        x = x.multiply(BigInteger.valueOf(8560));

        out.println(x);
    }
}
