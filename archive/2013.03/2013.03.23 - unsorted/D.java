package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;

public class D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.nextInt();

        int a = 0, b = 0;

        String s = in.nextString();

        ArrayList<Boolean> queue = new ArrayList<Boolean>();

        for (char c : s.toCharArray()) {
            queue.add(c == 'M');
        }

        int i = 0;

        for (; i < s.length(); i++) {
            if (Math.abs(a - b) > x) {
                i--;
                break;
            }
            if (b >= a && queue.get(0)) {
                queue.remove(0);
                a++;
                continue;
            }
            if (b <= a && !queue.get(0)) {
                queue.remove(0);
                b++;
                continue;
            }
            if (queue.size() > 1 && b >= a && queue.get(1)) {
                queue.remove(1);
                a++;
                continue;
            }
            if (queue.size() > 1 && b <= a && !queue.get(1)) {
                queue.remove(1);
                b++;
                continue;
            }

            if (queue.get(0)) {
                a++;
            } else {
                b++;
            }
            queue.remove(0);
        }
        out.println(i);
    }
}
