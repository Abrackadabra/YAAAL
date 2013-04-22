package prep;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 13/01/13
 * Time: 21:06
 */
public class SuffixAutomaton {
    public static class State {
        public int length;
        public char parentChar;

        public State link, parent;

        public State[] edges = new State[36];
        //public Map<Character, State> edges = new HashMap<Character, State>();
        public int a = 0;
        public boolean visited = false;
    };

    public final State root = new State();
    State last = root;

    private void append(char c) {
        State current = new State();
        current.length = last.length + 1;
        current.parent = last;
        current.parentChar = c;
        State p;
        for (p = last; p != null && p.edges[c - 'a'] == null; p = p.link) {
            p.edges[c - 'a'] = current;
        }
        if (p == null) {
            current.link = root;
        } else {
            State q = p.edges[c - 'a'];
            if (p.length + 1 == q.length) {
                current.link = q;
            } else {
                State clone = new State();
                clone.length = p.length + 1;
                clone.parent = p;
                clone.parentChar = c;
                clone.edges = q.edges.clone();
                clone.link = q.link;
                for (; p != null && p.edges[c - 'a'] == q; p = p.link) {
                    p.edges[c - 'a'] = clone;
                }
                q.link = clone;
                current.link = clone;
            }
        }
        last = current;
    }

    public SuffixAutomaton(String s) {
        for (char c : s.toCharArray()) {
            append(c);
        }
    }

    public static void main(String[] args) {
        SuffixAutomaton suffixAutomaton = new SuffixAutomaton("abcbc");
        System.out.println(suffixAutomaton);
    }
}
