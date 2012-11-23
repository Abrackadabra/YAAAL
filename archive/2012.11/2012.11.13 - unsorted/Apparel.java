package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Apparel {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[101];
        for (int i = 0; i < n; i++) {
            a[in.nextInt()]++;
        }
        int max = -1;
        int maxi = -1;
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            if (a[i] > max) {
                max = a[i];
                maxi = i;
                count = 0;
            }
            if (a[i] == max) {
                count++;
            }
        }
        if (count > 1) {
            out.println(0);
        } else {
            out.println(maxi);
        }
    }
}
