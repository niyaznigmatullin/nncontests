package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(a[i], Integer.MAX_VALUE);
            a[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int len = in.nextInt();
            a[to][from] = a[from][to] = len;
        }
        int[][] b = a.clone();
        for (int i = 0; i < n; i++) b[i] = b[i].clone();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                int[] ai = a[i];
                for (int j = 0; j < n; j++) {
                    int val1 = ai[k];
                    int val2 = a[k][j];
                    if (val1 != Integer.MAX_VALUE && val2 != Integer.MAX_VALUE) {
                        ai[j] = Math.min(ai[j], val1 + val2);
                    }
                }
            }
        }
        int[][] ans = new int[n][n];
        for (int start = 0; start < n; start++) {
            int[] aStart = a[start];
            for (int mid = 0; mid < n; mid++) {
                int[] aMid = a[mid];
                if (aStart[mid] == Integer.MAX_VALUE || mid == start) continue;
                int countEdges = 0;
                for (int pre = 0; pre < n; pre++) {
                    if (aStart[pre] == Integer.MAX_VALUE || b[pre][mid] == Integer.MAX_VALUE || pre == mid) continue;
                    if (aStart[pre] + b[pre][mid] != aStart[mid]) continue;
                    ++countEdges;
                }
                for (int where = start + 1; where < n; where++) {
                    if (aStart[where] == Integer.MAX_VALUE || aMid[where] == Integer.MAX_VALUE) continue;
                    if (aStart[where] == aStart[mid] + aMid[where]) {
                        ans[start][where] += countEdges;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) out.print(ans[i][j] + " ");
        }
        out.println();
    }
}
