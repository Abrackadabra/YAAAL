package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.*;

public class Sequence {
    int sillySolve(int n){
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add(1);
        for(int i = 0; i < n; ++i){
            ArrayList<Integer> b = new ArrayList<Integer>();
            for(int j = 0; j <a.size() - 1 ;++j){
                b.add(a.get(j));
                b.add(a.get(j) + a.get(j + 1));
            }
            b.add(a.get(a.size() - 1));
            a = b;
        }


        int ans = 0;
        for(int c: a){
            if(c==n)
                ++ans;
        }
        return ans;
    }
    long phi(long n){
        long ans = n;
        for(long i = 2; i * i <= n; ++i){
            if(n % i == 0){
                ans /= i;
                ans *= (i-1);
                while(n % i== 0)
                    n/=i;
            }


        }
        if(n != 1){
            ans /= n;
            ans *= (n - 1);
        }
        return ans;
    }
	public void solve(int testNumber, Reader in, PrintWriter out) {
        long n = in.nextLong();
        if(n == 1)
            out.println(2);
        else
            out.println(phi(n));
	}
}
