package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class TaskC {
    static final Random rand = new Random(1231113L);

    static final int PRIME = BigInteger.probablePrime(20, rand).intValue();

    static long hash(int[] a) {
        long ret = 1;
        for (int i = 0; i < a.length; i++) {
            ret = ret * PRIME + a[i];
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] deg = new int[n];
        int[] a = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            deg[a[i]]++;
            deg[b[i]]++;
        }
        long ans = 0;
        int[][] edges = new int[n][];
        {
            int[] curDeg = new int[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new int[deg[i]];
            }
            for (int i = 0; i < m; i++) {
                edges[a[i]][curDeg[a[i]]++] = b[i];
                edges[b[i]][curDeg[b[i]]++] = a[i];
            }
            ans += count(edges);
        }
        {
            int[] curDeg = new int[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new int[deg[i] + 1];
            }
            for (int i = 0; i < m; i++) {
                edges[a[i]][curDeg[a[i]]++] = b[i];
                edges[b[i]][curDeg[b[i]]++] = a[i];
            }
            for (int i = 0; i < n; i++) {
                edges[i][curDeg[i]++] = i;
            }
            ans += count(edges);
        }
        out.println(ans);
    }

    static long count(int[][] a) {
        long[] h = new long[a.length];
        long ret = 0;
        int count = 0;
        for (int[] e : a) {
            Arrays.sort(e);
            long p = hash(e);
            h[count++] = p;
        }
        Arrays.sort(h);
        for (int i = 0; i < a.length; ) {
            int j = i;
            while (j < a.length && h[i] == h[j]) {
                j++;
            }
            long z = j - i;
            ret += z * (z - 1) >> 1;
            i = j;
        }
        return ret;
    }


}
