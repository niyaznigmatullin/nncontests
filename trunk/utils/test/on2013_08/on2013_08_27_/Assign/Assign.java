package lib.test.on2013_08.on2013_08_27_.Assign;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Assign {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            a[i][n - 1] = 1;
        }
        a[n - 1][n - 1] = 100;
        for (int[] d : a) {
            out.printArray(d);
        }
    }
}
