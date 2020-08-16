package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        int m = in.nextInt();
        long k = in.nextLong();
        long[] p = in.readLongArray(m);
        int cur = 0;
        int ans = 0;
        while (cur < m) {
            ++ans;
            long page = (p[cur] - cur - 1) / k;
            int next = cur;
            while (next < m && (p[next] - cur - 1) / k == page) {
                ++next;
            }
            cur = next;
        }
        out.println(ans);
    }
}
