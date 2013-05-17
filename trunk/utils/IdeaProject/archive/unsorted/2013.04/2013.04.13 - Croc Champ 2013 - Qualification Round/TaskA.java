package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        Arrays.sort(a);
        for (int i = 1; i + 1 < n; i++) {
            if (a[i] == 0) {
                continue;
            }
            if (a[i - 1] == a[i] && a[i] == a[i + 1]) {
                out.println(-1);
                return;
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] == 0) {
                continue;
            }
            if (a[i] == a[i - 1]) {
                ++ans;
            }
        }
        out.println(ans);
    }
}
