package prep;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 10/01/13
 * Time: 22:31
 */
public class SuffixTree {
    public final String s;
    public final Node root = new Node();

    private State pointer = new State(root, 0);

    public SuffixTree(String s) {
        this.s = s;
        for (int i = 0; i < s.length(); i++) {
            build(i);
        }
    }

    class Node {
        int l = 0, r = 0;
        Node parent = null;
        Node link = null;
        Map<Character, Node> next = new HashMap<Character, Node>();

        Node() {}

        Node(int l, int r, Node parent) {
            this.l = l;
            this.r = r;
            this.parent = parent;
        }

        int length() {
            return r - l;
        }

        Node get(char c) {
            if (!next.containsKey(c)) {
                next.put(c, null);
            }
            return next.get(c);
        }

        Node getLink() {
            if (link != null) {
                return link;
            }
            if (parent == null) {
                return root;
            }

            Node to = parent.getLink();

            State parentState = new State(to, to.length());
            State parentGo = go(parentState, l + (parent == root ? 1 : 0), r);
            link = parentGo.split();

            return link;
        }


        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(" + l + ", " + r + "):");
            for (Map.Entry<Character, Node> e : next.entrySet()) {
                stringBuilder.append("[" + e.getKey() + "->{");
                stringBuilder.append(e.getValue());
                stringBuilder.append("}]");
            }
            return stringBuilder.toString();
        }

    }

    class State {
        Node node;
        int pos;

        State(Node node, int pos) {
            this.node = node;
            this.pos = pos;
        }

        Node split() {
            if (pos == node.length()) {
                return node;
            }
            if (pos == 0) {
                return node.parent;
            }

            Node oldNode = node;
            Node newNode = new Node(oldNode.l, oldNode.l + pos, oldNode.parent);
            oldNode.parent.next.put(s.charAt(oldNode.l), newNode);
            newNode.next.put(s.charAt(oldNode.l + pos), oldNode);
            oldNode.parent = newNode;
            oldNode.l += pos;

            return newNode;
        }

        public String toString() {
            return "(" + (node == null ? "null" : node.toString()) + " at " + pos + ")";
        }
    }

    void build(int pos) {
        while (true) {
            State newPointer = go(pointer, pos, pos + 1);
            if (newPointer.node != null) {
                pointer = newPointer;
                return;
            }

            Node middle = pointer.split();

            Node leaf = new Node(pos, s.length(), middle);

            middle.next.put(s.charAt(pos), leaf);

            pointer.node = middle.getLink();
            pointer.pos = pointer.node.length();

            if (middle == root) {
                return;
            }
        }
    }

    State go(State st, int l, int r) {
        while (l < r) {
            if (st.pos == st.node.length()) {
                st = new State(st.node.get(s.charAt(l)), 0);
                if (st.node == null) {
                    return st;
                }
            } else {
                if (s.charAt(st.node.l + st.pos) != s.charAt(l)) {
                    return new State(null, -1);
                }
                if (r - l < st.node.length() - st.pos) {
                    return new State(st.node, st.pos + r - l);
                }
                l += st.node.length() - st.pos;
                st.pos = st.node.length();
            }
        }
        return st;
    }

    public String toString() {
        return root.toString();
    }

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree("abadabac");
        //SuffixTree suffixTree = new SuffixTree("aba");
        System.out.println(suffixTree);
    }
}
