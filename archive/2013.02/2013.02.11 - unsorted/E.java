package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int levels = in.nextInt();
        int need = in.nextInt();
        int lift = in.nextInt();
        int liftPeriod = in.nextInt();
        int footPeriod = in.nextInt();

        int answer = (need - 1) * footPeriod;

        for (int goingUpTo = 1; goingUpTo <= need; goingUpTo++) {
            int time = (goingUpTo - 1) * footPeriod;
            for (int calledLiftAt = 1; calledLiftAt <= goingUpTo; calledLiftAt++) {
                int liftTime = (goingUpTo - calledLiftAt) * footPeriod;
                int liftAt = lift - (liftTime + liftPeriod - 1) / liftPeriod;
                int liftLeft = (liftPeriod - liftTime % liftPeriod) % liftPeriod;
                if (liftAt < goingUpTo) continue;
                time += liftLeft;
                time += liftPeriod * (liftAt - goingUpTo);
                time += (need - goingUpTo) * liftPeriod;

                answer = Math.min(answer, time);
            }
        }

        out.println(answer);
    }
}
