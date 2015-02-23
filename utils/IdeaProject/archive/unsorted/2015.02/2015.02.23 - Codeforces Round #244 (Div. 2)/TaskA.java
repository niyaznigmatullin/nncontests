package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int b = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (b + x < 0) ans++; else {
                b += x;
            }
        }
        out.println(ans);
    }
}
