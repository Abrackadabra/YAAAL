package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class C {
    Random random = new Random();

	public void solve(int testNumber, Scanner ___in, PrintWriter ____out) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (true) {
            int n = in.nextInt();
            if (n == -1) break;
            game(n, in, out);
        }
	}

    void game(int n, Scanner in, PrintWriter out) {
        int wins = 0, loses = 0;
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 1; i <= n; i++)
            players.add(new Player(i));
        int mission = 0;
        while (true) {
            Collections.shuffle(players, random);
            Collections.sort(players);
            int peopleToMission = in.nextInt();
            for (int i = 0; i < peopleToMission; i++) {
                out.print(players.get(players.size() - 1 - i).index + " ");
            }
            out.println();
            out.flush();

            String ans = in.next();
            if (ans.equals("WIN") || ans.equals("LOSE")) return;
            int sabs = Integer.parseInt(ans);
            if (sabs >= sabotagesToLose[n - 5][mission]) {
                loses++;
                for (int i = 0; i < peopleToMission; i++) {
                    players.get(players.size() - 1 - i).loses++;
                    if (sabs == peopleToMission) {
                        players.get(players.size() - 1 - i).wins -= 100;
                    } else if (sabs == 2) {
                        players.get(players.size() - 1 - i).wins -= 100;
                    }
                }
            } else {
                wins++;
                for (int i = 0; i < peopleToMission; i++)
                    players.get(players.size() - 1 - i).wins++;
            }
        }
    }

    int[] friends = {3, 4, 4, 5};
    int[] foes = {2, 2, 3, 3};
    int[][] peopleToMission = {
            {2, 3, 2, 3, 3},
            {2, 3, 4, 3, 4},
            {2, 3, 3, 4, 4},
            {3, 4, 4, 5, 5},
    };
    int[][] sabotagesToLose = {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 2, 1},
            {1, 1, 2, 2, 1},
    };
}

class Player implements Comparable<Player> {
    int wins = 0, loses = 0;

    int index;

    Player(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Player o) {
        return (int) (((wins + 1d) / (loses + 1) - (o.wins + 1d) / (o.loses + 1)) * 1000);
    }
}
