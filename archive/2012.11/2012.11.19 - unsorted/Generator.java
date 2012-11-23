package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Generator {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = 100000;
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(1);
        }
        for (int i = n; i < 2 * n; i++) {
            a.add(2);
        }
        Collections.shuffle(a);
        out.println(100000);
        for (int i : a)
            out.print(i + " ");
	}
}
