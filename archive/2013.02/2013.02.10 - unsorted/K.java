package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class K {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.println(in.nextString().replace("R", "").length());
    }
}
