package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.structure.Counter;

public class A {
    class Node {
        long v = -1;

        Node left = null;
        Node right = null;

        Node(char[] a) {
            this(a, 0, a.length - 1);
        }

        Node(char[] a, int l, int r) {
            if (a[l] != '[') {
                v = parse(a, l, r);
                return;
            }

            int comma = -1;
            int balance = 0;
            for (int i = l + 1; i < r; i++) {
                if (a[i] == '[') balance++;
                if (a[i] == ']') balance--;
                if (a[i] == ',' && balance == 0) {
                    comma = i;
                }
            }

            left = new Node(a, l + 1, comma - 1);
            right = new Node(a, comma + 1, r - 1);
        }

        long parse(char[] a, int l, int r) {
            long ans = 0;
            for (int i = l; i <= r; i++) {
                ans = ans * 10L + a[i] - '0';
            }
            return ans;
        }

        int maxDepth() {
            if (left == null) {
                return 1;
            } else {
                return 1 + Math.max(left.maxDepth(), right.maxDepth());
            }
        }
/*
        void go() {
            if (left != null) {
                left.go();
                right.go();
            }

            if (wildcard != -1) return;

            for (long i : left.map.keySet()) {
                if (right.map.containsKey(i)) {
                    map.put(i * 2, left.map.get(i) + right.map.get(i));
                } else {
                    map.put(i * 2, left.map.get(i) + right.wildcard);
                }
            }

            for (long i : right.map.keySet()) {
                if (!map.containsKey(i * 2) || map.get(i * 2) > left.wildcard + right.map.get(i)) {
                    map.put(i * 2, left.wildcard + right.map.get(i));
                }
            }

            wildcard = left.wildcard + right.wildcard;
        }
        */
        void run(int depth) {
            if (left == null) {
                int c = 1 << (MD - depth);
                counter.add(1.0 * v / c, 1);
                counter1++;
            } else {
                left.run(depth + 1);
                right.run(depth + 1);
            }
        }
    }

    int MD = -1;
    int counter1 = 0;

    Counter<Double> counter = new Counter<Double>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        MD = -1;
        counter1 = 0;
        counter = new Counter<Double>();

        String s = in.nextString();

        Node root = new Node(s.toCharArray());

        MD = root.maxDepth();

        root.run(0);

        int max = -1;

        for (Long aLong : counter.values()) {
            max = Math.max(max, (int) (long) aLong);
        }

        out.println(counter1 - max);
    }
}
