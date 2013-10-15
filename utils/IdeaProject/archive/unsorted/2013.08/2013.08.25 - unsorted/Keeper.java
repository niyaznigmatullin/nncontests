package coding;

import ru.ifmo.niyaz.graphalgorithms.HungarianAlgorithm;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Keeper {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int nm = Math.max(n, m);
        final int INF = 999;
        int[][] a = new int[nm][nm];
        for (int[] d : a) {
            Arrays.fill(d, INF);
        }
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                int x = in.nextInt() - 1;
                a[i][x] = 1;
            }
        }
        int[] rp = new int[m];
        Arrays.fill(rp, -1);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            if (x < 0) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] != INF) a[i][j] = 0;
                }
                continue;
            }
            a[i][x] = 0;
            rp[x] = i;
            for (int j = 0; j < nm; j++) {
                if (a[i][j] == INF) a[i][j] = INF * INF;
            }
        }
        for (int j = 0; j < m; j++) {
            if (rp[j] < 0) continue;
            for (int i = 0; i < nm; i++) {
                if (a[i][j] == INF) {
                    a[i][j] = INF * INF;
                }
            }
        }
//        for (int i = 0; i < m; i++) {
//            if (pr[i] < 0) {
//                for (int j = 0; j < n; j++) {
//                    if (a[j][i] != INF) a[j][i] = 0;
//                }
//            } else {
//                a[pr[i]][i] = 0;
//            }
//        }
//        for (int[] d : a) {
//            System.out.println(Arrays.toString(d));
//        }
        int[] p = HungarianAlgorithm.getMatching(a);
        int cost = 0;
        for (int i = 0; i < nm; i++) {
            cost += a[i][p[i]];
            if (a[i][p[i]] >= INF) {
                p[i] = -1;
            }
        }
//        System.out.println(cost + " " + Arrays.toString(rp));
        out.println(nm - cost / INF + " " + (cost % INF));
        for (int i = 0; i < n; i++) {
            out.print(1 + p[i] + " ");
        }
        out.println();
    }
}
