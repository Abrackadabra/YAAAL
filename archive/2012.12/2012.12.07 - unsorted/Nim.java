package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.structure.Counter;

import java.util.*;

public class Nim {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Counter<Integer> counter = new Counter<Integer>();
        int n = 1000000;
        int[] a = new int[n];
        int loses = 0;
        for (int i = 0; i < n; i++) {
            if (i % 1000 == 0) {
                System.out.println(i);
            }
            boolean[] p = new boolean[300];
            for (int j = 1; j <= i / 2; j++) {
                if (i >= 3 && j != i - j) {
                    p[a[j] ^ a[i - j]] = true;
                }
            }
            a[i] = mex(p);

            counter.add(a[i]);
        }

        ArrayList<Value> values = new ArrayList<Value>();
        for (Map.Entry<Integer, Long> e : counter.entrySet()) {
            values.add(new Value(e.getKey(), (int)(long)e.getValue()));
        }
        Collections.sort(values);
        for (Value value : values) {
            System.out.println(value.value + " " + value.count);
        }
    }

    int mex(boolean[] a) {
        for (int i = 0; true; i++) {
            if (!a[i]) {
                return i;
            }
        }
    }

    class Value implements Comparable<Value> {
        int value, count;

        Value(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Value o) {
            return Integer.compare(count, o.count);
        }
    }
}
