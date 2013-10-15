package lib.test.on2013_07.on2013_07_27_Codeforces_Round__194__Div__1_.D___Characteristics_of_Rectangles;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = in.readInt2DArray(n, m);
        int[] b = new int[n * m];
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = 0; j < m; j++, k++) {
                b[k] = a[i][j];
            }
        }
        b = ArrayUtils.sortAndUnique(b);
        int l = -1;
        int r = b.length;
        int[] z = new int[m];
        boolean[][] was = new boolean[m][m];
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            int x = b[mid];
            for (boolean[] e : was) {
                Arrays.fill(e, false);
            }
            boolean ok = false;
            loop: for (int i = 0; i < n; i++) {
                int cn = 0;
                int[] ai = a[i];
                for (int j = 0; j < m; j++) {
                    if (ai[j] >= x) {
                        z[cn++] = j;
                    }
                }
                for (int e1 = 0; e1 < cn; e1++) {
                    int c1 = z[e1];
                    for (int e2 = e1 + 1; e2 < cn; e2++) {
                        int c2 = z[e2];
                        if (was[c1][c2]) {
                            ok = true;
                            break loop;
                        }
                        was[c1][c2] = true;
                    }
                }
            }
            if (ok) {
                l = mid;
            } else {
                r = mid;
            }
        }
        out.println(b[l]);
    }
}
