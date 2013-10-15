package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE1 {

    static final int MOD = 1000000000;

    static int[] getFib(int n) {
        n = Math.max(n, 10);
        int[] ret = new int[n];
        ret[0] = ret[1] = 1;
        for (int i = 2; i < n; i++) {
            ret[i] = ret[i - 1] + ret[i - 2];
            if (ret[i] >= MOD) ret[i] -= MOD;
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] fib = getFib(n);
        int[] a = in.readIntArray(n);
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            if (t == 1) {
                int x = in.nextInt() - 1;
                int v = in.nextInt();
                a[x] = v;
            } else if (t == 2)  {
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                long ans = 0;
                for (int j = l; j < r; j++) {
                    ans = (ans + (long) a[j] * fib[j - l]) % MOD;
                }
                out.println(ans);
            }
        }
    }
}
