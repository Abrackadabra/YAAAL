package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        boolean[][] a = new boolean[n][m];
        boolean[][] b = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = in.nextString();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j) == '#';
            }
        }

        for (int i = 0; i < n; i++) {
            String s = in.nextString();
            for (int j = 0; j < m; j++) {
                b[i][j] = s.charAt(j) == '#';
            }
        }

        boolean[][] c = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] || b[i][j];
            }
        }

        if (calc(a) == calc(b) && calc(a) == calc(c) && calc(a) != -1) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    int calc(boolean[][] a) {
        int n = a.length;
        int m = a[0].length;

        int[][] d = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], -1);
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(0);

        d[0][0] = 0;

        while (!queue.isEmpty()) {
            int t = queue.poll();
            int x = t / m;
            int y = t % m;

            for (int[] direction : directions) {
                int xx = x + direction[0];
                int yy = y + direction[1];

                if (xx < 0 || yy < 0 || xx >= n || yy >= m || d[xx][yy] != -1 || a[xx][yy]) {
                    continue;
                }

                d[xx][yy] = d[x][y] + 1;
                queue.add(xx * m + yy);
            }
        }

        return d[n - 1][m - 1];
    }

    int[][] directions = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1},
    };
}
