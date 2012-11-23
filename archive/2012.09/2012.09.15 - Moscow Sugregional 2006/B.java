package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.HashMap;

public class B {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        long n = in.nextLong();

        int res = 0;
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(0L, 0);
        map.put(1L, 0);
        map.put(2L, 1);
        map.put(3L, 2);

        if (n > 3) res++;
        while (n > 3) {
            res += n / 2;
            n -= n / 2;
        }
        res += map.get(n);

        out.println(res);
	}
}
