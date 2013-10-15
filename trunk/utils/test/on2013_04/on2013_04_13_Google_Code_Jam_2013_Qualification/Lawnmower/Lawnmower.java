package lib.test.on2013_04.on2013_04_13_Google_Code_Jam_2013_Qualification.Lawnmower;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Lawnmower {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = in.readInt2DArray(n, m);
        int max = 0;
        for (int[] b : a) {
            for (int c : b) {
                max = Math.max(max, c);
            }
        }
        for (int z = 1; z <= max; z++) {
            boolean[] row = new boolean[n];
            boolean[] col = new boolean[m];
            Arrays.fill(row, true);
            Arrays.fill(col, true);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] > z) {
                        row[i] = false;
                        col[j] = false;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] == z && !row[i] && !col[j]) {
                        out.println("NO");
                        return;
                    }
                }
            }
        }
        out.println("YES");
    }
}
