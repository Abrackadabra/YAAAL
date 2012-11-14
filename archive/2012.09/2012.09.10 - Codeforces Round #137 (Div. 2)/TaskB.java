package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        int k = in.nextInt();

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++)
            a[i] = in.nextIntArray(m);

        int[] realRow = new int[n];
        for (int i = 0; i < n; i++)
            realRow[i] = i;

        int[] realColumn = new int[m];
        for (int i = 0; i < m; i++)
            realColumn[i] = i;

        for (int i = 0; i < k; i++) {
            String s = in.next();
            if (s.equals("c")) {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                int t = realColumn[x];
                realColumn[x] = realColumn[y];
                realColumn[y] = t;
            }
            if (s.equals("r")) {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                int t = realRow[x];
                realRow[x] = realRow[y];
                realRow[y] = t;
            }
            if (s.equals("g")) {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                out.println(a[realRow[x]][realColumn[y]]);
            }
        }
	}
}
