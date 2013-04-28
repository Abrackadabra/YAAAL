package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;

public class K {
    int[] teacher;
    int[][] top;
    int n;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        teacher = new int[n];
        top = new int[n][];
        for(int i = 0; i < n; ++i){
            teacher[i] = in.nextInt();
            top[i] = in.nextIntArray(n - 1);
            for(int j = 0;  j < top[i].length; ++j)
                top[i][j] --;

        }

        allowed = new boolean[n][3];

        for(int i = 0; i < n; ++i){
            for(int j = 0; j < 3; ++j){
                allowed[i][j] = true;
            }
            allowed[i][teacher[i]] = false;
        }

        int r = n - 1;
        int l = -1;
        while(r - l > 1){
            int m = (r + l) >> 1;

            if(possible(m))
                r = m;
            else
                l = m;

        }
        out.println(r);
    }

    boolean[][] allowed;

    ArrayList<HashSet<Integer>> badFriends;

    private boolean possible(int t) {
        badFriends = new ArrayList<HashSet<Integer>>(n);


        for(int i = 0; i < n; ++i){
            badFriends.add(new HashSet<Integer>());
        }

        for(int i = 0; i < n; ++i){
            for(int j = t; j < n - 1; ++j){
                badFriends.get(i).add(top[i][j]);
                badFriends.get(top[i][j]).add(i);

            }
        }


        return rec(0);

    }

    private boolean rec(int z) {
        if(z == n)
            return true;
        for(int j = 0; j < 3; ++j){
            if(allowed[z][j]){
                ArrayList<Integer> rollback = new ArrayList<Integer>();

                boolean res = true;
                for(int to: badFriends.get(z)){
                    if(allowed[to][j]){
                        allowed[to][j] = false;

                        rollback.add(to);
                        if(!allowed[to][0] && !allowed[to][1] && !allowed[to][2]){
                            res = false;
                            break;
                        }
                    }
                }

                if(res)
                    res = rec(z + 1);


                for(int to: rollback){
                    allowed[to][j] = true;
                }

                if(res)
                    return true;
            }
        }
        return false;
    }

}
