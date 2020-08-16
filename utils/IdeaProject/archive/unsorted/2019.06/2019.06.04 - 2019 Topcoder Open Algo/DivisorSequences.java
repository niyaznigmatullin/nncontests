package coding;

import java.util.HashMap;
import java.util.Map;

public class DivisorSequences {
    public int longest(int N) {
        ans = new HashMap<>();
        int ret = Math.max(solve(N), solve(N - 1) + 1);
        return ret;
    }

    static Map<Integer, Integer> ans;

    static int solve(int n) {
        if (n <= 1) return 0;
        if (ans.containsKey(n)) return ans.get(n);
        int ret = 1;
        for (int div = 2; div * div <= n; div++) {
            if (n % div != 0) continue;
            ret = Math.max(ret, solve(n / div - 1) + 1);
            if (div * div != n) {
                ret = Math.max(ret, solve(div - 1) + 1);
            }
        }
        ans.put(n, ret);
        return ret;
    }
}
