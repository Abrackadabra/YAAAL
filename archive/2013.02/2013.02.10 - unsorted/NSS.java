package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Random;

public class NSS {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long good = 0, total = 0;

        Random random = new Random();

        while (true) {
            int prev = random.nextInt(11);

            boolean ok = true;

            for (int i = 0; i < 4; i++) {
                int t = prev;

                while (t == prev) {
                    t = random.nextInt(11);
                }

                if ((prev >= 5 && t < prev) || (prev < 5 && t > prev)) {

                } else {
                    ok = false;
                    break;
                }

                prev = t;
            }

            if (ok) {
                good++;
            }
            total++;

            System.out.println(good * 1e2 / total);
        }
    }
}
