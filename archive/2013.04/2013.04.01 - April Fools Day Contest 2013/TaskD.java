package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] a = in.nextString().toLowerCase().toCharArray();

        int x = in.nextInt();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            int c = a[i];

            if (c >= 'a' && c <= 'z') {
                c = c - 'a';
            } else {
                c = c - 'A';
            }


            if (a[i] < x + 97) {
                c += 'A';
            } else {
                c += 'a';
            }
            stringBuilder.append((char) c);
        }

        out.println(stringBuilder.toString());
    }
}
