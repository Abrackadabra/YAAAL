package chelper;

import abrackadabra.io.Reader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class I {
    class Pair{
        int from,to;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (from != pair.from) return false;
            if (to != pair.to) return false;

            return true;
        }

        Pair(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
        }
    }
    int finish;
    HashSet<Integer>[] g;
    int n;
    HashSet<Pair> answer = new HashSet<Pair>();

    public void solve(int testNumber, Reader in, PrintWriter out) {
        n = in.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        int wl[] = new int[n];
        int wr[] = new int[n];
        Arrays.fill(wl, -1);
        Arrays.fill(wr, -1);


        g = new HashSet[n * 4 + 2];

        ArrayList<Integer> interestL = new ArrayList<Integer>(), interestR = new ArrayList<Integer>();
        for(int i = 0; i < n * 4 + 2; ++i)
            g[i] = new HashSet<Integer>();
        int start = 4 * n;
        finish = start + 1;
        int need = 0;
        for(int i = 0; i < n; ++i){
            l[i] = in.nextInt() - 1;
            if(wl[l[i]] == -1)
                wl[l[i]] = i;
            else{
                ++need;
                g[start].add(l[i]);
                g[l[i]].add(n + wl[l[i]]);
                g[l[i]].add(n + i);
                interestL.add(i);
                interestL.add(wl[l[i]]);
            }
            r[i] = in.nextInt() - 1;
            if(wr[r[i]] == -1)
                wr[r[i]] = i;
            else{
                g[r[i] + 3 * n].add(finish);
                g[wr[r[i]] + 2 * n].add(r[i] + 3 * n);
                g[2 * n + i].add(r[i] + 3 * n);

                interestR.add(i);
                interestR.add(wr[r[i]]);
            }
        }

        for (Integer left : interestL) {
            for (Integer right : interestR) {
                //out.println(left + " " + right);
                if(Math.abs(left - right) <= 1){
                    g[left + n].add(2 * n + right);
                }
            }
        }



        for(int i = 0; i < need; ++i){
            if(!dfs(start, new boolean[4 * n + 2])){
                out.println(-1);
                return;
            }
        }

        out.println(need);

        for(Pair ans: answer){
            out.println(ans.from + 1 + " " + (ans.to + 1));
        }


	}

    private boolean dfs(int vertex, boolean[] used) {
        if(vertex == finish)
            return true;
        if(used[vertex])
            return false;
        used[vertex] = true;
        for (Integer to : g[vertex]) {
            if(dfs(to, used)){
                if(vertex / n == 1 && to / n == 2){
                    answer.add(new Pair(vertex %n, to % n));
                }
                if(vertex / n == 2 && to / n == 1){
                    answer.remove(new Pair(to %n, vertex % n));
                }
                g[vertex].remove(to);
                g[to].add(vertex);
                return true;
            }
        }
        return false;
    }


}
