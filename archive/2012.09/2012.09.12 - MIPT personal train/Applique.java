package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Applique {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int X = in.nextInt(), Y = in.nextInt();
        HashSet<Integer> prohibited = new HashSet<Integer>();

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int x0 = in.nextInt();
            int y0 = in.nextInt();
            int x1 = in.nextInt();
            int y1 = in.nextInt();


            if (x0 == x1) {
                if (y0 > y1) {
                    int t = y0;
                    y0 = y1;
                    y1 = t;
                }

                for (int y = y0; y < y1; y++) {
                    prohibited.add(getCode(x0 - 1, y, x0, y));
                    prohibited.add(getCode(x0, y, x0 - 1, y));
                }
            }

            if (y0 == y1) {
                if (x0 > x1) {
                    int t = x0;
                    x0 = x1;
                    x1 = t;
                }

                for (int x = x0; x < x1; x++) {
                    prohibited.add(getCode(x, y0 - 1, x, y0));
                    prohibited.add(getCode(x, y0, x, y0 - 1));
                }
            }
        }

        boolean[][] visited = new boolean[X][Y];


        int res = 0;
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (visited[i][j])
                    continue;

                res++;

                Queue<Integer> queue = new LinkedList<Integer>();
                queue.add(i + 50 * j);
                while (!queue.isEmpty()) {
                    int t = queue.poll();
                    int x = t % 50;
                    int y = t / 50;

                    for (int[] dir : dirs) {
                        int xx = x + dir[0];
                        int yy = y + dir[1];

                        if (xx < 0 || xx >= X || yy < 0 || yy >= Y || visited[xx][yy] || prohibited.contains(getCode(x, y, xx, yy)))
                            continue;

                        visited[xx][yy] = true;
                        queue.add(xx + 50 * yy);
                    }
                }
            }
        }
        out.println(res);
	}

    int[][] dirs = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1},
    };

    int getCode(int x0, int y0, int x1, int y1) {
        return x0 + 50 * y0 + 50 * 50 * x1 + 50 * 50 * 50 * y1;
    }
}
