package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String[] presidents = {
                "Washington",
                "Adams",
                "Jefferson",
                "Madison",
                "Monroe",
                "Adams",
                "Jackson",
                "Van Buren",
                "Harrison",
                "Tyler",
                "Polk",
                "Taylor",
                "Fillmore",
                "Pierce",
                "Buchanan",
                "Lincoln",
                "Johnson",
                "Grant",
                "Hayes",
                "Garfield",
                "Arthur",
                "Cleveland",
                "Harrison",
                "Cleveland",
                "McKinley",
                "Roosevelt",
                "Taft",
                "Wilson",
                "Harding",
                "Coolidge",
                "Hoover",
                "Roosevelt",
                "Truman",
                "Eisenhower",
                "Kennedy",
                "Johnson",
                "Nixon",
                "Ford",
                "Carter",
                "Reagan",
        };

        int n = in.nextInt() - 1;
        out.println(presidents[n]);
    }
}
