package chelper;





import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import sun.misc.IOUtils;

import java.io.*;
import java.util.*;

public class P {
    String dir = "c:\\Downloads\\Chrome\\preec2013input\\input\\PROBLEMSET\\input\\P\\";

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("!!!!!!!!!!!! " + i);
                int res = solve(i);

                PrintWriter printWriter = new PrintWriter(new FileWriter(dir + "P" + i + ".out"));

                printWriter.print(res);

                printWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int solve(int test) throws IOException {
        RandomAccessFile file = new RandomAccessFile(dir + test + ".wav", "r");

        byte[] bytes = new byte[(int) file.length()];
        file.read(bytes);

        List<List<Integer>> beeps = new ArrayList<List<Integer>>();

        ArrayList<Integer> buffer = new ArrayList<Integer>();
        for (int i = 100; i < bytes.length; i++) {
            if (bytes[i] == bytes[i - 1] && bytes[i] == bytes[i - 2] && bytes[i] == -128) {
                if (buffer.size() > 0) {
                    beeps.add(buffer);
                    buffer = new ArrayList<Integer>();
                }
            } else {
                int b = bytes[i];
                if (b < 0) {
                    b = b + 256;
                }
                buffer.add(b);
            }
        }

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for (List<Integer> beep : beeps) {
            int res = analyse(beep);
            if (!map.containsKey(res)) {
                map.put(res, 0);
            }
            map.put(res, map.get(res) + 1);
        }

        System.out.println(map.size());

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            //System.out.println(e.getKey() + ": " + e.getValue());
        }

        Set<List<Integer>> set = new HashSet<List<Integer>>();

        set.addAll(beeps);

        System.out.println("tr" + set.size());

        return map.size();
    }

    int analyse(List<Integer> beep) {
        int counter = 0;

        boolean prevDeltaNeg = false;
        int prev = 0;

        for (int i : beep) {
            int delta = i - prev;

            if (delta >= 0 && prevDeltaNeg) {
                counter++;
            }

            prevDeltaNeg = delta < 0;

            prev = i;
        }

        double time = beep.size() / 44100.0;

        int freq = (int) Math.round(counter / time / 100) * 100;

        return counter;
    }
}
