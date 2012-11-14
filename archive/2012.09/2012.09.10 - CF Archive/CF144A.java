package chelper;

import abrackadabra.ArrayUtils;
import abrackadabra.Scanner;
import com.sun.deploy.util.ArrayUtil;

import java.io.PrintWriter;

public class CF144A {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        int max = ArrayUtils.findMax(a), min = ArrayUtils.findMin(a);
        int maxi = -1, mini = -1;

        for (int i = 0; i < n; i++) {
            if (a[i] == max && maxi == -1)
                maxi = i;
            if (a[i] == min)
                mini = i;
        }

        int res = maxi;
        if (maxi > mini) mini++;
        res += n - mini - 1;

        out.println(res);
	}
}
