package ru.ifmo.niyaz.DataStructures;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 25.12.12
 * Time: 1:07
 * To change this template use File | Settings | File Templates.
 */
public class FenwickMin {
    int[] min;

    public FenwickMin(int n) {
        min = new int[n];
        Arrays.fill(min, Integer.MAX_VALUE);
    }

    public void setAndMin(int x, int y) {
        for (int i = x; i < min.length; i |= i + 1) {
            min[i] = Math.min(min[i], y);
        }
    }

    public int getMin(int x) {
        x = Math.min(x, min.length - 1);
        int ret = Integer.MAX_VALUE;
        for (int i = x; i >= 0; i = (i & i + 1) - 1) {
            ret = Math.min(ret, min[i]);
        }
        return ret;
    }
}
