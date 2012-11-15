package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskG {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextInt();
        int bits = Integer.numberOfTrailingZeros(Integer.highestOneBit((int) n));
        for (int i = bits; i > 0; i--) {
            if (((n >> i) & 1) == 1) {
                n ^= 1 << (i - 1);
            }
        }
        out.println(n);
    }
}
