package chelper;

import abrackadabra.io.Reader;
import abrackadabra.StringUtils;

import java.io.PrintWriter;
import java.util.Arrays;

public class Strings {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        String s = in.nextString();
        out.println("Length: " + s.length());

        long a = System.nanoTime();
        out.println("Pref:             " + Arrays.toString(
                StringUtils.getPrefixFunction(s)
        ));
        out.println("Time: " + (System.nanoTime() - a) / 1e9);
        a = System.nanoTime();
        out.println("Z:                " + Arrays.toString(
                StringUtils.getZFunction(s)
        ));
        out.println("Time: " + (System.nanoTime() - a) / 1e9);
        a = System.nanoTime();
        out.println("Z to Pref:        " + Arrays.toString(
                StringUtils.ZFunctionToPrefFunction(StringUtils.getZFunction(s))
        ));
        out.println("Time: " + (System.nanoTime() - a) / 1e9);
        a = System.nanoTime();
        out.println("Pref to Z:        " + Arrays.toString(
                StringUtils.PrefixFunctionToZFunction(StringUtils.getPrefixFunction(s))
        ));
        out.println("Time: " + (System.nanoTime() - a) / 1e9);
        a = System.nanoTime();
        out.println("String from Pref: " +
                StringUtils.getStringFromPrefixFunction(StringUtils.getPrefixFunction(s))
        );
        out.println("Time: " + (System.nanoTime() - a) / 1e9);
        a = System.nanoTime();
        out.println("String from Z:    " +
                StringUtils.getStringFromZFunction(StringUtils.getZFunction(s))
        );
        out.println("Time: " + (System.nanoTime() - a) / 1e9);
    }
}
