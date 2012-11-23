package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Wheel {
    int n, r;
    ArrayList<Integer> letterCounts =  new ArrayList<Integer>();
    double [] q;
    private double eps = 1e-9;

    int size(int mask){
        return Integer.bitCount(mask);
    }
    HashMap<Integer, Double> cache = new HashMap<Integer, Double>();
    double pow(double a, double b){
        if(a < eps && b < eps)
            return 1;
        else
            return Math.pow(a, b);
    }
    double probability(int t, int unguessed,  int unknown, int boxes){
        double  pow = pow(q[t], 1.0 / (1 + boxes - unknown));
        return (1 - pow) *  unknown / unguessed + pow * pow(1 - 1.0 / unguessed, unguessed - unknown);
    }

    double ans(int mask, int missedLetters){

        int key = mask + (missedLetters << 13);
        if(cache.containsKey(key))
            return cache.get(key);
        int number = missedLetters % n;
        if(mask == (1<<letterCounts.size()) - 1)
            return number == r ? 1 : 0;
        int size = size(mask);
        int unguessedLetters = 26 - missedLetters - size;

        int boxes = 0;
        for(int i = 0;  i < letterCounts.size(); ++i){
            if(((1<<i) & mask) == 0)
                boxes += letterCounts.get(i);
        }
        double p = probability(number, unguessedLetters, letterCounts.size() - size, boxes);

        double ans = (p > 1 - eps) ? 0 : ((1 - p) * ans(mask, missedLetters + 1));


        int letters = letterCounts.size() - size;

        for(int i = 0;  i < letterCounts.size(); ++i){
            if(((1<<i) & mask) == 0) {
                ans += ans(mask + (1<<i), missedLetters) * p / letters;
            }
        }



        cache.put(key, ans);
        return ans;
    }
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        r = in.nextInt() - 1;
        String s = in.nextString();
        int[] c = new int[256];
        for(char t: s.toCharArray()){
            c[t]++;
        }
        for(int i = 0; i < 256; ++i){
            if(c[i] > 0)
                letterCounts.add(c[i]);
        }
        Collections.sort(letterCounts);
        q = new double[n];
        for(int i = 0; i < n; ++i){
            q[i] = in.nextDouble();
        }

        out.println(ans(0, 0));
	}
}
