package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.io.File;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        File file = new File("/home/abra/Temp/runs_temp");
        for (String s : file.list()) {
            if (!s.contains(".cpp")) continue;

            in = new InputReader("/home/abra/Temp/runs_temp/" + s);
            String q = in.readEverything();
            q = q.replaceAll("public", "private");
            out = new OutputWriter("/home/abra/Temp/runs_temp/" + s);
            out.println(q);
            out.flush();
        }
    }
}
