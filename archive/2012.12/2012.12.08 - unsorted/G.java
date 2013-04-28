package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

import java.util.*;

public class G {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        while (true) {
            Building root;
            try {
                root = new Building(in, null);
            } catch (Exception e) {
                break;
            }
            out.println(root.ifYes);
        }
    }
}

class Building {
    int n, m;

    int[] vertices;
    ArrayList<int[]> edges = new ArrayList<int[]>();

    int ifNo = Integer.MAX_VALUE;
    int ifYes = Integer.MAX_VALUE;

    ArrayList<Building> children = new ArrayList<Building>();
    int[] parentEdge;

    Building(InputReader in, int[] parentEdge) {
        this.parentEdge = parentEdge;
        n = in.nextInt();
        vertices = new int[n];
        m = in.nextInt();
        int w = in.nextInt();

        HashSet<Integer> thisBuildingsVertices = new HashSet<Integer>();

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            edges.add(new int[] {a, b});
            thisBuildingsVertices.add(a);
            thisBuildingsVertices.add(b);
        }

        int c = 0;
        for (int v : thisBuildingsVertices) {
            vertices[c++] = v;
        }

        assert c == n;

        for (int i = 0; i < w; i++) {
            int a = in.nextInt(), b = in.nextInt();

            children.add(new Building(in, new int[] {a, b}));
        }

        calc();
    }

    void calc() {
        for (int mask = 0; mask < 1 << n; mask++) {
            int c = 0;
            HashSet<Integer> chosen = new HashSet<Integer>();
            for (int i = 0; i < n; i++) {
                if (((1 << i) & mask) > 0) {
                    chosen.add(vertices[i]);
                    c++;
                }
            }

            boolean ok = true;
            for (int[] e : edges) {
                if (!chosen.contains(e[0]) && !chosen.contains(e[1])) {
                    ok = false;
                }
            }
            if (!ok) {
                continue;
            }

            for (Building child : children) {
                if (chosen.contains(child.parentEdge[0])) {
                    c += child.ifNo;
                } else {
                    c += child.ifYes;
                }
            }

            if (parentEdge != null) {
                if (chosen.contains(parentEdge[1])) {
                    ifYes = AMath.min(ifYes, c);
                }
                ifNo = AMath.min(ifNo, c);
            } else {
                ifYes = AMath.min(ifYes, c);
            }
        }
    }
}
