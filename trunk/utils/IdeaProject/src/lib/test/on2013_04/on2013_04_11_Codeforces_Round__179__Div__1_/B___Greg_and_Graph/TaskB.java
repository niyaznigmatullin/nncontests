package lib.test.on2013_04.on2013_04_11_Codeforces_Round__179__Div__1_.B___Greg_and_Graph;



import org.jcp.xml.dsig.internal.dom.DOMTransform;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[n - i - 1] = in.nextInt() - 1;
        }
        long[] answer = new long[n];
        int[][] b = new int[n][n];
        for (int[] d : b) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[x[i]][x[j]];
            }
        }
        for (int k = 0; k < n; k++) {
            int[] bk = b[k];
            long cur = 0;
            for (int i = 0; i < n; i++) {
                int[] bi = b[i];
                for (int j = 0; j < n; j++) {
                    int val = bi[k] + bk[j];
                    if (val < bi[j]) {
                        bi[j] = val;
                    }
                    if (i <= k && j <= k) {
                        cur += bi[j];
                    }
                }
            }
            answer[k] = cur;
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(answer[n - i - 1]);
        }
        out.println();
    }
}
