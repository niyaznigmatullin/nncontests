package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 22.01.12
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class FenwickLong {
    long[] a;

    public FenwickLong(int n) {
        a = new long[n];
    }

    public void add(int x, long y) {
        for (int i = x; i < a.length; i |= i + 1) {
            a[i] += y;
        }
    }

    public long getSum(int x) {
        if (x >= a.length) x = a.length - 1;
        long ret = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            ret += a[i];
        }
        return ret;
    }

    public long getSum(int l, int r) {
        return getSum(r - 1) - getSum(l - 1);
    }
}
