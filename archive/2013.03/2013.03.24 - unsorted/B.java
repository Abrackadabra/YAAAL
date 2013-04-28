package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class B {

    int color[];
    Stack<Integer> stack;
    int time[];

    ArrayList<Integer>[] g;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        colors = 0;
        timer = 0;

        int n = in.nextInt();
        color = new int[n];
        stack = new Stack<Integer>();
        time = new int[n];
        Arrays.fill(time, -1);

        Arrays.fill(color, -1);

        g = new ArrayList[n];

        for(int i = 0; i < n; ++i)
            g[i] = new ArrayList<Integer>();

        int m = in.nextInt();
        for(int i = 0; i < m; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            g[a].add(b);
        }

        for(int i = 0; i < n; ++i){
            if(time[i] == -1)
                dfs(i);
        }

        HashSet<Integer> hasFrom = new HashSet<Integer>();
        HashSet<Integer> hasTo = new HashSet<Integer>();


        for(int i = 0; i < n; ++i){
            for(int to: g[i]){
                if(color[i] != color[to]){
                    hasFrom.add(color[i]);
                    hasTo.add(color[to]);
                }
            }
        }

        if (colors == 1) {
            out.println(0);
            return;
        }

        out.println(colors - Math.min(hasFrom.size(), hasTo.size()));
        //out.println("COl" + Arrays.toString(color));
    }

    int timer = 0;
    int colors = 0;

    void dfs(int v){

        ++timer;

       // System.out.println("in " + v);

        int startTimer = timer;
        time[v] = timer;

        stack.add(v);

        for(int to: g[v]){
            if(time[to] == -1){
                dfs(to);
            }

            if(color[to] == -1)
                time[v] = Math.min(time[v], time[to]);
        }

        if(time[v] == startTimer) {
            int x = -17;
            do {
                x = stack.peek();
                stack.pop();
                color[x] = colors;
               // System.out.println("print " + x + " to " + " " + colors );
            }
            while (x != v);

            ++colors;
        }

       // System.out.println("out " + v);

    }
}
