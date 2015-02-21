package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = 5 - in.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (in.nextInt() <= k) {
                ans++;
            }
        }
        out.println(ans / 3);
    }
}
