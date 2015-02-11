package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskK {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int s = in.nextInt();
        int k = in.nextInt();
        int minSum = k * (k + 1) / 2;
        int maxSum = n * (n + 1) / 2 - (n - k) * (n - k + 1) / 2;
        if (s < minSum || s > maxSum) {
            out.println("NO");
            return;
        }
        char[] ans = new char[n];
        for (int i = n; i > 0; i--) {
            if (s >= i) {
                ans[i - 1] = '1';
                s -= i;
            } else ans[i - 1] = '0';
        }
        out.println("YES");
        out.println(ans);
    }
}
