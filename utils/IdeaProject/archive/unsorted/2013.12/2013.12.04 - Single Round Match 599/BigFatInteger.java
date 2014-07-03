package coding;

import ru.ifmo.niyaz.math.Factor;
import ru.ifmo.niyaz.math.MathUtils;

public class BigFatInteger {
    public int minOperations(int a, int b) {
        Factor[] f = MathUtils.factorize(a);
        for (Factor e : f) {
            e.pow *= b;
        }
        int ans = Integer.MAX_VALUE;
        for (int z = 0; z < 100; z++) {
            int cur = 0;
            for (Factor e : f) {
                int x = e.pow;
                for (int j = 0; j < z; j++) x = (x + 1) / 2;
                cur += x;
            }
            cur += z;
            ans = Math.min(ans, cur);
        }
        return ans;
    }
}
