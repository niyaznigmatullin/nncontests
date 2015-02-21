package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int[] who = new int[x + y];
        for (int i = 1, j = 1; i <= x && j <= y; ) {
            if ((long) i * y == (long) j * x) {
                who[i + j - 2] = 3;
                who[i + j + 1 - 2] = 3;
                ++i;
                ++j;
            } else if ((long) i * y > (long) j * x) {
                who[i + j - 2] = 2;
                ++j;
            } else {
                who[i + j - 2] = 1;
                ++i;
            }
        }
        for (int i = 0; i < n; i++) {
            int t = in.nextInt() - 1;
            t %= x + y;
            out.println(who[t] == 3 ? "Both" : who[t] == 1 ? "Vanya" : "Vova");
        }
    }
}
