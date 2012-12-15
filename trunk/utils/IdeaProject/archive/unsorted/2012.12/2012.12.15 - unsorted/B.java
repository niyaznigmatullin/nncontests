package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class B {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            map.put(in.next(), i);
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = map.get(in.next());
        }
        long ans = ArrayUtils.countNumberOfInversions(a);
        int all = n * (n - 1) / 2;
        out.println((all - ans) + "/" + all);
	}
}
