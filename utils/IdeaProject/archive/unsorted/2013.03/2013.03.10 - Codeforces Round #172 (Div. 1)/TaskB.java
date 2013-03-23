package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] stack = new int[n];
        int cur = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (cur > 0 && a[stack[cur - 1]] < x) {
                int y = a[stack[--cur]];
                ans = Math.max(ans, x ^ y);
            }
            if (cur > 0) {
                ans = Math.max(ans, x ^ a[stack[cur - 1]]);
            }
            stack[cur++] = i;
        }
        out.println(ans);
    }
}
