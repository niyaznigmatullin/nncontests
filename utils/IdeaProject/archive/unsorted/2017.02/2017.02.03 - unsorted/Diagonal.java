package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.HungarianAlgorithm;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Diagonal {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = in.readInt2DArray(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = -a[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int t = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = t;
            }
        }
        int[] p = HungarianAlgorithm.getMatching(a);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum -= a[i][p[i]];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[n - p[i] - 1] = i + 1;
        }
        out.printArray(ans);
    }
}
