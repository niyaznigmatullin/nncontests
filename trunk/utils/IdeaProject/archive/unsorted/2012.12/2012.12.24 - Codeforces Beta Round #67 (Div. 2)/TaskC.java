package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskC {

    static NavigableSet<Integer> getDivisors(int n) {
        NavigableSet<Integer> ret = new TreeSet<Integer>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            ret.add(i);
            ret.add(n / i);
        }
        return ret;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        NavigableSet<Integer> set = getDivisors(a);
        set.retainAll(getDivisors(b));
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            Integer z = set.floor(right);
            if (z != null && z >= left) {
                out.println(z);
            } else {
                out.println(-1);
            }
        }
	}
}
