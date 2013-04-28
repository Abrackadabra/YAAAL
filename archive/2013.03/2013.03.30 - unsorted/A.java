package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;

public class A {
    class Entry implements Comparable<Entry> {
        char from, to;

        Entry(char from, char to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        int cost;

        @Override
        public int compareTo(Entry o) {
            return o.cost - cost;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        if(n == 0)
            throw new UnknownError();
        String from = in.nextString(), to = in.nextString();

        Entry[] entries = new Entry[n];
        long currentSum = 0;
        for(int i = 0; i < n; ++i){
            entries[i] = new Entry(from.charAt(i), to.charAt(i), in.nextInt());
            if(entries[i].from == '1')
                currentSum += entries[i].cost;
        }

        Arrays.sort(entries);

        long bestAnswer = Long.MAX_VALUE;
        long currentAnswer = 0;



        for(int i = 0; i < n ; ++i) {
            bestAnswer = Math.min(bestAnswer, currentAnswer + stupidSolution(entries, i, currentSum));

            if(entries[i].from == '0')
                continue;

            currentSum -= entries[i].cost;
            currentAnswer += currentSum;
            entries[i].from = '0';
        }
        bestAnswer = Math.min(bestAnswer, currentAnswer + stupidSolution(entries, n, currentSum));


        out.println("Case " + testNumber + ": " + bestAnswer);


    }

    private long stupidSolution(Entry[] entries, int k, long currentSum) {
        long ans = 0;
        for(int i = k; i < entries.length; ++i){
            if(entries[i].from == '1' && entries[i].to == '0') {
                currentSum -= entries[i].cost;
                ans += currentSum;
            }
        }

        for(int i = entries.length - 1; i >= 0; --i){
            if(entries[i].from == '0' && entries[i].to == '1') {
                currentSum += entries[i].cost;
                ans += currentSum;
            }
        }

        return ans
                ;
    }

}
