package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> all = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (all.containsKey(a[i])) all.put(a[i], all.get(a[i]) + 1); else
                all.put(a[i], 1);
        }
        Fenwick f = new Fenwick(n + 1);
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (count.containsKey(a[i])) {
                count.put(a[i], count.get(a[i]) + 1);
            } else {
                count.put(a[i], 1);
            }
            int x = count.get(a[i]);
//            System.out.println(x + " " + (all.get(a[i]) - x) + " " + f.getSum(all.get(a[i]) - x));
            ans += f.getSum(all.get(a[i]) - x);
            f.add(x, 1);
        }
        out.println(ans);
    }
}
