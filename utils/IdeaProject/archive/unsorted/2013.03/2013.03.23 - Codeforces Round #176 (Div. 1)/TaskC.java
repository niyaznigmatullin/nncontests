package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] p = in.readIntArray(n);
        int m = in.nextInt();
        boolean[] neg = new boolean[n];
        for (int i : in.readIntArray(m)) {
            neg[i - 1] = true;
        }
        boolean[] ans = neg.clone();
        int[] stack = new int[n];
        int cnt = 0;
        Arrays.fill(ans, true);
        for (int i = n - 1; i >= 0; i--) {
            if (neg[i]) {
                stack[cnt++] = p[i];
            } else {
                if (cnt > 0 && stack[cnt - 1] == p[i]) {
                    ans[i] = false;
                    --cnt;
                } else {
                    stack[cnt++] = p[i];
                }
            }
        }
        if (cnt > 0) {
            out.println("NO");
            return;
        }
        out.println("YES");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            if (ans[i]) {
                sb.append(-p[i]);
            } else {
                sb.append(p[i]);
            }
        }
        out.println(sb);
    }
}
