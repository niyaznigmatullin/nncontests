package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long left = in.nextLong();
        long right = in.nextLong();
        int k = in.nextInt();
        if (right - left <= 10) {
            int count = (int) (right - left + 1);
            long best = Long.MAX_VALUE;
            int bMask = 0;
            for (int mask = 1; mask < 1 << count; mask++) {
                if (Integer.bitCount(mask) > k) continue;
                long xor = 0;
                for (int i = 0; i < count; i++) {
                    if (((mask >> i) & 1) == 1) xor ^= (left + i);
                }
                if (xor < best) {
                    best = xor;
                    bMask = mask;
                }
            }
            out.println(best);
            out.println(Integer.bitCount(bMask));
            for (int i = 0; i < count; i++) {
                if (((bMask >> i) & 1) == 1) {
                    out.print(left + i + " ");
                }
            }
            out.println();
            return;
        }
        if (k == 1) {
            out.println(left);
            out.println(1);
            out.println(left);
            return;
        }
        if (k >= 4) {
            left = (left + 3) / 4 * 4;
            out.println(0);
            out.println(4);
            out.println(left + " " + (left + 1) + " " + (left + 2) + " " + (left + 3));
            return;
        }
        if (k == 3) {
            for (int bit = 2; bit < 50; bit++) {
                long first = (1L << bit - 1) - 1;
                long second = ((1L << bit) - 1) ^ (1L << bit - 2);
                long third = first ^ second;
                if (first >= second || second >= third) throw new AssertionError();
                if (first >= left && third <= right) {
                    out.println(0);
                    out.println(3);
                    out.println(first + " " + second + " " + third);
                    return;
                }
            }
        }
        if ((left & 1) == 1) ++left;
        out.println(1);
        out.println(2);
        out.println(left + " " + (left + 1));
    }
}
