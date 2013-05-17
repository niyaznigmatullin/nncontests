package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.HashSet;

public class TaskC {

    static final int MOD = 1000000007;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        int k = in.nextInt();
        if (n == 0 && k == 0) {
            throw new UnknownError();
        }
        long all = n;
        HashSet<Long> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            long z = in.nextLong();
            if (!set.add(z)) {
                continue;
            }
            all -= Math.max(0, Math.min(n, z - 1) - z / 2);
        }
        out.println(MathUtils.modPow(2, all, MOD));
    }
}
