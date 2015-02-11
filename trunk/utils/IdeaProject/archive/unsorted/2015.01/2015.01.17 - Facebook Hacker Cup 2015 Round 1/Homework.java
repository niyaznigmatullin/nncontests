package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class Homework {

    static int[] PRIMACITY;
    static final int MAXN = 12345678;
    static {
        PRIMACITY = new int[MAXN];
        for (int i = 2; i < MAXN; i++) {
            if (PRIMACITY[i] == 0) {
                for (int j = i; j < MAXN; j += i) {
                    PRIMACITY[j]++;
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int ans = 0;
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        for (int i = a; i <= b; i++) {
            if (PRIMACITY[i] == k) {
                ++ans;
            }
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
