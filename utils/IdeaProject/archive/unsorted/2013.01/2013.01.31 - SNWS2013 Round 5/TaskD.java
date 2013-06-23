package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        boolean[][] can = new boolean[101][101];
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j <= i; j++) {
                int z = j * 100 / i;
                if (j * 100 % i >= (i + 1) / 2) {
                    ++z;
                }
                can[z][i] = true;
            }
        }
        int answer = 0;
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 1; i <= 100; i++) {
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if (!can[a[j]][i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.println(i);
                return;
            }
        }
    }
}
