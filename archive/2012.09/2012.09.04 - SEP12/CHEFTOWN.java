package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.TreeSet;

public class CHEFTOWN {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), w = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        int l = 0, r = 0;
        while (r - l + 1 < w) {
            treeSet.add(a[r++]);
        }
        int ans = 0;
        while (true) {

        }
	}
}
