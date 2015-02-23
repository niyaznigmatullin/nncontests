package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] x = in.readIntArray(n);
        long[] s1 = new long[n];
        int[] c1 = new int[n];
        for (int i = 0; i < n; i += m) {
            s1[i] = x[i];
            c1[i] = 1;
        }
        for (int i = 1; i < n; i++) {
            s1[i] += s1[i - 1];
            c1[i] += c1[i - 1];
        }
        long[] s2 = new long[n];
        int[] c2 = new int[n];
        for (int i = n - 1; i >= 0; i -= m) {
            s2[i] = x[i];
            c2[i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            s2[i] += s2[i + 1];
            c2[i] += c2[i + 1];
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long s = (long) c1[i] * x[i] - s1[i];
            s += s2[i] - (long) c2[i] * x[i];
            ans = Math.min(ans, s);
        }
        out.println(ans * 2);
    }
}
