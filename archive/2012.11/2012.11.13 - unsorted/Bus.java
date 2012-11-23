package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Bus {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        ArrayList<Integer> speeds = new ArrayList<Integer>();
        speeds.add(m);
        int[] d = new int[n];
        int[] l = new int[n];
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
            l[i] = in.nextInt();
            h[i] = in.nextInt();
            if (l[i] <= m)
                speeds.add(l[i]);
        }

        double bestTime = 1e9;
        int bestSpeed = -1;

        Collections.sort(speeds);

        for (int i = speeds.size() - 1; i >= 0; i--) {
            int speed = speeds.get(i);
            double time = 0.0;
            for (int j = 0; j < n; j++) {
                time += (double) d[j] / speed;
                if (speed > l[j])
                    time += h[j];
            }
            if (time < bestTime) {
                bestTime = time;
                bestSpeed = speed;
            }
        }

        out.println(bestSpeed);
	}
}
