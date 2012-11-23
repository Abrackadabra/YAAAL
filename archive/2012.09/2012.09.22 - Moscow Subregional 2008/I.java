package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class I {
    int maxX, maxY;
    int n;
    int taken0, taken1;
    int[][] a;

	public void solve(int testNumber, Reader in, PrintWriter out) {
        maxX = in.nextInt();
        maxY = in.nextInt();
        n = in.nextInt();
        a = new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = in.nextInt();
            }
        }

        taken0 = in.nextInt() - 1;
        taken1 = in.nextInt() - 1;

        ArrayList<Integer> answer = null;

        answer = getHorizontal();

        if (answer == null)
            answer = getVertical();

        if (answer == null) {
            out.println(-1);
        } else {
            out.println(answer.size());
            for (int i : answer)
                out.print((i + 1) + " ");
        }
	}

    ArrayList<Integer> getHorizontal() {
        if (a[taken0][0] > a[taken1][0]) {
            int t = taken0;
            taken0 = taken1;
            taken1 = t;
        }
        if (a[taken0][2] > a[taken1][0]) return null;

        ArrayList<Integer> result = new ArrayList<Integer>();

        ArrayList<Integer> t = getHorizontal(0, a[taken0][0]);
        if (t == null) return null;
        result.addAll(t);

        result.add(taken0);

        t = getHorizontal(a[taken0][2], a[taken1][0]);
        if (t == null) return null;
        result.addAll(t);

        result.add(taken1);

        t = getHorizontal(a[taken1][2], maxX);
        if (t == null) return null;
        result.addAll(t);

        return result;
    }

    ArrayList<Integer> getHorizontal(int l, int r) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (l == r) return result;

        int[] prev = new int[maxX + 1];
        Arrays.fill(prev, -1);
        ArrayList<Integer> q = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            q.add(i);
        Collections.sort(q, new HorizontalComparator());

        for (int i : q) {
            if (i == taken0 || i == taken1) continue;
            if (a[i][0] == l || prev[a[i][0]] != -1) {
                prev[a[i][2]] = i;
            }
        }

        if (prev[r] == -1) return null;

        Stack<Integer> stack = new Stack<Integer>();
        int t = r;
        while (t != l) {
            stack.add(prev[t]);
            t = a[prev[t]][0];
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    class HorizontalComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (a[o1][0] < a[o2][0]) return -1;
            if (a[o1][0] > a[o2][0]) return 1;
            return 0;
        }
    }

    ArrayList<Integer> getVertical() {
        if (a[taken0][1] > a[taken1][1]) {
            int t = taken0;
            taken0 = taken1;
            taken1 = t;
        }
        if (a[taken0][3] > a[taken1][1]) return null;

        ArrayList<Integer> result = new ArrayList<Integer>();

        ArrayList<Integer> t = getVertical(0, a[taken0][1]);
        if (t == null) return null;
        result.addAll(t);

        result.add(taken0);

        t = getVertical(a[taken0][3], a[taken1][1]);
        if (t == null) return null;
        result.addAll(t);

        result.add(taken1);

        t = getVertical(a[taken1][3], maxY);
        if (t == null) return null;
        result.addAll(t);

        return result;
    }

    ArrayList<Integer> getVertical(int l, int r) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (l == r) return result;

        int[] prev = new int[maxY + 1];
        Arrays.fill(prev, -1);
        ArrayList<Integer> q = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            q.add(i);
        Collections.sort(q, new VerticalComparator());

        for (int i : q) {
            if (i == taken0 || i == taken1) continue;
            if (a[i][1] == l || prev[a[i][1]] != -1) {
                prev[a[i][3]] = i;
            }
        }

        if (prev[r] == -1) return null;

        Stack<Integer> stack = new Stack<Integer>();
        int t = r;
        while (t != l) {
            stack.add(prev[t]);
            t = a[prev[t]][1];
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    class VerticalComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (a[o1][1] < a[o2][1]) return -1;
            if (a[o1][1] > a[o2][1]) return 1;
            return 0;
        }
    }
}
