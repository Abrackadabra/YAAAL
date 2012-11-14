package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class Legion {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        out.println(f(in.nextLong()));
	}


    HashMap<Long, Long> cache = new HashMap<Long, Long>();
    private long f(long n) {
        if(cache.containsKey(n))
            return cache.get(n);
        if(n < 3)
            return 0;
        if(n==3)
            return 1;
        cache.put(n, f(n / 2) + f((n+1)/2));
        return f( n / 2) + f((n+1)/2);
    }
}
