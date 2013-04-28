package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;

public class G {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            int[][] map = new int[200][200];

            int v = in.nextInt();
            int[] vam = new int[v * 2];
            int o = in.nextInt();
            int m = in.nextInt();

            if (v + o + m == 0) break;

            for (int i = 0; i < v; i++) {
                int x = in.nextInt();
                int y = in.nextInt();

                map[x][y] = 1;

                vam[i * 2] = x;
                vam[i * 2 + 1] = y;
            }

            for (int i = 0; i < o; i++) {
                int x = in.nextInt();
                int y = in.nextInt();

                map[x][y] = 2;
            }

            for (int i = 0; i < m; i++) {
                String dir = in.nextString();

                int d = 3;
                if (dir.charAt(0) == 'E') d = 3;
                if (dir.charAt(0) == 'N') d = 4;
                if (dir.charAt(0) == 'S') d = 5;
                if (dir.charAt(0) == 'W') d = 6;

                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();

                if (x1 > x2) {
                    int t = x1;
                    x1 = x2;
                    x2 = t;
                }

                if (y1 > y2) {
                    int t = y1;
                    y1 = y2;
                    y2 = t;
                }

                for (int x = x1; x <= x2; x++) {
                    map[x][y1] = d;
                }

                for (int y = y1; y <= y2; y++) {
                    map[x1][y] = d;
                }
            }

            out.println("Case " + testNumber + ":");

            boolean none = true;

            for (int i = 0; i < v; i++) {
                int x = vam[i * 2];
                int y = vam[i * 2 + 1];

                ArrayList<String> dangers = new ArrayList<String>();

                for (int dir = 0; dir < 4; dir++) {
                    int[] d = directions[dir];

                    for (int j = 1; true; j++) {
                        int xx = x + d[0] * j;
                        int yy = y + d[1] * j;

                        if (xx < 0 || yy < 0 || xx > 150 || yy > 150) {
                            break;
                        }

                        if (map[xx][yy] == 2) {
                            break;
                        }

                        if (map[xx][yy] >= 3) {
                            if (map[xx][yy] - 3 == 3 - dir) {
                                dangers.add(directionNames[dir]);
                            }
                            break;
                        }
                    }
                }

                if (dangers.size() == 0) continue;

                out.print("vampire " + (i + 1) + " ");

                out.printSeparated(dangers.toArray());
                out.println();

                none = false;
            }

            if (none) {
                out.println("none");
            }

            testNumber++;
        }
    }

    int[][] directions = {
            {1, 0}, //east
            {0, 1}, //north
            {0, -1},//south
            {-1, 0},//west
    };

    String[] directionNames = {
        "east",
        "north",
        "south",
        "west",
    };
}
