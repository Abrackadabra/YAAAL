package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int quota = in.nextInt();
        if(n == 0 && quota == 0)
            throw new UnknownError();

        ArrayList<Integer> weights = new ArrayList<Integer>();

        out.print("Case " + testNumber + ":");
        for(int i = 0; i < n; ++i){
            int weight = in.nextInt();
            for(int j = 0, s = in.nextInt(); j < s; ++j){
                weights.add(weight);
            }
        }


        for(int i = 0; i < weights.size(); ++i){
            if(i != 0 && weights.get(i).equals(weights.get(i - 1)))
                continue;

            long [] ways = new long[quota];

            ways[0] = 1;
            for(int j = 0; j < weights.size(); ++j) {
                if(i == j)
                    continue;
                for(int k = quota - 1; k >= 0; --k){
                    if(k + weights.get(j) < quota){
                        ways[k + weights.get(j)] += ways[k];
                    }
                }
            }

            long sum = 0;
            for(int t = Math.max(quota - weights.get(i), 0); t < quota; ++t)
                sum += ways[t];

            out.print(" " + sum);
        }
        out.println();


    }
}
