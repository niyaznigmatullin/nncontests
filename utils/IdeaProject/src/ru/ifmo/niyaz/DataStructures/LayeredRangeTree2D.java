package ru.ifmo.niyaz.DataStructures;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

public class LayeredRangeTree2D {
    private final int[][] t;
    private int n;

    public LayeredRangeTree2D(int[] a) {
        n = 1;
        while (n < a.length) n *= 2;
        t = new int[2 * n][];
        for (int i = 0; i < a.length; i++) {
            t[i + n] = new int[]{a[i]};
        }
        for (int i = n - 1; i > 0; i--) {
            t[i] = ArrayUtils.merge(t[i + i], t[i + i + 1]);
        }
    }

    public int count(int left, int right, int from, int to) {
        left += n;
        right += n;
        --right;
        int ans = 0;
        while (left <= right) {
            if ((left & 1) == 1) {
                ans += search(t[left++], from, to);
            }
            if ((right & 1) == 0) {
                ans += search(t[right--], from, to);
            }
            left >>>= 1;
            right >>>= 1;
        }
        return ans;
    }

    private static int search(int[] a, int from, int to) {
        return ArrayUtils.lowerBound(a, to) - ArrayUtils.lowerBound(a, from);
    }
}
