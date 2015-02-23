package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static final int MOD = 1000000007;
    static final int MAXN = 123456;
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int tests = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[MAXN];
        a[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            a[i] = a[i - 1];
            if (i >= k) {
                a[i] += a[i - k];
                if (a[i] >= MOD) a[i] -= MOD;
            }
        }
        for (int i = 1; i < MAXN; i++) {
            a[i] += a[i - 1];
            if (a[i] >= MOD) a[i] -= MOD;
        }
        for (int curTest = 0; curTest < tests; curTest++) {
            int left = in.nextInt();
            int right = in.nextInt();
            int ans = a[right] - a[left - 1];
            if (ans < 0) ans += MOD;
            out.println(ans);
        }
    }
}
