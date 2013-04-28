package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        //out.println(n / 3);
        for(int d = 1; d < n; d *= 3){
            solve(d, n / d / 3);
        }

        out.println(output.size());

        for (String s : output) {
            out.println(s);
        }
    }

    ArrayList<String> output =  new ArrayList<String>();

    private void solve( int d, int times) {
        for(int i = 0; i < d; ++i){
            for(int j = 0; j < d; ++j){
                int k = (i + j) % d;
                print(i, j, k,  d, times);
            }
        }
    }

    private void print(int i, int j, int k, int d, int times) {

        for(int z = 0; z < times; ++z){
            output.add("" + (i + 1) + " " +(j + 1 + d) + " " + ( k + 1 + d + d));
            i += 3 * d;
            j += 3 * d;
            k += 3 * d;
        }
    }
}
