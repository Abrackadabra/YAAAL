package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.math.BigInteger;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        BigInteger x = new BigInteger(in.nextString());
        x = x.add(BigInteger.ONE);
        out.println(x);
    }
}
