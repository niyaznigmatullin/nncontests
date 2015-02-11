package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskB1 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        List<int[]> ret = new ArrayList<>();
        int best = Integer.MIN_VALUE;
        do {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = i; j < n; j++) {
                    min = Math.min(min, p[j]);
                    sum += min;
                }
            }
            if (best < sum) {
                ret.clear();
                best = sum;
            }
            if (best == sum) ret.add(p.clone());
        } while (ArrayUtils.nextPermutation(p));
        int[] ans = ret.get(m - 1);
        for (int i = 0; i < n; i++) ++ans[i];
        out.printArray(ans);
    }
}
