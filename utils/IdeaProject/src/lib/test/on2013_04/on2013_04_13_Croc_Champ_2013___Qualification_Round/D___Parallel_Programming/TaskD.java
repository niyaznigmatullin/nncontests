package lib.test.on2013_04.on2013_04_13_Croc_Champ_2013___Qualification_Round.D___Parallel_Programming;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] left = new int[n];
        for (int i = 0; i + 1 < n; i++) {
            left[i] = n - i - 2;
        }
        int[][] ans = new int[k][n];
        for (int i = 0; i < k; i++) {
            int[] curAns = ans[i];
            for (int j = 0; j < n; j++) {
                curAns[j] = ((left[j] >> i) & 1) == 1 ? (n - (1 << i)) : n;
            }
            out.printArray(curAns);
        }
//        test(n, ans);
    }

    static void test(int n, int[][] ans) {
        int[] x = new int[n];
        for (int i = 0; i + 1 < n; i++) {
            x[i] = 1;
        }
        for (int i = 0; i < ans.length; i++) {
            int[] y = new int[n];
            for (int j = 0; j < n; j++) {
                y[j] = x[j] + x[ans[i][j] - 1];
            }
            x = y;
        }
        for (int i = 0; i < n; i++) {
            if (x[i] != n - i - 1) {
                throw new AssertionError();
            }
        }
    }
}
