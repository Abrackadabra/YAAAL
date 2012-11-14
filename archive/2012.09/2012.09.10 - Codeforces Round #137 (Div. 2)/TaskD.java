package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            a.add(t);
        }
        ArrayList<Integer> b = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            b.add(t);
        }

        int ans = 0;

        Collections.sort(a);
        Collections.sort(b);

        int j = 0;

        for (int i = n - 1; i >= 0; i--) {
            while (j < n && a.get(i) + b.get(j) < x)
                j++;
            if (j == n) break;
            j++;
            ans++;
        }


        out.println("1 " + ans);
	}
}
