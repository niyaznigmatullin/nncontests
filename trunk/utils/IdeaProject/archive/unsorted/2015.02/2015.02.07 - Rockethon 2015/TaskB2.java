package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long m = in.nextLong();
        List<Integer> ans = go(n, m - 1, 1);
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ans));
    }

    static List<Integer> go(int n, long m, int cur) {
        if (n == 1) return new ArrayList<>(Arrays.asList(cur));
        long ways = 1L << (n - 2);
        if (m >= ways) {
            m -= ways;
            List<Integer> ret = go(n - 1, m, cur + 1);
            ret.add(cur);
            return ret;
        } else {
            List<Integer> ret = go(n - 1, m, cur + 1);
            ret.add(0, cur);
            return ret;
        }
    }
}
