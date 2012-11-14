package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class J {
    int[] letters = new int[4];
    int[] lettersFirst = new int[4];
    int[] lettersSecond = new int[4];
    int[] lettersThird = new int[4];

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        Scanner min = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            String s = "";
            for (int j = 0; j < 18; j++)
                s += (char) ('a' + i);
//            System.out.println("abacabadbcdabacaba");
            System.out.println(s);
            System.out.flush();

            letters[i] = min.nextInt();
            if (letters[i] == -1) return;
        }
        letters[3] = 18 - letters[0] - letters[1] - letters[2];

        int[] sum = new int[4];
        sum[0] = letters[0];

        for (int i = 1; i < 4; i++) {
            String s = "";
            for (int j = 0; j < 6; j++) {
                s += (char) ('a' + i);
            }
            for (int j = 0; j < 12; j++) {
                s += 'a';
            }
//            System.out.println("abacabadbcdabacaba");
            System.out.println(s);
            System.out.flush();

            sum[i] = min.nextInt();
            if (sum[i] == -1) return;
        }

        int t = sum[0] + sum[1] + sum[2] + sum[3];
        t = (t - 6) / 4;
        lettersFirst[0] = sum[0] - t;
        lettersFirst[1] = sum[1] - t;
        lettersFirst[2] = sum[2] - t;
        lettersFirst[3] = sum[3] - t;

        for (int i = 1; i < 4; i++) {
            String s = "";
            for (int j = 0; j < 6; j++) {
                s += 'a';
            }
            for (int j = 0; j < 6; j++) {
                s += (char) ('a' + i);
            }
            for (int j = 0; j < 6; j++) {
                s += 'a';
            }
//            System.out.println("abacabadbcdabacaba");
            System.out.println(s);
            System.out.flush();

            sum[i] = min.nextInt();
            if (sum[i] == -1) return;
        }

        t = sum[0] + sum[1] + sum[2] + sum[3];
        t = (t - 6) / 4;
        lettersSecond[0] = sum[0] - t;
        lettersSecond[1] = sum[1] - t;
        lettersSecond[2] = sum[2] - t;
        lettersSecond[3] = sum[3] - t;

        lettersThird[0] = letters[0] - lettersFirst[0] - lettersSecond[0];
        lettersThird[1] = letters[1] - lettersFirst[1] - lettersSecond[1];
        lettersThird[2] = letters[2] - lettersFirst[2] - lettersSecond[2];
        lettersThird[3] = letters[3] - lettersFirst[3] - lettersSecond[3];

        go(0);

        while (true) {
            Candidate candidate = null;
            for (Candidate c : candidates) {
                candidate = c;
                break;
            }
            candidates.remove(candidate);

//            System.out.println("abacabadbcdabacaba");
            System.out.println(candidate);
            System.out.flush();
            int ans = min.nextInt();
            if (ans == -1) return;

            HashSet<Candidate> newCandidates = new HashSet<Candidate>();
            for (Candidate c : candidates) {
                if (c.same(candidate) == ans)
                    newCandidates.add(c);
            }
            candidates = newCandidates;
//            System.out.println(candidates.size());
        }
    }

    int[] word = new int[18];
    HashSet<Candidate> candidates = new HashSet<Candidate>();

    void go(int x) {
        if (x == 18) {
            candidates.add(new Candidate(word));
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (x > 0 && word[x - 1] == i) continue;
            if (x < 6) {
                if (lettersFirst[i] == 0) continue;
                lettersFirst[i]--;
                word[x] = i;
                go(x + 1);
                lettersFirst[i]++;
            } else if (x < 12) {
                if (lettersSecond[i] == 0) continue;
                lettersSecond[i]--;
                word[x] = i;
                go(x + 1);
                lettersSecond[i]++;
            } else {
                if (lettersThird[i] == 0) continue;
                lettersThird[i]--;
                word[x] = i;
                go(x + 1);
                lettersThird[i]++;
            }
        }
    }

}

class Candidate {
    int[] word = new int[18];

    Candidate(int[] a) {
        for (int i = 0; i < 18; i++)
            word[i] = a[i];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length; i++)
            sb.append((char) ('a' + word[i]));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        if (!Arrays.equals(word, candidate.word)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return word != null ? Arrays.hashCode(word) : 0;
    }

    public int same(Candidate c) {
        int res = 0;
        for (int i = 0; i < 18; i++) {
            if (word[i] == c.word[i])
                res++;
        }
        return res;
    }
}
