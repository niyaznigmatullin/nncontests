package lib.test.on2013_09.on2013_09_03_.Rhymes;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rhymes {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] all = ArrayUtils.sortAndUnique(a);
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.binarySearch(all, a[i]);
        }
        short[][] next = new short[n + 1][all.length];
        for (int i = 0; i < all.length; i++) {
            next[n][i] = (short) n;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < all.length; j++) {
                next[i][j] = next[i + 1][j];
            }
            next[i][a[i]] = (short) i;
        }
        int[] dp = new int[n + 1];
        int[] c1 = new int[n + 1];
        int[] c2 = new int[n + 1];
        int[] c3 = new int[n + 1];
        int[] c4 = new int[n + 1];
        Arrays.fill(c1, -1);
        for (int i = 0; i < n; i++) {
            int val = dp[i];
            for (int j = i + 1; j < n; j++) {
                if (a[i] == a[j]) {
                    int c = a[i];
                    int k = next[j + 1][c];
                    if (k < n) {
                        int t = next[k + 1][c];
                        if (t < n) {
                            update(dp, c1, c2, c3, c4, i, val, j, k, t);
                        }
                    }
                } else {
                    {
                        int k = next[i + 1][a[i]];
                        if (k < j) {
                            int t = next[j + 1][a[j]];
                            if (t < n) {
                                update(dp, c1, c2, c3, c4, i, val, k, j, t);
                            }
                        }
                    }
                    {
                        int k = next[j + 1][a[i]];
                        if (k < n) {
                            int t = next[k + 1][a[j]];
                            if (t < n) {
                                update(dp, c1, c2, c3, c4, i, val, j, k, t);
                            }
                        }
                    }
                    {
                        int k = next[j + 1][a[j]];
                        if (k < n) {
                            int t = next[k + 1][a[i]];
                            if (t < n) {
                                update(dp, c1, c2, c3, c4, i, val, j, k, t);
                            }
                        }
                    }
                }
            }
            if (dp[i + 1] < dp[i]) {
                dp[i + 1] = dp[i];
                c1[i + 1] = -1;
            }
        }
        List<int[]> ans = new ArrayList<>();
        for (int i = n; i > 0; ) {
            if (c1[i] < 0) {
                --i;
                continue;
            }
            ans.add(new int[]{c1[i], c2[i], c3[i], c4[i]});
            i = c1[i];
        }
        Collections.reverse(ans);
        if (ans.size() != dp[n]) throw new AssertionError();
        out.println(dp[n]);
        for (int[] f : ans) {
            out.println(f[0] + 1 + " " + (f[1] + 1) + " " + (f[2] + 1) + " " + (f[3] + 1));
        }
    }

    private void update(int[] dp, int[] c1, int[] c2, int[] c3, int[] c4, int i, int val, int j, int k, int t) {
        int ni = t + 1;
        if (dp[ni] < val + 1) {
            dp[ni] = val + 1;
            c1[ni] = i;
            c2[ni] = j;
            c3[ni] = k;
            c4[ni] = t;
        }
    }
}
