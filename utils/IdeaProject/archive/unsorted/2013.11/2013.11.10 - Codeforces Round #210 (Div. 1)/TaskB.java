package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        long l = -1;
        long r = Integer.MAX_VALUE * 2L;
        while (l < r - 1) {
            long mid = l + r >> 1;
            int count = 0;
            int last = Integer.MIN_VALUE;
            for (int i : a) {
                if (last == Integer.MIN_VALUE || Math.abs(last - i) <= mid) {
                    last = i;
                } else {
                    ++count;
                    last = Integer.MIN_VALUE;
                }
            }
            System.out.println(mid + " " + count);
            if (count <= k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        out.println(r);
    }
}
