package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class MessageRelay {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        int answer = 0;

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            visited[i] = true;

            boolean loopy = false;

            int t = a[i] - 1;
            while (true) {
                if (t == -1) {
                    break;
                }
                if (visited[t]) {
                    loopy = true;
                    break;
                }
                visited[t] = true;
                t = a[t] - 1;
            }

            if (!loopy) {
                answer++;
            }
        }

        out.println(answer);
    }
}
