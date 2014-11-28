package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskB {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int p = in.nextInt();
        int q = in.nextInt();
        int n = in.nextInt();
        int[] h = new int[n];
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
            g[i] = in.nextInt();
        }
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(1, 0);
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> next = new HashMap<>();
            int bDiana = -((h[i] + p - 1) / p);
            for (int take = 0; h[i] > take * p; take++) {
                int left = (h[i] - take * p);
                if ((left - 1) % q > p - 1) continue;
                int d = (left - 1) / q - take - 1;
                bDiana = Math.max(bDiana, d);
            }
            int bNothing = (h[i] + q - 1) / q;
            for (Integer j : dp.keySet()) {
                Integer val = dp.get(j);
                if (j + bNothing >= 0) {
                    putMax(next, j + bNothing, val);
                }
                if (j + bDiana >= 0) {
                    putMax(next, j + bDiana, g[i] + val);
                }
            }
            dp = next;
        }
        int ans = Integer.MIN_VALUE;
        for (int i : dp.values()) ans = Math.max(ans, i);
        out.println("Case #" + testNumber + ": " + ans);
    }

    static <K> void putMax(Map<K, Integer> map, K k, Integer v) {
        Integer was = map.get(k);
        if (was == null) {
            was = v;
        } else {
            was = Math.max(was, v);
        }
        map.put(k, was);
    }
}
