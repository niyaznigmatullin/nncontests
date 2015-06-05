package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] ans = new int[n];
        for (int i = 1; i < n; i++) {
            int v = i - 1;
            int p = v;
            while (p != 0) {
                int right = v / p;
                int left = v / (p + 1);
                if (a[p] > a[i]) {
                    ans[left]--;
                    ans[right]++;
                }
                p = v / (right + 1);
            }
            if (a[p] > a[i]) {
                ans[i - 1]--;
                ans[n - 1]++;
            }
        }
        for (int i = n - 2; i >= 0; i--) ans[i] += ans[i + 1];
        out.printArray(Arrays.copyOfRange(ans, 1, ans.length));
    }
}
