package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        List<Integer> ans = new ArrayList<>();
        while (n > 0) {
            int p = n;
            int cur = 0;
            int ten = 1;
            while (p > 0) {
                if (p % 10 > 0) {
                    cur += ten;
                }
                ten *= 10;
                p /= 10;
            }
            ans.add(cur);
            n -= cur;
        }
        out.println(ans.size());
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ans));
    }
}
