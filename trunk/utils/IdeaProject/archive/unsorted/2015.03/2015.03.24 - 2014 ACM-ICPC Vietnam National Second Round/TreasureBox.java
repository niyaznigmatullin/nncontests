package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TreasureBox {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int done = 0;
        long ans = 0;
        int x = n % 100;
        for (int i = 0; k > 0 && i < 100; i++) {
            ans += x;
            x = (x * 2) % 100;
            --k;
        }
        int period = 0;
        boolean[] was = new boolean[100];
        int v = x;
        int sum = 0;
        while (!was[v]) {
            was[v] = true;
            sum += v;
            v = v * 2 % 100;
            ++period;
        }
        ans += (long) sum * (k / period);
        k %= period;
        for (int i = 0; i < k; i++) {
            ans += x;
            x = x * 2 % 100;
        }
        out.println(n + ans);
    }
}
