package ru.ifmo.niyaz.DataStructures;

/**
 * Created by niyaz on 3/3/2015.
 */
public class FenwickKth {
    int[] a;

    public FenwickKth(int n) {
        a = new int[Integer.highestOneBit(n) << 1];
    }

    public void add(int x, int y) {
        for (int i = x; i < a.length; i |= i + 1) {
            a[i] += y;
        }
    }

    public int getSum(int x) {
        if (x >= a.length) x = a.length - 1;
        int ret = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            ret += a[i];
        }
        return ret;
    }

    public int getSum(int l, int r) {
        return getSum(r - 1) - getSum(l - 1);
    }

    public int getKth(int k) {
        int l = 0;
        int r = a.length;
        while (l < r - 1) {
            int mid = l + r >> 1;
            if (a[mid - 1] >= k) {
                r = mid;
            } else {
                k -= a[mid - 1];
                l = mid;
            }
        }
        return l;
    }

    public int getCapacity() {
        return a.length;
    }

}
