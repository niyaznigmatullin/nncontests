package coding;

import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class AlwaysDefined {
    public long countIntegers(long L, long R, int W) {
        return solve(L, R, W);
    }

    static List<Integer>[] great;
    static long ans;

    static long get(long n, long x, int w) {
        n /= x;
        if (n <= 0) return 0;
        n--;
        n /= w;
        return n + 1;
    }

    static void go(long n1, long n, long x, int mod, int w) {
        if (x > n) return;
        ans += get(n, x, w);
        ans -= get(n1, x, w);
        for (int i : great[mod]) {
            if (n / x < i) {
                continue;
            }
            go(n1, n, x * i, i, w);
        }
    }

    static long solve(long l, long r, int w) {
        great = new List[w];
        for (int i = 0; i < w; i++) {
            great[i] = new ArrayList<>();
        }
        for (int mod = 1; mod < w; mod++) {
            for (int mod2 = 2; mod2 < w; mod2++) {
                if (mod * mod2 % w == mod2) {
                    great[mod].add(mod2);
                }
            }
        }
        ans = 0;
        go(l - 1, r, 1, 1, w);
        return ans;
    }
}
