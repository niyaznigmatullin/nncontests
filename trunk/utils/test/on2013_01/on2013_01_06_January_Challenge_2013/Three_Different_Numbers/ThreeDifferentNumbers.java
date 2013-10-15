package lib.test.on2013_01.on2013_01_06_January_Challenge_2013.Three_Different_Numbers;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class ThreeDifferentNumbers {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n1 = in.nextLong();
        long n2 = in.nextLong();
        long n3 = in.nextLong();
        if (n1 > n2) {
            long t = n1;
            n1 = n2;
            n2 = t;
        }
        if (n1 > n3) {
            long t = n1;
            n1 = n3;
            n3 = t;
        }
        if (n2 > n3) {
            long t = n2;
            n2 = n3;
            n3 = t;
        }
        n1 %= MOD;
        n2 = (n2 + MOD - 1) % MOD;
        n3 = (n3 + MOD - 2) % MOD;
        out.println(n1 * n2 % MOD * n3 % MOD);
    }

    static final int MOD = 1000000007;
}
