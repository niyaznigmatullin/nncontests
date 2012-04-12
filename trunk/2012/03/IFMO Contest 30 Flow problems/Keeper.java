package mypackage;

import graphalgorithms.HungarianAlgorithm;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Keeper {

    static final int INF = 1 << 12;
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int z = in.nextInt();
        int n = Math.max(m, z);
        int[][] a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, INF);
        }
        for (int i = m; i < n; i++) {
            Arrays.fill(a[i], INF * INF);
        }
        for (int i = z; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[j][i] = INF * INF;
            }
        }
        for (int i = 0; i < m; i++) {
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                int x = in.nextInt() - 1;
                a[i][x] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            int d = in.nextInt();
            if (d == 0) {
                continue;
            }
            --d;
            for (int j = 0; j < n; j++) {
                if (a[i][j] < INF) {
                    a[i][j]++;
                }
            }
            for (int j = 0; j < m; j++) {
                if (a[j][d] < INF) {
                    a[j][d]++;
                }
            }
            a[i][d] = 0;
        }
        int[] p = HungarianAlgorithm.getMatching(a);
        int ans = 0;
        int cost = 0;
        for (int i = 0; i < n; i++) {
            int j = p[i];
            if (a[i][j] >= INF) {
                continue;
            }
            ans++;
            cost += a[i][j];
        }
        if ((cost & 1) == 1) {
            throw new AssertionError();
        }
        cost >>= 1;
        out.println(ans + " " + cost);
        for (int i = 0; i < m; i++) {
            int j = p[i];
            if (a[i][j] >= INF) {
                out.print(0 + " ");
                continue;
            }
            out.print(j + 1 + " ");
        }
	}
}
