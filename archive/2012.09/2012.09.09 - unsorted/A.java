package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class A {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        BigInteger bi = new BigInteger(in.nextString());
        int l = in.nextInt();
        int MAGIC = 20;
        int[] precalc = new int[(1<<MAGIC)+1];
        for(int  i = 1; i<= 1<< MAGIC; ++i){
            int j = i;
            int cnt = 0;
            while(j % 2 == 0){
                ++cnt;
                j/=2;
            }
            precalc[i] = cnt + 1;
        }

        int mod = bi.mod(BigInteger.valueOf(1<< MAGIC)).intValue();
        for(int i = 0; i < l; ++i){
            if(mod!=0){
                out.print(precalc[mod] + " ");
            }
            else {
                BigInteger cur = bi.add(BigInteger.valueOf(i));
                int t = 0;
                while(!cur.testBit(t)){
                    ++t;
                }
                out.print(t + 1 + " ");

            }
            mod++;
            mod %= 1<< MAGIC;
        }
	}
}
