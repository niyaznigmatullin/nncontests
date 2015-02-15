package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    static final int MOD = 1000000007;

    static int f(long x) {
        return (int) ((x * (x + 1) / 2) % MOD);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int sc = f(b - 1);
        int sk = f(a);
        out.println(add(mul(b, mul(sc, sk)), mul(sc, a)));
    }
}
