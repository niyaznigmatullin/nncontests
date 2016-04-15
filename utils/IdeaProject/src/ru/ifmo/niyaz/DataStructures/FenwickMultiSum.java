package ru.ifmo.niyaz.DataStructures;

/**
 * Created by Niyaz Nigmatullin on 3/4/2015.
 * Modified by Niyaz Nigmatullin on 11/16/2015.
 */
public class FenwickMultiSum {
    FenwickLong fX;
    FenwickLong f;

    public FenwickMultiSum(int n) {
        fX = new FenwickLong(n);
        f = new FenwickLong(n);
    }

    public void add(int x, long d) {
        f.add(x, d);
        fX.add(x, d * (1 - x));
    }

    public void add(int left, int right, long d) {
        add(left, d);
        add(right, -d);
    }

    public long getSum(int x) {
        return f.getSum(x) + fX.getSum(x) * x;
    }

    public long getSum(int left, int right) {
        return getSum(right - 1) - getSum(left - 1);
    }
}
