package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 22.01.12
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class FenwickMod {
    final int[] a;
    final int mod;

    public FenwickMod(int n, int mod) {
        a = new int[n];
        this.mod = mod;
    }

    public void add(int x, int y) {
        y %= mod;
        if (y < 0) y += mod;
        for (int i = x; i < a.length; i |= i + 1) {
            a[i] += y;
            if (a[i] >= mod) a[i] -= mod;
        }
    }

    public int getSum(int x) {
        if (x >= a.length) x = a.length - 1;
        int ret = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            ret += a[i];
            if (ret >= mod) ret -= mod;
        }
        return ret;
    }

    public int getSum(int l, int r) {
        int ret = getSum(r - 1) - getSum(l - 1);
        if (ret < 0) ret += mod;
        return ret;
    }
}
