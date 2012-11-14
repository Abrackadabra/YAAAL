package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class Princess {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        long L = in.nextLong();
        double ans = L;
        for(int i = 2; i <=ans; ++i){
            ans = Math.min(ans, i * Math.pow(L, 1.0/i));
        }
        out.println(ans);
	}
}
