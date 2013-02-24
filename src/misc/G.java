package misc;

import abrackadabra.io.InputReader;

import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 23/02/13
 * Time: 16:52
 */
public class G {
    public static void main(String[] args) {
        new G().run();
    }

    String dir = "c:\\Temp\\PROBLEMSET\\input\\G\\";

    static class Wagon {
        int l;
        int n;
        ArrayList<Integer> offsets = new ArrayList<Integer>();

        Wagon(InputReader in) {
            l = in.nextInt();
            n = in.nextInt();
            for (int i = 0; i < n; i++) {
                offsets.add(in.nextInt());
            }
        }
    }

    void run() {
        InputReader inputReader = new InputReader(dir + "UDoW.txt");

        ArrayList<Wagon> wagons = new ArrayList<Wagon>();
        int wagonCount = inputReader.nextInt();
        for (int i = 0; i < wagonCount; i++) {
            wagons.add(new Wagon(inputReader));
        }

        solve(1);
    }

    void solve(int testNumber) {
        RandomAccessFile file = null;
        byte[] bytes = null;
        try {
            file = new RandomAccessFile(dir + testNumber + ".wav", "r");
            bytes = new byte[(int) file.length()];
            file.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int n = bytes.length - 100;

        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = bytes[i + 100];
            if (data[i] < 0) {
                data[i] += 256;
            }
            data[i] -= 128;
        }

        ArrayList<ArrayList<Integer>> trains = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> buffer = new ArrayList<Integer>();
        for (int i = 2; i < data.length - 2; i++) {
            if (data[i] != 0 || data[i - 1] != 0 || data[i + 1] != 0) {
                buffer.add(data[i]);
            } else {
                ArrayList<Integer> q = new ArrayList<Integer>();
                q.addAll(buffer);
                if (q.size() > 100) {
                    trains.add(q);
                }
                buffer.clear();
            }
        }

        System.out.println(trains.size());

        System.out.println(trains.get(0).size() / 44100.0);
    }
}
