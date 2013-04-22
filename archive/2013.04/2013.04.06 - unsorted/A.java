package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        List<Set<String>> all = new ArrayList<Set<String>>();
        List<Set<String>> used = new ArrayList<Set<String>>();

        for (int i = 0; i < 3; i++) {
            all.add(new HashSet<String>());
            used.add(new HashSet<String>());
        }

        for (int i = 0; i < n; i++) {
            String a = in.nextString();
            String b = in.nextString();
            String c = in.nextString();
            String x = in.nextString();

            all.get(0).add(a);
            all.get(1).add(b);
            all.get(2).add(c);

            if (x.equals(a))
                used.get(0).add(x);
            if (x.equals(b))
                used.get(1).add(x);
            if (x.equals(c))
                used.get(2).add(x);

            for (int j = 0; j < 3; j++) {
                out.print(all.get(j).size() - used.get(j).size());
                out.print(" ");
            }
            out.println();
        }
    }
}
