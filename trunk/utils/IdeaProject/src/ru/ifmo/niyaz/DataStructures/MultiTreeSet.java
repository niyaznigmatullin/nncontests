package DataStructures;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 08.02.12
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public class MultiTreeSet<T> {


    NavigableMap<T, Integer> map;
    int size;

    public MultiTreeSet() {
        map = new TreeMap<T, Integer>();
    }

    public T lower(T t) {
        return map.lowerKey(t);
    }

    public T floor(T t) {
        return map.floorKey(t);
    }

    public T ceiling(T t) {
        return map.ceilingKey(t);
    }

    public T higher(T t) {
        return map.higherKey(t);
    }

    public T pollFirst() {
        T ret = map.firstKey();
        remove(ret);
        return ret;
    }

    public T pollLast() {
        T ret = map.lastKey();
        remove(ret);
        return ret;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public int getCount(T t) {
        Integer cnt = map.get(t);
        if (cnt == null) {
            cnt = 0;
        }
        return cnt;
    }

    public boolean add(T t) {
        Integer e = map.get(t);
        if (e == null) {
            e = 0;
        }
        map.put(t, e + 1);
        size++;
        return true;
    }

    public boolean remove(Object o) {
        Integer e = map.get(o);
        if (e == null) {
            return false;
        }
        if (e == 1) {
            map.remove(o);
        } else {
            map.put((T) o, e - 1);
        }
        size--;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Map.Entry<T, Integer> e : map.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                sb.append(e.getKey().toString()).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(']');
        return sb.toString();
    }
}
