package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= in.nextInt();
        }
        ans ^= get(n);
        out.println(ans);
    }

    static int get(int n) {
        ++n;
        int ans = 0;
        for (int mod = 1; mod < n; mod++) {
            int blocks = n / mod;
            if ((blocks & 1) == 1) {
                ans ^= f(mod - 1);
            }
            if (n % mod > 0) {
                ans ^= f(n % mod - 1);
            }
//            System.out.println(n + " " + mod + " " + blocks);
        }
        return ans;
    }

    static int f(int x) {
        if ((x & 3) == 3) return 0;
        if ((x & 3) == 1) return 1;
        if ((x & 3) == 0) return x;
        if ((x & 3) == 2) return 1 ^ x;
        throw new AssertionError();
    }
}
