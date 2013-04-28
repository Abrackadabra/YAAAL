package chelper;



import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class EGen {
	public void solve(int testNumber, InputReader in, OutputWriter cout) {
        OutputWriter out = OutputWriter.openFile("input.txt");

        int tests = 500;
        out.println(tests);
        for (int test = 0; test < tests; test++) {
            int n = 500;
            out.println(n);
            for (int i = 0; i < n; i++) {
                out.println(getString(i) + " = aaaaa");
            }
            out.println("AA");
            for (int i = 0; i < 2000; i++) {
                out.print('a');
            }
            out.println();
        }

        out.flush();
	}

    String getString(int id) {
        return "" + (char) ('A' + id / 26) + (char) ('A' + id % 26);
    }
}
