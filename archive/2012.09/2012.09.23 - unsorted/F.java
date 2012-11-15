package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;

public class F {
    int x = 35, y = 35;
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
        while (true) {
            Area area = new Area();
            area.map[x][y] = Cell.EMPTY;
            area.explore();
        }
    }

    class Area {
        Cell[][] map = new Cell[70][70];

        Area() {
            for (int i = 0; i < 70 * 70; i++)
                map[i / 70][i % 70] = Cell.TERRA_INCOGNITA;
        }

        void explore() {
            //qrqdfs();
        }

        void dfs(int x, int y) {

        }
    }

    static enum Cell {
        TERRA_INCOGNITA,
        EMPTY,
        WALL,
        TELEPORT,
        STONE,
        EMPTY_OR_TELEPORT
    }

    Cell command(String s) {
        out.println(s);
        if (s.contains("DONE")) return Cell.EMPTY;
        String ans = in.nextLine();
        if (ans.charAt(0) == 'W') return Cell.WALL;
        if (ans.charAt(0) == 'C') return Cell.STONE;
        if (ans.charAt(0) == 'E') return Cell.EMPTY;
        return Cell.EMPTY;
    }

    int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    String[] directionNames = {
            "UP",
            "RIGHT",
            "DOWN",
            "LEFT"
    };
}


