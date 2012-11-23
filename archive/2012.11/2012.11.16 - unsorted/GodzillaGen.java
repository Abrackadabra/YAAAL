package chelper;

import abrackadabra.io.InputReader;

import java.io.*;
import java.util.*;

public class GodzillaGen {
    public void solve(int testNumber, InputReader in, PrintWriter qout) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("input.txt"));

            int n = 100000, m = 100000;

            out.println(n + " " + m);

            HashSet<Long> used = new HashSet<Long>();

            Random random = new Random();

            for (int i = 0; i < m; i++) {
                while (true) {
                    int a = random.nextInt(n);
                    int b = random.nextInt(n);

                    if (a == b || used.contains((long) a * n + b)) {
                        continue;
                    }

                    out.println((a + 1) + " " + (b + 1));
                    used.add((long) a * n + b);
                    break;
                }
            }
            int k = m;
            out.println(k);
            for (int i = 0; i < k; i++) {
                out.println(i + 1);
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
