package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int people = in.nextInt();
        int candidates = in.nextInt();
        if(people == 0)
            throw new UnknownError();

        int[][] pos = new int[people][candidates];

        for(int i = 0; i < people; ++i){

            int[] ar = in.nextIntArray(candidates);

            for(int j = 0; j < candidates; ++j){
                pos[i][ar[j]] = j;
            }

        }

        int curLeader = 0;

        for(int i = 1; i < candidates; ++i){
            if(better(i, curLeader, pos))
                curLeader = i;
        }
        out.print("Case " + testNumber + ": ");

        for(int i = 0; i < candidates; ++i){
            if(i == curLeader)
                continue;
            if(!better(curLeader, i, pos)){
                out.println("No Condorcet winner");
                return;
            }
        }
        out.println(curLeader);

    }

    boolean better(int a, int b, int[][] pos){
        int cnt1 = 0;
        for(int i = 0; i < pos.length; ++i){
            if(pos[i][a] < pos[i][b])
                ++cnt1;
        }
        return cnt1 > pos.length - cnt1;
    }
}
