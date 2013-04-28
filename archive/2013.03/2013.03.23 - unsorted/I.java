package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class I {
    int[] known = new int[26];
    int[] revknown = new int[26];

    ArrayList<String> knownStrings = new ArrayList<String>();

    ArrayList<Queue<Integer>> rollBackQueues = new ArrayList<Queue<Integer>>();

    ArrayList<ArrayList<Integer>> words = new ArrayList<ArrayList<Integer>>();

    String line;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        for (int i = 0; i < 150; i++) {
            rollBackQueues.add(new LinkedList<Integer>());
        }

        knownStrings.add("be");
        knownStrings.add("our");
        knownStrings.add("rum");
        knownStrings.add("dead");
        knownStrings.add("will");
        knownStrings.add("hook");
        knownStrings.add("ship");
        knownStrings.add("blood");
        knownStrings.add("sable");
        knownStrings.add("avenge");
        knownStrings.add("parrot");
        knownStrings.add("captain");

        line = in.nextLine();

        for (String s : line.split(" +")) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            for (int i = 0; i < s.length(); i++) {
                a.add(s.charAt(i) - 'a');
                needToKnow.add(s.charAt(i) - 'a');
            }
            words.add(a);
        }

        Arrays.fill(known, -1);
        Arrays.fill(revknown, -1);

        go(0);
        if (impossible || solution == null) {
            out.println("Impossible");
        } else {
            for (char c : line.toCharArray()) {
                if (c == ' ') {
                    out.print(c);
                    continue;
                }
                out.print((char) ('a' + solution[c - 'a']));
            }
        }
    }

    int[] solution = null;
    boolean impossible = false;

    Collection<Integer> needToKnow = new HashSet<Integer>();

    boolean setKnown(int what, int to, int i) {
        if ((known[what] != -1 && known[what] != to)
            || (revknown[to] != -1 && revknown[to] != what)) {
            return false;
        }
        if (known[what] == -1) {
            rollBackQueues.get(i).add(what);
        }
        known[what] = to;
        revknown[to] = what;
        return true;
    }

    void rollBack(int i) {
        Queue<Integer> queue = rollBackQueues.get(i);
        while (!queue.isEmpty()) {
            int what = queue.poll();
            int to = known[what];

            known[what] = -1;
            revknown[to] = -1;
        }
    }

    boolean tryToSet(int x, String goal) {
        ArrayList<Integer> word = words.get(x);
        for (int i = 0; i < goal.length(); i++) {
            if (!setKnown(word.get(i), goal.charAt(i) - 'a', x)) {
                return false;
            }
        }
        return true;
    }

    void go(int i) {
        if (i == words.size()) {
                                               /*
            for (char c : line.toCharArray()) {
                if (c == ' ') {
                    System.err.print(c);
                    continue;
                }
                System.err.print((char) ('a' + known[c - 'a']));
            }
            System.err.println();
            System.err.flush();   */

            for (int j : needToKnow) {
                if (known[j] == -1) {
                    return;
                }
            }
            if (solution != null && !Arrays.equals(solution, known)) {
                impossible = true;
            } else {
                solution = known.clone();
            }
            return;
        }

        ArrayList<Integer> word = words.get(i);
        int l = word.size();

        for (String s : knownStrings) {
            if (s.length() == l && lookAlike(word, s)) {
                if (tryToSet(i, s)) {
                    go(i + 1);
                }
                rollBack(i);
            }
        }

        go(i + 1);
    }

    boolean lookAlike(ArrayList<Integer> word, String string) {
        return getEqualities(word).equals(getEqualities(string));
    }

    Map<ArrayList<Integer>, ArrayList<Integer>> mem0 = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();

    ArrayList<Integer> getEqualities(ArrayList<Integer> word) {
        if (mem0.containsKey(word)) {
            return mem0.get(word);
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < word.size() - 1; i++) {
            for (int j = i + 1; j < word.size(); j++) {
                if (word.get(i).equals(word.get(j))) {
                    res.add(i);
                    res.add(j);
                }
            }
        }
        mem0.put(word, res);
        return res;
    }

    Map<String, ArrayList<Integer>> mem1 = new HashMap<String, ArrayList<Integer>>();

    ArrayList<Integer> getEqualities(String word) {
        if (mem1.containsKey(word)) {
            return mem1.get(word);
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < word.length() - 1; i++) {
            for (int j = i + 1; j < word.length(); j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    res.add(i);
                    res.add(j);
                }
            }
        }
        mem1.put(word, res);
        return res;
    }
}
