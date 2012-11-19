package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.*;

public class General {
    class Permutation {
        int[] a;
        ArrayList<Integer> history = new ArrayList<Integer>();

        Permutation(String s) {
            a = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                a[i] = s.charAt(i) - 'a';
            }
        }

        Permutation(int[] q, int n) {
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            for (int i = 0; i < q.length; i++) {
                a[q[i] - 1] = q[(i + 1) % q.length] - 1;
            }
        }

        Permutation(int[] a) {
            this.a = a;
        }

        Permutation apply(Permutation p) {
            int[] res = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                res[p.a[i]] = a[i];
            }
            return new Permutation(res);
        }

        Permutation apply(Permutation p, int id) {
            Permutation res = apply(p);
            res.history.addAll(history);
            res.history.add(id);
            return res;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < a.length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(a[i]);
            }
            sb.append("]");
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Permutation that = (Permutation) o;

            if (!Arrays.equals(a, that.a)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }

    int n, m;
    Permutation begin;
    Permutation end;

    Permutation[] commands;
    Permutation[] commandsReversed;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        begin = new Permutation(in.nextString());
        end = new Permutation(in.nextString());

        commands = new Permutation[m];
        commandsReversed = new Permutation[m];
        for (int i = 0; i < m; i++) {
            int[] a = in.nextIntArray(in.nextInt());
            commands[i] = new Permutation(a, n);
            int[] b = new int[a.length];
            for (int j = 0; j < a.length; j++) {
                b[j] = a[a.length - 1 - j];
            }
            commandsReversed[i] = new Permutation(b, n);
        }

        int leftTurns = 0, rightTurns = 0;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                leftTurns++;
            } else {
                rightTurns++;
            }
            HashSet<Permutation> forward = calc(begin, commands, leftTurns, false);
            HashSet<Permutation> back = calc(end, commandsReversed, rightTurns, true);

            ArrayList<Integer> best = null;

            for (Permutation permutation : forward) {
                if (back.contains(permutation)) {
                    Permutation a = permutation;
                    Permutation b = null;
                    for (Permutation permutation1 : back) {
                        if (permutation1.equals(a)) {
                            b = permutation1;
                        }
                    }

                    ArrayList<Integer> x = new ArrayList<Integer>();

                    for (int q = 0; q < a.history.size(); q++) {
                        x.add(a.history.get(q) + 1);
                    }
                    for (int q = b.history.size() - 1; q >= 0; q--) {
                        x.add(b.history.get(q) + 1);
                    }

                    if (best == null || myComp.compare(x, best) < 0) {
                        best = x;
                    }
                }
            }

            if (best != null) {
                for (int c : best) {
                    out.print(c + " ");
                }
                return;
            }
        }
    }

    Comparator<ArrayList<Integer>> myComp = new Comparator<ArrayList<Integer>>() {
        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            for (int i = 0; i < Math.max(o1.size(), o2.size()); i++) {
                if (o1.get(i) < o2.get(i)) {
                    return -1;
                }

                if (o1.get(i) > o2.get(i)) {
                    return 1;
                }
            }
            return 0;
        }
    };

    HashSet<Permutation> calc(Permutation from, Permutation[] commands, int turns, boolean reverse) {
        HashSet<Permutation> set = new HashSet<Permutation>();
        set.add(from);
        for (int i = 0; i < turns; i++) {
            HashSet<Permutation> next = new HashSet<Permutation>();
            for (Permutation permutation : set) {
                if (!reverse) {
                    for (int j = 0; j < commands.length; j++) {
                        Permutation p = permutation.apply(commands[j], j);
                        if (!next.contains(p)) {
                            next.add(p);
                        }
                    }
                } else {
                    for (int j = 0; j < commands.length; j++) {
                        Permutation p = permutation.apply(commands[j], commands.length - j - 1);
                        if (!next.contains(p)) {
                            next.add(p);
                        }
                    }
                }
            }
            set = next;
        }
        return set;
    }
}
