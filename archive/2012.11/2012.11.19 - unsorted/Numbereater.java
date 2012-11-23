package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Numbereater {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        HashSet<HashSet<Integer>> set = new HashSet<HashSet<Integer>>();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                HashSet<Integer> s = new HashSet<Integer>();
                for (int k = i; k <= j; k++) {
                    s.add(a[k]);
                }
                set.add(s);
            }
        }

        out.println(set.size());
	}
}
