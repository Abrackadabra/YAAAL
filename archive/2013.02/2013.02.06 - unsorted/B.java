package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.structure.Polynomial;

import java.math.BigInteger;
import java.util.*;

public class B {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Polynomial polynomial = new Polynomial(in.nextLine());

        int res = 0;

        for (int i = -10000; i <= 10000; i++) {
            if (polynomial.isRoot(i)) {
                res++;
            }
        }

        out.println(res > 100 ? "inf" : res);

        //System.out.println(polynomial);
    }
}
