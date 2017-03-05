package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;

public class Sortish {
    public long ways(int sortedness, int[] seq) {
        int n = seq.length;
        int zeros = 0;
        ArrayUtils.reverse(seq);
        boolean[] was = new boolean[n];
        for (int i = 0; i < n; i++) {
            --seq[i];
            if (seq[i] == -1) {
                zeros++;
            } else {
                was[seq[i]] = true;
            }
        }
        int[] a = new int[zeros];
        zeros = 0;
        for (int i = 0; i < n; i++) {
            if (!was[i]) a[zeros++] = i;
        }
        int[] id = new int[n];
        int[] empty = new int[zeros];
        Arrays.fill(id, -1);
        zeros = 0;
        for (int i = 0; i < n; i++) {
            if (seq[i] == -1) {
                id[i] = zeros;
                empty[zeros++] = i;
            }
        }
        cost = new int[zeros][zeros];
        for (int i = 0; i < zeros; i++) {
            int invs = 0;
            for (int j = 0; j < a[i]; j++) {
                if (was[j]) ++invs;
            }
            for (int j = 0; j < n; j++) {
                if (seq[j] == -1) {
                    cost[i][id[j]] = invs;
                } else {
                    if (seq[j] < a[i]) {
                        --invs;
                    } else {
                        ++invs;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (seq[i] == -1) continue;
            for (int j = i + 1; j < n; j++) {
                if (seq[j] == -1) continue;
                if (seq[i] > seq[j]) --sortedness;
            }
        }
        int half = zeros / 2;
        int[] inv1 = getInversions(half);
        int[] inv2 = getInversions(zeros - half);
        int[] a1 = new int[inv1.length];
        int[] a2 = new int[inv2.length];
        long ans = 0;
        Sortish.was = new boolean[zeros];
        for (int mask = 0; mask < 1 << zeros; mask++) {
            if (Integer.bitCount(mask) != zeros - half) continue;
            int[] left = new int[half];
            int[] right = new int[zeros - half];
            int pos1 = 0;
            int pos2 = 0;
            for (int i = 0; i < zeros; i++) {
                if (((mask >>> i) & 1) == 0) {
                    left[pos1++] = i;
                } else {
                    right[pos2++] = i;
                }
            }
            int invs = 0;
            for (int i = 0; i < half; i++) {
                for (int j = 0; j < zeros - half; j++) {
                    if (a[left[i]] > a[right[j]]) ++invs;
                }
            }
            int leftSortedness = sortedness - invs;
            cc = 0;
            add = 0;
            go(0, half, left, inv1, 0, a1);
            cc = 0;
            add = half;
            go(0, zeros - half, right, inv2, 0, a2);
            Arrays.sort(a1);
            Arrays.sort(a2);
            for (int i = 0, j = a2.length - 1, k = a2.length - 1; i < a1.length; i++) {
                while (j >= 0 && a1[i] + a2[j] > leftSortedness) --j;
                while (k >= 0 && a1[i] + a2[k] >= leftSortedness) --k;
                ans += j - k;
            }
        }
        return ans;
    }

    static int cc;
    static int add;
    static boolean[] was;
    static int[][] cost;

    static void go(int x, int n, int[] array, int[] inv, int got, int[] out) {
        if (x == n) {
            out[cc] = inv[cc] + got;
            cc++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            was[i] = true;
            go(x + 1, n, array, inv, got + cost[array[i]][x + add], out);
            was[i] = false;
        }
    }

    static int[] getInversions(int n) {
        int m = 1;
        for (int i = 2; i <= n; i++) m *= i;
        int[] ret = new int[m];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int cc = 0;
        do {
            int cur = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (p[i] > p[j]) ++cur;
                }
            }
            ret[cc++] = cur;
        } while (ArrayUtils.nextPermutation(p));
        if (cc != ret.length) throw new AssertionError();
        return ret;
    }
}
