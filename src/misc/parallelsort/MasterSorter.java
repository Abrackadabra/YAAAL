package misc.parallelsort;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 19/11/12
 * Time: 23:47
 */
public class MasterSorter {
    private ExecutorService executorService;

    public void sort(ArrayList<String> strings, Comparator<String> comparator, int threadCount) {
        for (int i = 2; true; i <<= 1) {
            executorService = Executors.newFixedThreadPool(threadCount);
            for (int l = 0; l < strings.size(); l += i) {
                executorService.execute(new SlaveSorter(strings, comparator, l, l + i));
            }
            try {
                executorService.shutdown();
                while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i >= strings.size()) {
                break;
            }
        }
    }
}
