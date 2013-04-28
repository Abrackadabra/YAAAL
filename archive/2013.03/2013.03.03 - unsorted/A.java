package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import javax.xml.crypto.NodeSetData;
import java.util.*;

public class A {
    static class Node implements Comparable<Node> {
        String name;
        final Collection<Node> children = new ArrayList<Node>();
        final int id;

        Node parent;

        Node(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String toString() {
            return countWeight() + "\n" + toString(0);
        }

        String toString(int offset) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < offset; i++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(name);
            stringBuilder.append(' ');
            stringBuilder.append(id);
            stringBuilder.append('\n');

            for (Node node : children) {
                stringBuilder.append(node.toString(offset + 4));
            }

            return stringBuilder.toString();
        }

        public String toStringXml() {
            return toStringXml(0);
        }

        String toStringXml(int offset) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < offset; i++) {
                stringBuilder.append(' ');
            }
            if (children.size() == 0) {
                stringBuilder.append("<file name='");
            } else {
                stringBuilder.append("<dir name='");
            }
            stringBuilder.append(name);
            stringBuilder.append("' id='");
            stringBuilder.append(id);
            if (children.size() == 0) {
                stringBuilder.append("'/>");
            } else {
                stringBuilder.append("'>");
                stringBuilder.append('\n');
            }

            for (Node node : children) {
                stringBuilder.append(node.toStringXml(offset + 2));
                stringBuilder.append('\n');
            }

            if (children.size() > 0) {
                for (int i = 0; i < offset; i++) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("</dir>");
            }

            return stringBuilder.toString();
        }

        int countWeight() {
            int res = 1;
            for (Node node : children) {
                res += node.countWeight();
            }
            return res;
        }

        void hang() {
            for (Node node : children) {
                node.parent = this;
                node.children.remove(this);
                node.hang();
            }
        }

        String longName() {
            if (parent == null) {
                return name;
            }
            return parent.longName() + "/" + name;
        }

        void addNodes(Collection<Node> collection) {
            collection.add(this);
            for (Node node : children) {
                node.addNodes(collection);
            }
        }

        @Override
        public int compareTo(Node o) {
            if (id < o.id) return -1;
            if (id > o.id) return 1;
            return 0;
        }
    }

    static Node parseFind(InputReader in) {
        int n = in.nextInt();
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(in.nextString(), in.nextInt());
        }

        Node root = null;

        for (Node node : nodes) {
            String parentName;
            if (!node.name.contains("/")) {
                parentName = "";
            } else {
                parentName = node.name.substring(0, node.name.lastIndexOf("/"));
            }

            boolean foundParent = false;

            for (Node anode : nodes) {
                if (anode.name.equals(parentName)) {
                    foundParent = true;
                    anode.children.add(node);
                    node.parent = anode;
                    break;
                }
            }

            if (!foundParent) {
                root = node;
            }
        }

        for (Node node : nodes) {
            if (node == root) {
                continue;
            }
            node.name = node.name.substring(node.name.lastIndexOf("/") + 1);
        }

        return root;
    }

    static Node parsePython(InputReader in) {
        int n = Integer.parseInt(in.nextLine());
        String rootString = in.nextLine();
        Node root = new Node(
                rootString.replaceAll(" .*", ""),
                Integer.parseInt(rootString.replaceAll(".* ", ""))
        );
        Node last = root;
        int lastDepth = 0;
        for (int i = 1; i < n; i++) {
            String string = in.nextLine();
            int depth = (string.length() - string.trim().length()) / 4;
            string = string.trim();

            Node node = new Node(
                    string.replaceAll(" .*", ""),
                    Integer.parseInt(string.replaceAll(".* ", ""))
            );

            int backs = lastDepth - depth + 1;

            for (int j = 0; j < backs; j++) {
                last = last.parent;
            }

            last.children.add(node);
            node.parent = last;

            last = node;
            lastDepth = depth;
        }
        return root;
    }

    static Node parseAcm1(InputReader in) {
        int n = in.nextInt();
        SortedMap<Integer, Node> map = new TreeMap<Integer, Node>();

        for (int i = 0; i < n; i++) {
            String name = in.nextString();
            int id = in.nextInt();
            map.put(id, new Node(name, id));
        }

        Iterator<Integer> currentId = map.keySet().iterator();
        for (int i = 0; i < n; i++) {
            int id = currentId.next();

            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                int t = in.nextInt();
                map.get(t).parent = map.get(id);
                map.get(id).children.add(map.get(t));
            }
        }

        return map.get(0);
    }

    static Node parseAcm2(InputReader in) {
        int n = in.nextInt();
        SortedMap<Integer, Node> map = new TreeMap<Integer, Node>();

        for (int i = 0; i < n; i++) {
            String name = in.nextString();
            int id = in.nextInt();
            map.put(id, new Node(name, id));
        }

        Iterator<Integer> currentId = map.keySet().iterator();
        for (int i = 0; i < n; i++) {
            int id = currentId.next();

            int k = in.nextInt();
            if (k == -1) continue;

            map.get(k).children.add(map.get(id));
            map.get(id).parent = map.get(k);
        }

        return map.get(0);
    }

    static Node parseAcm3(InputReader in) {
        int n = in.nextInt();
        SortedMap<Integer, Node> map = new TreeMap<Integer, Node>();

        for (int i = 0; i < n; i++) {
            String name = in.nextString();
            int id = in.nextInt();
            map.put(id, new Node(name, id));
        }

        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            map.get(a).children.add(map.get(b));
            map.get(b).children.add(map.get(a));
        }

        map.get(0).hang();

        return map.get(0);
    }

    static Node parseXml(InputReader in) {
        String rootString = in.nextLine();
        Node root = new Node(
                extractNameFromXml(rootString),
                extractIdFromXml(rootString)
        );

        Node last = root;
        int lastDepth = 0;

        while (true) {
            String string = in.nextLine();
            if (string == null) break;

            int depth = (string.length() - string.trim().length()) / 2;
            string = string.trim();

            if (string.startsWith("</dir>")) continue;

            Node node = new Node(
                    extractNameFromXml(string),
                    extractIdFromXml(string)
            );

            int backs = lastDepth - depth + 1;

            for (int j = 0; j < backs; j++) {
                last = last.parent;
            }

            last.children.add(node);
            node.parent = last;

            last = node;
            lastDepth = depth;
        }
        return root;
    }

    static String extractNameFromXml(String s) {
        String a = s.trim();
        a = a.replaceAll(".*name=.", "");
        a = a.replaceAll("\u0019.*", "");
        a = a.replaceAll("’.*", "");
        a = a.replaceAll("'.*", "");
        return a;
    }

    static int extractIdFromXml(String s) {
        String a = s.trim();
        a = a.replaceAll(".*id=.", "");
        a = a.replaceAll("\u0019.*", "");
        a = a.replaceAll("’.*", "");
        a = a.replaceAll("'.*", "");
        return Integer.parseInt(a);
    }

    static void printFind(Node root, OutputWriter out) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        root.addNodes(nodes);
        Collections.sort(nodes);

        out.println(nodes.size());
        for (Node node : nodes) {
            out.println(node.longName() + " " + node.id);
        }
    }

    static void printPython(Node root, OutputWriter out) {
        out.println(root);
    }

    static void printAcm1(Node root, OutputWriter out) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        root.addNodes(nodes);
        Collections.sort(nodes);

        out.println(nodes.size());
        for (Node node : nodes) {
            out.println(node.name + " " + node.id);
        }

        for (Node node : nodes) {
            ArrayList<Integer> nums = new ArrayList<Integer>();
            nums.add(node.children.size());
            for (Node anode : node.children) {
                nums.add(anode.id);
            }
            out.printSeparated(nums);
            out.println();
        }
    }

    static void printAcm2(Node root, OutputWriter out) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        root.addNodes(nodes);
        Collections.sort(nodes);

        out.println(nodes.size());
        for (Node node : nodes) {
            out.println(node.name + " " + node.id);
        }

        for (Node node : nodes) {
            out.println(node.parent == null ? -1 : node.parent.id);
        }
    }

    static void printAcm3(Node root, OutputWriter out) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        root.addNodes(nodes);
        Collections.sort(nodes);

        out.println(nodes.size());
        for (Node node : nodes) {
            out.println(node.name + " " + node.id);
        }

        for (Node node : nodes) {
            for (Node anode : nodes) {
                if ((node.parent == anode || anode.parent == node) && node.id < anode.id) {
                    out.println(node.id + " " + anode.id);
                }
            }
        }
    }

    static void printXml(Node root, OutputWriter out) {
        out.println(root.toStringXml());
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String from = in.nextLine();
        String to = in.nextLine();

        Node root = null;

        if (from.equals("find")) root = parseFind(in);
        if (from.equals("python")) root = parsePython(in);
        if (from.equals("acm1")) root = parseAcm1(in);
        if (from.equals("acm2")) root = parseAcm2(in);
        if (from.equals("acm3")) root = parseAcm3(in);
        if (from.equals("xml")) root = parseXml(in);

        if (to.equals("find")) printFind(root, out);
        if (to.equals("python")) printPython(root, out);
        if (to.equals("acm1")) printAcm1(root, out);
        if (to.equals("acm2")) printAcm2(root, out);
        if (to.equals("acm3")) printAcm3(root, out);
        if (to.equals("xml")) printXml(root, out);
    }
}
