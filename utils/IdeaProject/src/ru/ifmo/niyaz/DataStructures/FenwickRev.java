package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 22.01.12
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class FenwickRev {
    int[] a;

    public FenwickRev(int n) {
        a = new int[n];
    }

    public void add(int x, int y) {
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            a[i] += y;
        }
    }

    public int getElement(int x) {
        int ret = 0;
        for (int i = x; i < a.length; i |= i + 1) {
            ret += a[i];
        }
        return ret;
    }

    public void add(int left, int right, int y) {
        add(left - 1, -y);
        add(right - 1, y);
    }

}
