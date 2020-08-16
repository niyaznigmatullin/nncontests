package coding;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class SimulateBST {
    public int checksum(int n, int[] Sprefix, int a, int m) {
        depth = new TreeMap<>();
        int p = Sprefix.length;
        int ans = 0;
        int lastd = 0;
        final int MOD = 1000000007;
        int curTen = 1;
        for (int i = 0; i < p; i++) {
            lastd = process(Sprefix[i]);
            ans = add(ans, mul(lastd, curTen, MOD), MOD);
            curTen = mul(curTen, 100000, MOD);
        }
        int[] s = Arrays.copyOf(Sprefix, n);
        for (int i = p; i < n; i++) {
            s[i] = add(add(mul(s[i - p], a, m), lastd % m, m), 1, m);
            lastd = process(s[i]);
            ans = add(ans, mul(lastd, curTen, MOD), MOD);
            curTen = mul(curTen, 100000, MOD);
        }
        return ans;
    }

    static NavigableMap<Integer, Integer> depth;

    static int process(int x) {
        if (depth.containsKey(x)) return depth.get(x);
        Map.Entry<Integer, Integer> next = depth.higherEntry(x);
        Map.Entry<Integer, Integer> prev = depth.lowerEntry(x);
        int d;
        if (next != null && prev != null) {
            d = Math.max(next.getValue(), prev.getValue()) + 1;
        } else if (next == null && prev == null) {
            d = 0;
        } else if (next == null) {
            d = prev.getValue() + 1;
        } else {
            d = next.getValue() + 1;
        }
        depth.put(x, d);
        return d;
    }

    static int mul(int a, int b, int mod) {
        return (int) ((long) a * b % mod);
    }

    static int add(int a, int b, int mod) {
        a += b;
        if (a >= mod) a -= mod;
        return a;
    }
}
