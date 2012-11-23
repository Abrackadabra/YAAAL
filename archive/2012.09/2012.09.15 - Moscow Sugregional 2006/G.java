package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class G {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        String s = in.nextString();
        int curNumber = 0;
        int minNumber = 0;
        for(int i = 0; i < s.length(); ++i){
            curNumber += (s.charAt(i) - '0') - 1;
            if(i == s.length() - 1)
                ++curNumber;
            minNumber = Math.min(curNumber, minNumber);
        }

        StringBuilder answer = new StringBuilder();
        if(minNumber<= 0){
            while (minNumber < -6){
                curNumber += 6;
                minNumber += 6;
                answer.append('7');
            }
            if(minNumber < 0){
                answer.append(-minNumber + 1);
                curNumber += -minNumber;
                minNumber = 0;
            }

        }
        answer.append(s);
        for(int i = 0; i < curNumber; ++i){
            answer.append(0);
        }

        out.println(answer);
	}
}
