package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class ThePriceIsCorrect {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int[] a = in.readIntArray(n);
        long sum = 0;
        long ans = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n && sum + a[j] <= p) {
                sum += a[j++];
            }
            ans += j - i;
            sum -= a[i];
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
