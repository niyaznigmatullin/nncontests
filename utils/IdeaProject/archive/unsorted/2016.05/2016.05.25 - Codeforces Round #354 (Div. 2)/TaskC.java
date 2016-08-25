package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        char[] c = in.next().toCharArray();
        int ans = 0;
        for (char e = 'a'; e <= 'b'; e++) {
            int[] a = new int[n + 2];
            int cn = 0;
            a[cn++] = -1;
            for (int i = 0; i < n; i++) {
                if (c[i] == e) {
                    a[cn++] = i;
                }
            }
            a[cn++] = n;
            for (int i = 0; i < cn; i++) {
                int first = a[i];
                int last = a[Math.min(cn - 1, i + k + 1)];
                ans = Math.max(ans, last - first - 1);
            }
        }
        out.println(ans);
    }
}
