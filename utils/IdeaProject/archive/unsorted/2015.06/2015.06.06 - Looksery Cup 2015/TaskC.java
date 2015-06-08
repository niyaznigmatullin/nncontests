package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            ones += x % 2;
        }
        if (n == k) {
            if (ones % 2 == 0) {
                out.println("Daenerys");
            } else {
                out.println("Stannis");
            }
        } else if ((n - k) % 2 == 0) {
            if (k % 2 == 0 || (n - ones) > (n - k) / 2) {
                out.println("Daenerys");
            } else {
                out.println("Stannis");
            }
        } else {
            if ((n - k) / 2 >= ones || k % 2 == 0 && (n - k) / 2 >= n - ones) {
                out.println("Daenerys");
            } else {
                out.println("Stannis");
            }
        }
    }
}
