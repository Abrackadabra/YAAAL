package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class I {
    Random random = new Random();

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        Scanner min = new Scanner(System.in);

        ArrayList<Integer> commands = new ArrayList<Integer>();
        ArrayList<boolean[]> answers = new ArrayList<boolean[]>();
        int lastSwap = 0;
        int steps = 9000;
        for (int i = 0; i < steps; i++) {
            String s = min.nextLine();
            boolean[] a = new boolean[5];
            ArrayList<Integer> black = new ArrayList<Integer>();
            ArrayList<Integer> white = new ArrayList<Integer>();
            for (int j = 0; j < 5; j++) {
                a[j] = s.charAt(j) == 'B';
                if (a[j])
                    black.add(j);
                else
                    white.add(j);
            }
            answers.add(a);

            if (i == steps - 1) break;

            if (black.size() > 0 && white.size() > 0 && i - lastSwap > 5 + Math.random() * 3) {
                lastSwap = i;
                int b = black.get(random.nextInt(black.size()));
                int w = white.get(random.nextInt(white.size()));
                System.out.println("Swap " + (b + 1) + " " + (w + 1));
                System.out.flush();
                commands.add(b);
                commands.add(w);
            } else{
                System.out.println("Next");
                System.out.flush();
                commands.add(-1);
            }
        }

        for (int n = 20; n <= 100; n++) {
            for (int s = 1; s <= n; s++) {
                if (gcd(s, n) > 5) continue;

                int[] map = new int[n];
                Arrays.fill(map, -1);
                int pos = 0;
                int command = 0, answer = 0;
                boolean ok = true;
                while (command < commands.size() && answer < answers.size()) {
                    for (int i = 0; i < 5; i++) {
                        int t = answers.get(answer)[i] ? 1 : 0;
                        if (map[(pos + i) % n] != -1 && map[(pos + i) % n] != t) {
                            ok = false;
                            break;
                        }
                        map[(pos + i) % n] = t;
                    }
                    answer++;
                    if (!ok) break;

                    if (commands.get(command) == -1) {
                        pos = (pos + 2 * n - s) % n;
                        command++;
                    } else {
                        int a = commands.get(command);
                        int b = commands.get(command + 1);
                        int t = map[(pos + a) % n];
                        map[(pos + a) % n] = map[(pos + b) % n];
                        map[(pos + b) % n] = t;
                        command += 2;
                    }
                }
                if (!ok) continue;

                int res = 0;
                for (int i = 0; i < n; i++) {
                    if (map[i] == -1) ok = false;
                    if (map[i] == 1) res++;
                }
                if (!ok) continue;
                System.out.println("Answer " + res);
                System.out.flush();
                return;
            }
        }
    }

    int gcd(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        while (b != 0) {
            a %= b;
            if (a < b) {
                int t = a;
                a = b;
                b = t;
            }
        }
        return a;
    }
}
