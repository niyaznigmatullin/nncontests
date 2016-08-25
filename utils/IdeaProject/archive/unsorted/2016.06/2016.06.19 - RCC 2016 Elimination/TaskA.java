package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {

    static int getAns(int[] a, int t) {
        int ans = 0;
        for (int i : a) {
            if (i > t) break;
            t -= i;
            ++ans;
        }
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        int t0 = in.nextInt();
        for (int e = 0; e < n; e++) {
            int k = in.nextInt();
            int[] b = in.readIntArray(k);
            int ans = getAns(b, t);
            for (int i = 0; i < k; i++) {
                int old = b[i];
                b[i] = t0;
                ans = Math.max(ans, getAns(b, t));
                b[i] = old;
            }
            out.println(ans);
        }
    }
}
