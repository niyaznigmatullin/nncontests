package ru.ifmo.niyaz.DataStructures;

/**
 * Created by Niyaz Nigmatullin on 3/4/2015.
 */
public class FenwickMultiSum {
    long[] a;
    long[] b;

    public FenwickMultiSum(int n) {
        a = new long[n];
        b = new long[n];
    }

    public void add(int x, long d) {
        addSuffix(a, x, d);
        addSuffix(b, x, d * (1 - x));
    }

    private static void addSuffix(long[] a, int x, long d) {
        for (int i = x; i < a.length; i |= i + 1) {
            a[i] += d;
        }
    }

    public void add(int left, int right, long d) {
        add(left, d);
        add(right, -d);
    }

    public long getSum(int x) {
        return getPrefix(a, x) * x + getPrefix(b, x);
    }

    public long getSum(int left, int right) {
        return getSum(right - 1) - getSum(left - 1);
    }

    private static long getPrefix(long[] a, int x) {
        long ret = 0;
        for (int i = x; i >= 0; i = ((i + 1) & i) - 1) {
            ret += a[i];
        }
        return ret;
    }
}
