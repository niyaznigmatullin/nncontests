package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int w = in.nextInt();
        int[] a = in.readIntArray(n);
        int left = 0;
        int right = 1000000000 + 1000000;
        while (left < right - 1) {
            int mid = left + right >> 1;
            int[] add = new int[n];
            int have = m;
            for (int i = 0; i < n; i++) {
                if (i > 0) add[i] += add[i - 1];
                if (add[i] + a[i] >= mid) continue;
                int todo = mid - a[i] - add[i];
                if (todo > have) {
                    have = -1;
                    break;
                }
                have -= todo;
                add[i] += todo;
                if (i + w < n) add[i + w] -= todo;
            }
            if (have >= 0) left = mid; else right = mid;
        }
        out.println(left);
    }
}
