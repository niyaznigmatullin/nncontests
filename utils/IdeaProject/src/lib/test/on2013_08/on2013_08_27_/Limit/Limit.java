package lib.test.on2013_08.on2013_08_27_.Limit;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class Limit {

    static int phi(int n) {
        int ret = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            while (n % i == 0) n /= i;
            ret -= ret / i;
        }
        if (n > 1) {
            ret -= ret / n;
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int p = in.nextInt();
        int m = in.nextInt();
        int mod = 1;
        for (int i = 2; i <= m; i++) mod *= i;
        out.println(tower(p, mod));
    }

    static int tower(int x, int mod) {
        if (mod == 1) return 0;
        int count = 0;
        while (mod % x == 0) {
            mod /= x;
            ++count;
        }
        int y = phi(mod);
        int got = (tower(x, y) - count) % y;
        if (got < 0) got += y;
        int ans = MathUtils.modPow(x, got, mod);
        for (int i = 0; i < count; i++) {
            ans *= x;
            mod *= x;
        }
        return ans % mod;
    }
}
