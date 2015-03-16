package ru.ifmo.niyaz.DataStructures;

/**
 * Created by niyaz on 3/4/2015.
 */
public class StupidSummator {
    long[] a;

    public StupidSummator(int n) {
        a = new long[n];
    }

    public void add(int left, int right, long d) {
        for (int i = left; i < right; i++) a[i] += d;
    }

    public long getSum(int left, int right) {
        long ret = 0;
        for (int i = left; i < right; i++) ret += a[i];
        return ret;
    }
}
