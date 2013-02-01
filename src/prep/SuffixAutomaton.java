package prep;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 13/01/13
 * Time: 21:06
 */
public class SuffixAutomaton {
    private static class State {
        int length;
        char parent_char;

        State link, parent;

        Map<Character, State> edges = new HashMap<Character, State>();
    };

    private State root;
    private State last;

    private void init() {
        root = new State();
        root.length = 0;
        root.link = null;
        root.parent = null;
        root.parent_char = '\0';
        last = root;
    }

    private void append(char c) {
        State current = new State();
        current.length = last.length + 1;
        current.parent = last;
        current.parent_char = c;
        State p = last;
        for (; p != null && !p.edges.containsKey(c); p = p.link) {
            p.edges.put(c, current);
        }
        if (p == null) {
            current.link = root;
        } else {
            State q = p.edges.get(c);
            if (p.length + 1 == q.length) {
                current.link = q;
            } else {
                State clone = new State();
                clone.length = p.length + 1;
                clone.parent = p;
                clone.parent_char = c;
                clone.edges = q.edges;
                clone.link = q.link;
                for (; p != null && p.edges.get(c) == q; p = p.link) {
                    p.edges.put(c, clone);
                }
                q.link = clone;
                current.link = clone;
            }
        }
        last = current;
    }

    SuffixAutomaton(String s) {
        init();
        for (char c : s.toCharArray()) {
            append(c);
        }
    }

    public static void main(String[] args) {
        SuffixAutomaton suffixAutomaton = new SuffixAutomaton("abcbc");
        System.out.println(suffixAutomaton);
    }
}
