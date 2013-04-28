package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m * 2];

        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            for (int j = 0; j < m; j++) {
                int x = i;
                int y = j;
                char c = s.charAt(j);

                if (c == '*') {
                    a[x][y] = -1;
                } else {
                    a[x][y] = Integer.MAX_VALUE;
                }

                if (c == 'D') {
                    startX = x;
                    startY = y;
                }

                if (c == 'K') {
                    endX = x;
                    endY = y;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            for (int j = 0; j < m; j++) {
                int x = n - i - 1;
                int y = (m - j - 1) + m;
                char c = s.charAt(j);

                if (c == '*') {
                    a[x][y] = -1;
                } else {
                    a[x][y] = Integer.MAX_VALUE;
                }

                if (c == 'D') {
                    startX = x;
                    startY = y;
                }

                if (c == 'K') {
                    endX = x;
                    endY = y;
                }
            }
        }

        m *= 2;

        a[startX][startY] = 0;

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(startX * m + startY);

        while (!queue.isEmpty()) {
            int t = queue.poll();
            int x = t / m;
            int y = t % m;

            for (int[] direction : directions) {
                int xx = x + direction[0];
                int yy = (y + direction[1] + m) % m;

                if (xx < 0 || xx >= n) {
                    continue;
                }

                if (a[xx][yy] > a[x][y] + 1) {
                    a[xx][yy] = a[x][y] + 1;
                    queue.add(xx * m + yy);
                }
            }
        }

        out.println(a[endX][endY]);
    }

    int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };
}
