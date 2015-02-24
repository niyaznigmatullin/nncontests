package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        int[] next = new int[n + 1];
        next[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == -1) next[i] = next[i + 1]; else next[i] = i;
        }
        int ans = 0;
        for (int cur = 0; cur < n; ) {
            int next1 = next[cur];
            if (next1 >= n) {
                ++ans;
                break;
            }
            int next2 = next[next1 + 1];
            if (next2 >= n) {
                ++ans;
                break;
            }
            long dif = a[next2] - a[next1];
            if (dif % (next2 - next1) != 0) {
                cur = next2;
                ++ans;
                continue;
            }
            dif /= next2 - next1;
            long val = a[next1] - dif * (next1 - cur);
            if (val <= 0) {
                cur = next2;
                ++ans;
                continue;
            }
            while (cur < n && val > 0) {
                if (a[cur] >= 0 && a[cur] != val) {
                    break;
                }
                val += dif;
                ++cur;
            }
            ++ans;
        }
        out.println(ans);
    }
}
