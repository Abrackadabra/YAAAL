package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int tux = in.nextInt();
        int foo = 0;
        int bar = 0;
        int baz = 0;
        int quz = 1;

        for (int i = 0; i < tux; i++) {
            int pur = in.nextInt();

            foo += pur;
            bar++;

            if (Math.max(foo * quz, bar * baz) == foo * quz) {
                baz = foo;
                quz = bar;
            }
        }

        out.println(1d * baz / quz);
    }
}
