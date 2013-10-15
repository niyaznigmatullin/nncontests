package lib.test.on2013_06.on2013_06_15_GCJ_Round_3.TaskA;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        int b = in.nextInt();
        int n = in.nextInt();
        double ans = 0;
        int[] a = new int[37];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0; i--) a[i] -= a[0];
        for (int bet = 0; bet <= b; bet++) {
            for (int win = 1; win <= 37; win++) {
                int need = 0;
                int count = 0;
                for (int i : a) {
                    if (i <= bet) {
                        need += bet - i;
                        ++count;
                    }
                }
                if (win > count) {
                    continue;
                }
                need += count - win;
                if (need > b) {
                    continue;
                }
                double sum = 0;
                for (int i = 0; i < win; i++) {
                    sum += bet - a[i];
                }
                ans = Math.max(ans, 36 * sum / win - need);
            }
        }
        out.println(ans);
    }
}
