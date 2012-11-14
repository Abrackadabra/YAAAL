package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class cafe {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();

        int[][] a = new int[n][m];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int x = 1 + i * 3;
                int y = 1 + j * 3;

                if (x < n && y < m) a[x][y] = 1;
            }
        }

        if ((n - 1) % 3 == 0) {
            for (int i = 0; i < 100; i++) {
                int x = n - 1;
                int y = 1 + i * 3;
                if (y < m) a[x][y] = 1;
            }
        }

        if ((m - 1) % 3 == 0) {
            for (int j = 0; j < 100; j++) {
                int x = 1 + j * 3;
                int y = m - 1;
                if (x < n) a[x][y] = 1;
            }
        }

        for (int i = 0; i < n && k > 0; i++) {
            for (int j = 0; j < m && k > 0; j++) {
                if (a[i][j] == 0) {
                    boolean ok = false;
                    for (int[] dir : dirs) {
                        int xx = i + dir[0];
                        int yy = j + dir[1];

                        if (xx < n && xx >= 0 && yy < m && yy >= 0) {
                            if (a[xx][yy] == 1) ok = true;
                        }
                    }
                    if (ok) {
                        k--;
                        a[i][j] = 2;
                    }
                }
            }
        }

        if (k > 0) {
            out.println("Impossible");
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0) out.print(".");
                if (a[i][j] == 1) out.print("T");
                if (a[i][j] == 2) out.print("h");
            }
            out.println();
        }
	}

    int[][] dirs = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
            {1, 1},
            {-1, 1},
            {-1, -1},
            {1, -1},
    };
}
