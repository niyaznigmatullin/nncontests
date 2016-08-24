package ru.ifmo.niyaz.DataStructures;

/**
 * Created by niyaz on 29.04.16.
 */
public class SparseTableMin {

    int[][] min;
    int[] h;

    public SparseTableMin(int[] a) {
        h = new int[a.length + 1];
        h[1] = 0;
        for (int i = 2; i < h.length; i++) {
            h[i] = h[i >> 1] + 1;
        }
        min = new int[h[h.length - 1] + 1][a.length];
        for (int i = 0; i < a.length; i++) {
            min[0][i] = a[i];
        }
        for (int i = 1; i < min.length; i++) {
            int[] prev = min[i - 1];
            int[] mini = min[i];
            for (int v = 0; v < a.length; v++) {
                if (v + (1 << (i - 1)) < a.length) {
                    mini[v] = Math.min(prev[v], prev[v + (1 << (i - 1))]);
                } else {
                    mini[v] = prev[v];
                }
            }
        }
    }

    public int getMin(int left, int right) {
        if (right <= left) {
            return Integer.MAX_VALUE;
        }
        int k = h[right - left];
        int[] mink = min[k];
        return Math.min(mink[left], mink[right - (1 << k)]);
    }
}
