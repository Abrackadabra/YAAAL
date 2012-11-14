package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Lucky {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(in.nextInt());
        a.add(in.nextInt());
        a.add(in.nextInt());
        a.add(in.nextInt());
        a.add(in.nextInt());
        a.add(in.nextInt());

        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                for (int k = j + 1; k < 6; k++) {
                    int sum0 = 0;
                    sum0 += a.get(i) + a.get(j) + a.get(k);

                    int sum1 = 0;
                    for (int l = 0; l < 6; l++) {
                        if (l != i && l != k && l != j)
                            sum1 += a.get(l);
                    }
                    if (sum0 == sum1) {
                        out.print(a.get(i) + "" + a.get(j) + "" + a.get(k));
                        for (int l = 0; l < 6; l++) {
                            if (l != i && l != k && l != j)
                                out.print(a.get(l));
                        }
                        return;
                    }
                }
            }
        }

        out.println(-1);
	}
}
