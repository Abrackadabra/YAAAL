package abrackadabra.structure;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 22/11/12
 * Time: 18:01
 */
public class Counter<K> extends HashMap<K, Long> {
    @Override
    public Long get(Object key) {
        if (containsKey(key))
            return super.get(key);
        return 0L;
    }

    public void add(K key) {
        add(key, 1);
    }

    public void add(K key, long delta) {
        put(key, get(key) + delta);
    }
}
