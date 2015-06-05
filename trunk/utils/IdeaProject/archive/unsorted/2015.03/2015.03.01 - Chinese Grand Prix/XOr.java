package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class XOr {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        int cur = 0;
        for (int bit = 29; bit >= 0; bit--) {
            int next = cur | (1 << bit);
            int places = 0;
            int xor = 0;
            for (int i = 0; i < n; i++) {
                xor ^= a[i];
                if ((xor & next) == 0) ++places;
            }
            if ((xor & next) == 0 && places >= m) {
                cur = next;
            }
        }
        out.println(((1 << 30) - 1) ^ cur);
    }
}
