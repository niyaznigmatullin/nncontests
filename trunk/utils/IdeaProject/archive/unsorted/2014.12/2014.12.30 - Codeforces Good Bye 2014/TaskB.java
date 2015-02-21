package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] z = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                a[i][j] = z[j] - '0';
            }
        }
        for (int i = 0; i < n; i++) a[i][i] = 1;
        for (int k = 0; k < n; k++) {
            int[] ak = a[k];
            for (int i = 0; i < n; i++) {
                int[] ai = a[i];
                for (int j = 0; j < n; j++) {
                    ai[j] |= ai[k] & ak[j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i][j] == 0) continue;
                if (p[i] > p[j]) {
                    int t=  p[i];
                    p[i] = p[j];
                    p[j] = t;
                }
            }
        }
        out.printArray(p);
//        boolean[] was = new boolean[n];
//        int[] pos = new int[n];
//        int[] ans = new int[n];
//        for (int i = 0; i < n; i++) {
//            if (was[p[i]]) continue;
//            was[p[i]] = true;
//            int cn = 0;
//            for (int j = 0; j < n; j++) {
//                if (a[p[i]][p[j]] == 1) {
//                    pos[cn++] = j;
//                    was[p[j]] = true;
//                }
//            }
//            for (int e = 0; e < cn; e++) {
//                for (int f = e + 1; f < cn; f++) {
//                    if (p[pos[e]] > p[pos[f]]) {
//                        int t = pos[e];
//                        pos[e] = pos[f];
//                        pos[f] = t;
//                    }
//                }
//            }
//            int cur = 0;
//            for (int j = 0; j < n; j++) {
//                if (a[p[i]][p[j]] == 1) {
//                    ans[j] = p[pos[cur++]];
//                }
//            }
//        }
//        for (int i = 0; i < n; i++) ans[i]++;
//        out.printArray(ans);
    }
}
