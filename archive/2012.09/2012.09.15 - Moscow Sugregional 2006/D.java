package chelper;

import abrackadabra.io.Reader;
import java.io.PrintWriter;

public class D {
	public void solve(int testNumber, Reader in, PrintWriter out) {

        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        int answer = 0;
        for(int i = 1; i <n - 1;++i){
            int  t = Math.max(a[i] - a[i - 1], a[i + 1] - a[i]);
            int need = 1;
            long power = 10;
            while(t > power){
                ++need;
                power *= 10;
            }
            answer += String.valueOf(a[i]).length() - need;
        }

        out.println(answer);

	}
}
