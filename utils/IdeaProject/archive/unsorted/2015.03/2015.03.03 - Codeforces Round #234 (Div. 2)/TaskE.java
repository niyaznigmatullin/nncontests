package coding;

import ru.ifmo.niyaz.DataStructures.FenwickKth;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        FenwickKth[] set = new FenwickKth[17];
        for (int i = 0; i < set.length; i++) set[i] = new FenwickKth(n + 2);
        for (FenwickKth e : set) {
            for (int i = 0; i <= n + 1; i++) e.add(i, 1);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            for (int bit = 0; bit < set.length; bit++) {
                if (((x >> bit) & 1) == 1) {
                    set[bit].add(i + 1, -1);
                    ans += get(set[bit], i) << bit;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt();
            for (int bit = 0; bit < set.length; bit++) {
                if ((set[bit].getSum(x + 1, x + 2) == 1) != (((y >> bit) & 1) == 0)) {
                    if (((y >> bit) & 1) == 1) {
                        set[bit].add(x + 1, -1);
                        ans += get(set[bit], x) << bit;
                    } else {
                        ans -= get(set[bit], x) << bit;
                        set[bit].add(x + 1, 1);
                    }
                }
            }
            out.println(ans);
        }
    }

    static long get(FenwickKth set, Integer i) {
        i++;
        int sum = set.getSum(i);
        int prev = set.getKth(sum);
        int next = set.getKth(sum + 1);
        return (long) (i - prev) * (next - i);
    }
}
