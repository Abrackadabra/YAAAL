package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class Perimeter {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int N = 102;

        boolean[][] field = new boolean[N][N];

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            field[x][y] = true;
        }

        boolean[][] visited = new boolean[N][N];

        int answer = 0;

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(0);
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int t = queue.poll();
            int x = t / N;
            int y = t % N;

            for (int[] direction : directions) {
                int xx = x + direction[0];
                int yy = y + direction[1];

                if (xx < 0 || xx >= N || yy < 0 || yy >= N || visited[xx][yy]) {
                    continue;
                }

                if (field[xx][yy]) {
                    answer++;
                    continue;
                }

                visited[xx][yy] = true;

                queue.add(xx * N + yy);
            }
        }

        out.println(answer);
    }

    int[][] directions = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };
}
