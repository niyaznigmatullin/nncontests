package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int z = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = in.nextInt();
        int[] mask = new int[n];
        int[] countMasks = new int[1 << k];
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            for (int j = 0; j < d; j++) {
                mask[i] |= 1 << (in.nextInt() - 1);
            }
            countMasks[mask[i]]++;
        }
        int[][] f = new int[1 << k][];
        for (int i = 0; i < 1 << k; i++) {
            f[i] = new int[countMasks[i]];
        }
        int[] tail = new int[1 << k];
        int[] head = new int[1 << k];
        int[] ans = new int[n];
        dp = new int[1 << k];
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && dp[(1 << k) - 1] < z) {
                int curMask = mask[j];
                while (tail[curMask] > head[curMask] && p[f[curMask][tail[curMask] - 1]] < p[j]) {
                    --tail[curMask];
                }
                f[curMask][tail[curMask]++] = j;
                ++j;
                if ((head[curMask] + 1 == tail[curMask]))
                    addDP(f, head, tail, p, curMask);
            }
            ans[i] = dp[(1 << k) - 1] >= z ? j : -1;
            int curMask = mask[i];
            if (head[curMask] < tail[curMask] && f[curMask][head[curMask]] == i) {
                ++head[curMask];
            }
            getDP(f, head, tail, p);
        }
        for (int i : ans) out.println(i);
    }

    static int[] dp;

    static int[] getDP(int[][] f, int[] head, int[] tail, int[] p) {
        Arrays.fill(dp, 0);
        for (int subMask = 1; subMask < f.length; subMask++) {
            addDP(f, head, tail, p, subMask);
        }
        return dp;
    }

    static final int INF = (1 << 30) - 5;

    static int[] addDP(int[][] f, int[] head, int[] tail, int[] p, int subMask) {
        if (head[subMask] >= tail[subMask]) {
            return dp;
        }
        int val = p[f[subMask][head[subMask]]];
        int nMask = (f.length - 1) ^ subMask;
        for (int mask = nMask; ; mask = (mask - 1) & nMask) {
            dp[mask | subMask] = Math.max(dp[mask | subMask], add(val, dp[mask]));
            if (mask == 0) break;
        }
        return dp;
    }

    static int[] removeDP(int[][] f, int[] head, int[] tail, int[] p, int subMask) {
        int val = p[f[subMask][head[subMask]]];
        ++head[subMask];
        int notSubMask = (f.length - 1) ^ subMask;
        for (int mask = notSubMask; ; mask = (mask - 1) & notSubMask) {
            int nMask = mask | subMask;
            if (dp[nMask] == add(val, dp[mask])) {
                dp[nMask] = 0;
                int thatBit = Integer.lowestOneBit(subMask);
                int sMask = nMask & ~thatBit;
                for (int q = sMask; ; q = (q - 1) & sMask) {
                    int m = q | thatBit;
                    if (head[m] < tail[m]) {
                        int val2 = p[f[m][head[m]]];
                        dp[nMask] = Math.max(dp[nMask], add(dp[nMask ^ m], val2));
                    }
                    if (q == 0) break;
                }
            }
            if (mask == 0) break;
        }
        return dp;
    }

    static int add(int a, int b) {
        a += b;
        if (a >= INF) a = INF;
        return a;
    }
}
