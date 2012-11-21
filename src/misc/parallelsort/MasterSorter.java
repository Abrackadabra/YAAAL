package misc.parallelsort;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 19/11/12
 * Time: 23:47
 */
public class MasterSorter {
    public void sort(String[] strings, Comparator<String> comparator, int maxThreadCount) {
        AtomicInteger threadsLeft = new AtomicInteger(maxThreadCount - 1);
        Thread sorter = new SlaveSorter(strings, comparator, threadsLeft, 0, strings.length);
        sorter.start();
        try {
            sorter.join();
        } catch (InterruptedException e) {
            System.err.println("Sorting interrupted");
            System.exit(1);
        }
    }
}
