package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Game {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] win = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                win[i][j] = in.nextInt() == 1;
            }
        }
        int[] conf = new int[m];
        for (int i = 0; i < m; i++) {
            conf[i] = in.nextInt() - 1;
        }
        TreeSet<Integer> winners = new TreeSet<Integer>();

        for (int i = 0; i < m; i++) {
            boolean won = false, loser = false;
            for (int j = 0; j < m; j++) {
                if (i == j) continue;
                if (win[conf[i]][conf[j]]) {
                    won = true;
                }
                if (win[conf[j]][conf[i]]) {
                    loser = true;
                }
            }
            if (won && !loser) {
                winners.add(i + 1);
            }
        }
        if (winners.isEmpty()) {
            out.println(-1);
        } else {
            for (int i : winners) {
                out.print(i + " ");
            }
        }
	}
}
