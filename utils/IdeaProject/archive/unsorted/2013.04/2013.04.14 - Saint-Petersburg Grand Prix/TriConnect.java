package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class TriConnect {
    static long[] dp = new long[76];

    static {
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 0;
            for (int a = 1; a < i; a++) {
                for (int b = a + 1; b < i; b++) {
                    dp[i] += dp[a - 1] * dp[b - a - 1] * dp[i - b - 1];
                }
            }
        }
//        System.err.println(dp[dp.length - 1]);
    }

    static long getNumber(int[][] id, int[][] a) {
        if (a.length == 0) {
            return 0;
        }
        int[] first = a[0].clone();
        int[] cid = null;
        int cidPos = -1;
        all:
        for (int i = 0; i < id.length; i++) {
            int[] e = id[i];
            for (int j = 0; j < e.length; j++) {
                if (e[j] == first[0]) {
                    cid = e.clone();
                    cidPos = i;
                    break all;
                }
            }
        }
        int[] all = ArrayUtils.sortAndUnique(cid);
        for (int i = 0; i < cid.length; i++) {
            cid[i] = Arrays.binarySearch(all, cid[i]);
        }
        for (int i = 0; i < first.length; i++) {
            first[i] = Arrays.binarySearch(all, first[i]);
        }
        int n = cid.length;
        int[] revid = new int[n];
        for (int i = 0; i < n; i++) {
            revid[cid[i]] = i;
        }
        int zid = revid[0];
        long ret = 0;
        long ways = 1;
        for (int i = 0; i < id.length; i++) {
            if (i == cidPos) {
                continue;
            }
            ways *= dp[id[i].length];
        }
        all:
        for (int i = 1; i < n; i++) {
            int id1 = revid[i];
            for (int j = i + 1; j < n; j++) {
                int id2 = revid[j];
                if (i == first[1] && j == first[2]) {
                    int[][] nid = cut(id, cidPos, zid, id1, id2);
                    ret += getNumber(nid, Arrays.copyOfRange(a, 1, a.length));
                    return ret;
                } else {
                    ret += ways * getWaysSimple(zid, id1, id2, n);
                }
            }
        }
        throw new AssertionError();
    }

    static int[][] cut(int[][] id, int pos, int a, int b, int c) {
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            int t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            int t = b;
            b = c;
            c = t;
        }
        int[][] retid = Arrays.copyOf(id, id.length + 2);
        int[] t = retid[pos];
        retid[pos] = retid[id.length - 1];
        int len = id.length - 1;
        if (b - a - 1 > 0) {
            retid[len++] = Arrays.copyOfRange(t, a + 1, b);
        }
        if (c - b - 1 > 0) {
            retid[len++] = Arrays.copyOfRange(t, b + 1, c);
        }
        if (t.length - c - 1 + a > 0) {
            int[] w = new int[t.length - c - 1 + a];
            System.arraycopy(t, c + 1, w, 0, t.length - c - 1);
            System.arraycopy(t, 0, w, t.length - c - 1, a);
            retid[len++] = w;
        }
        return Arrays.copyOf(retid, len);
    }

    static long getWaysSimple(int a, int b, int c, int n) {
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            int t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            int t = b;
            b = c;
            c = t;
        }
        return dp[b - a - 1] * dp[c - b - 1] * dp[n - c - 1 + a];
    }

    static int[][] getObjectByNumber(long k, int[][] id) {
        if (id.length == 0) {
            return new int[0][];
        }
        int[] cid = null;
        int cidPos = -1;
        int minimal = Integer.MAX_VALUE;
        all:
        for (int i = 0; i < id.length; i++) {
            int[] e = id[i];
            for (int j = 0; j < e.length; j++) {
                if (e[j] < minimal) {
                    cid = e;
                    cidPos = i;
                    minimal = e[j];
                }
            }
        }
        cid = cid.clone();
        int[] all = ArrayUtils.sortAndUnique(cid);
        for (int i = 0; i < cid.length; i++) {
            cid[i] = Arrays.binarySearch(all, cid[i]);
        }
        minimal = Arrays.binarySearch(all, minimal);
        int n = cid.length;
        int[] revid = new int[n];
        for (int i = 0; i < n; i++) {
            revid[cid[i]] = i;
        }
        long ways = 1;
        for (int i = 0; i < id.length; i++) {
            if (i != cidPos) {
                ways *= dp[id[i].length];
            }
        }
        int zid = revid[minimal];
        for (int i = 1; i < n; i++) {
            int id1 = revid[i];
            for (int j = i + 1; j < n; j++) {
                int id2 = revid[j];
                long curW = ways * getWaysSimple(zid, id1, id2, n);
                if (k >= curW) {
                    k -= curW;
                } else {
                    int[][] nid = cut(id, cidPos, zid, id1, id2);
                    int[][] ans = getObjectByNumber(k, nid);
                    int[][] ret = new int[ans.length + 1][];
                    System.arraycopy(ans, 0, ret, 1, ans.length);
                    ret[0] = new int[]{id[cidPos][zid], id[cidPos][id1], id[cidPos][id2]};
                    return ret;
                }
            }
        }
        throw new AssertionError();
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] id1 = new int[3 * n];
        for (int i = 0; i < id1.length; i++) {
            id1[i] = in.nextInt() - 1;
        }
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = in.nextInt() - 1;
            }
        }
        int[] id2 = new int[3 * n];
        for (int i = 0; i < 3 * n; i++) {
            id2[i] = in.nextInt() - 1;
        }
        long num = getNumber(new int[][]{id1}, a);
        int[][] ans = getObjectByNumber(num, new int[][]{id2});
        for (int[] d : ans) {
            for (int j = 0; j < d.length; j++) {
                if (j > 0) {
                    out.print(' ');
                }
                out.print(d[j] + 1);
            }
            out.println();
        }
    }
}
