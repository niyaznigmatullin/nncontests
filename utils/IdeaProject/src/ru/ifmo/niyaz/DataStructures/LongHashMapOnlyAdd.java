package DataStructures;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 20.05.12
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
public class LongHashMapOnlyAdd {
    final static Random rand = new Random();
    static int A = BigInteger.probablePrime(20, rand).intValue();
    static int B = BigInteger.probablePrime(17, rand).intValue();
    long[] map;
    int[] value;
    int size = 0;

    public LongHashMapOnlyAdd() {
        map = new long[B];
        value = new int[B];
        clear();
    }

    public LongHashMapOnlyAdd(int capacity) {
        ++capacity;
        capacity *= 3;
        capacity = Integer.numberOfTrailingZeros(Integer.highestOneBit(capacity)) + 1;
        B = BigInteger.probablePrime(capacity, rand).intValue();
        map = new long[B];
        value = new int[B];
        clear();
    }

    public void clear() {
        Arrays.fill(map, Long.MIN_VALUE);
    }

    public int put(long v, int val) {
        int key = hash(v);
        while (map[key] != Long.MIN_VALUE && map[key] != v) {
            key++;
            if (key == B) {
                key = 0;
            }
        }
        if (map[key] == Long.MIN_VALUE) {
            ++size;
        }
        map[key] = v;
        value[key] = val;
        return key;
    }

    public boolean containsKey(long v) {
        int key = hash(v);
        while (map[key] != Long.MIN_VALUE) {
            if (map[key] == v) {
                return true;
            }
            key++;
            if (key == B) {
                key = 0;
            }
        }
        return false;
    }

    public int get(long v) {
        int key = hash(v);
        while (map[key] != Long.MIN_VALUE) {
            if (map[key] == v) {
                return value[key];
            }
            key++;
            if (key == B) {
                key = 0;
            }
        }
        return Integer.MIN_VALUE;
    }

    int hash(long v) {
        return (int) (((v * A) % B + B) % B);
    }
}