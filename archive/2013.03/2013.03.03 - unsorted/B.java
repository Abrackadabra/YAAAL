package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import prep.SuffixAutomaton;

import java.util.*;

public class B {
    Map<SuffixAutomaton.State, Integer> visited = new HashMap<SuffixAutomaton.State, Integer>();

    int n;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += in.nextString() + (char)(i + 'z' + 1);
        }

        SuffixAutomaton suffixAutomaton = new SuffixAutomaton(s);
        SuffixAutomaton.State root = suffixAutomaton.root;

        dfs(root);

        Stack<Character> string = new Stack<Character>();
        while (bestState != root) {
            string.add(bestState.parentChar);
            bestState = bestState.parent;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!string.isEmpty()) {
            stringBuilder.append(string.pop());
        }
        out.println(stringBuilder.toString());
    }

    int max = -1;
    SuffixAutomaton.State bestState;

    void dfs(SuffixAutomaton.State state) {
        if (state.visited) {
            return;
        }
        state.visited = true;
        for (char c = 'a'; c <= 'z' + 10; c++) {
            if (state.edges[c - 'a'] == null) continue;
            dfs(state.edges[c - 'a']);
            if (c >= 'z' + 1 && c <= 'z' + 10) {
                state.a |= 1 << (c - 'z' - 1);
            } else {
                state.a |= state.edges[c - 'a'].a;
            }
        }
        boolean ok = state.a == (1 << n) - 1;
        if (ok && state.length > max) {
            max = state.length;
            bestState = state;
        }
    }
}
