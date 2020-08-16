package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class AdditionErrors {

    static long getBadSum(long a, long b) {
        if (a == 0 && b == 0) return 0;
        return getBadSum(a / 10, b / 10) * 10 + (a + b) % 10;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        if (a < 1 || b < 1 || a > 1000000000000000000L || b > 1000000000000000000L) throw new AssertionError();
        long sum = a + b;
        long badSum = getBadSum(a, b);
        out.println(sum - badSum);
    }
}
