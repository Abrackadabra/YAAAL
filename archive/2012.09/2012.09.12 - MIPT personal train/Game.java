package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int crosses = 0;
        int ouths = 0;

        int[][] a = new int[3][3];
        for (int i = 0; i < 9; i++) {
            a[i / 3][i % 3] = in.nextInt();
            if (a[i / 3][i % 3] == 1) crosses++;
            if (a[i / 3][i % 3] == 2) ouths++;
        }

        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            String s = "";
            for (int j = 0; j < 3; j++) {
                s += a[i][j];
            }
            strings.add(s);
            s = "";
            for (int j = 0; j < 3; j++) {
                s += a[j][i];
            }
            strings.add(s);
        }

        String s = "";
        for (int i = 0; i < 3; i++) {
            s += a[i][i];
        }
        strings.add(s);
        s = "";
        for (int i = 0; i < 3; i++) {
            s += a[i][2 - i];
        }
        strings.add(s);

        boolean crossesWin = false;
        boolean outhsWin = false;

        for (String t : strings) {
            if (t.equals("111")) crossesWin = true;
            if (t.equals("222")) outhsWin = true;
        }

        if ((crossesWin && outhsWin) || ouths > crosses || crosses - ouths > 1 || (crossesWin && crosses == ouths) || (outhsWin && crosses == ouths + 1)) {
            out.println("Impossible");
            return;
        }

        if (crossesWin || outhsWin || crosses + ouths == 9) {
            out.println("The end of game");
            return;
        }

        if (crosses == ouths) {
            out.println("Crosses' move");
            return;
        }

        out.println("Ouths' move");
    }
}
