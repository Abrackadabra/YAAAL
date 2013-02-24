package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import javax.xml.crypto.NodeSetData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D {
    static class Node {
        Map<Node, Long> children = new HashMap<Node, Long>();

        long oneWayTicket = -1;
        long twoWayTicket = -1;

        int id;

        Node parent = null;

        Node(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (id != node.id) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return id;
        }

        void hang() {
            for (Node child : children.keySet()) {
                child.children.remove(this);
                child.parent = this;

                child.hang();
            }
        }

        Node goodChild1 = null;
        long goodCost1 = Long.MAX_VALUE;
        Node goodChild2 = null;
        long goodCost2 = Long.MAX_VALUE;

        void calc() {
            for (Node child : children.keySet()) {
                child.calc();
            }

            twoWayTicket = 0;

            for (Map.Entry<Node, Long> e : children.entrySet()) {
                Node child = e.getKey();
                long cost = e.getValue();

                twoWayTicket += child.twoWayTicket;
                twoWayTicket += cost + (cost % 2);
            }

            oneWayTicket = Long.MAX_VALUE;
            if (children.size() == 0) {
                oneWayTicket = 0;
            }

            for (Map.Entry<Node, Long> e : children.entrySet()) {
                Node child = e.getKey();
                long cost = e.getValue();

                long res = twoWayTicket - child.twoWayTicket - cost - (cost % 2);

                res += child.oneWayTicket;
                res += cost + ((cost + 1) % 2);

                if (res < oneWayTicket) {
                    oneWayTicket = res;
                    goodChild1 = child;
                    goodCost1 = res;
                }
            }

            for (Map.Entry<Node, Long> e : children.entrySet()) {
                Node child = e.getKey();
                long cost = e.getValue();

                if (child.equals(goodChild1)) continue;

                long res = twoWayTicket - child.twoWayTicket - cost - (cost % 2);

                res += child.oneWayTicket;
                res += cost + ((cost + 1) % 2);

                if (res < goodCost2) {
                    goodChild2 = child;
                    goodCost2 = res;
                }
            }

            bestAns = oneWayTicket;
        }

        static long bestAns = 0;

        void makeRoot() {
            if (parent.goodChild1 != this) {
                long cost = parent.children.get(this);

                long decrease = cost + cost % 2;
                parent.oneWayTicket -= decrease;
                parent.twoWayTicket -= decrease;

                children.put(parent, cost);
                parent.children.remove(this);

                twoWayTicket += decrease;

                long res = twoWayTicket - parent.twoWayTicket - cost - (cost % 2);

                res += parent.oneWayTicket;
                res += cost + ((cost + 1) % 2);

                if (res < oneWayTicket) {
                    oneWayTicket = res;
                }


            } else {

            }
        }

        void go() {

        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            long c = in.nextLong();

            nodes[a].children.put(nodes[b], c);
            nodes[b].children.put(nodes[a], c);
        }

        Node root = nodes[0];
        root.hang();

        root.calc();

        root.go();




        out.println(Node.bestAns);
    }
}
