package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class C {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt() + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.next().length();
        }
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                map.put(a[i - k], map.get(a[i - k]) - 1);
            }
            Integer z = map.get(a[i]);
            if (z == null) {
                z = 0;
            }
            ans += z;
            map.put(a[i], z + 1);
        }
        out.println(ans);
	}
}
