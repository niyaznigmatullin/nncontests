package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class BobAndBen {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            in.nextInt();
            if (m != 2) {
                ans ^= 2 - (m & 1);
            }
        }
        out.println(ans == 0 ? "BEN" : "BOB");
    }
}
