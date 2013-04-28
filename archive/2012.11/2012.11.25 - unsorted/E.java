package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class E {
    HashMap<String, Integer> stringToID = new HashMap<String, Integer>();
    HashMap<Integer, int[]>  rules = new HashMap<Integer, int[]>();
    HashMap<Integer, String> nonTerminals = new HashMap<Integer, String>();

    int[][]   dps = new int[500][2000];
    boolean[] calculated;

    String pattern;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = 0;
        stringToID.clear();
        rules.clear();
        nonTerminals.clear();
        for (int i = 0; i < n; i++) {
            String[] inp = in.nextLine().split(" ");

            if (!stringToID.containsKey(inp[0])) {
                stringToID.put(inp[0], k++);
            }

            if (inp.length == 3) {
                nonTerminals.put(stringToID.get(inp[0]), inp[2]);
            } else {
                if (!stringToID.containsKey(inp[2])) {
                    stringToID.put(inp[2], k++);
                }
                if (!stringToID.containsKey(inp[4])) {
                    stringToID.put(inp[4], k++);
                }
                rules.put(stringToID.get(inp[0]), new int[] {stringToID.get(inp[2]), stringToID.get(inp[4])});
            }
        }

        int start = stringToID.get(in.nextString());
        pattern = in.nextString();

        calculated = new boolean[n];

        calc(start);

        if (dps[start][0] == pattern.length()) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    void calc(int id) {
        if (calculated[id]) {
            return;
        }

        if (nonTerminals.containsKey(id)) {
            String s = nonTerminals.get(id);
            for (int begin = 0; begin < pattern.length(); begin++) {
                dps[id][begin] = begin;
                for (int i = 0; i < s.length() && dps[id][begin] < pattern.length(); i++) {
                    if (s.charAt(i) == pattern.charAt(dps[id][begin])) {
                        dps[id][begin]++;
                    }
                }
            }
        } else {
            int[] dep = rules.get(id);
            calc(dep[0]);
            calc(dep[1]);
            for (int i = 0; i < pattern.length(); i++) {
                dps[id][i] = dps[dep[0]][i];
                if (dps[id][i] != pattern.length()) {
                    dps[id][i] = dps[dep[1]][dps[id][i]];
                }
            }
        }

        calculated[id] = true;
    }
}
