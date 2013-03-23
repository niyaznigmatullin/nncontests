package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Factor;
import ru.ifmo.niyaz.math.MathUtils;

import java.math.BigInteger;

public class A {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int p = in.nextInt();
        int n = in.nextInt();
        int mod = in.nextInt();
        Factor[] factors = MathUtils.factorize(n);
        long goodMod = (long)mod * n;
        long sum = 0;
        for (int mask = 0; mask < 1 << factors.length; mask++) {
            int ok = n;
            for (int i = 0; i < factors.length; i++) {
                if ((mask & (1<<i)) != 0) {
                    ok /= factors[i].prime;
                }
            }
            int sign = (Integer.bitCount(mask) & 1) == 0? 1: -1;
            long current = pow(p, ok, goodMod);
            sum += current * sign;
            if (sum < 0) {
                sum += goodMod;
            }
            if (sum >= goodMod) {
                sum -= goodMod;
            }
        }
        out.println(sum / n);

    }

    private long pow(int p, int ok, long goodMod) {
        return BigInteger.valueOf(p).modPow(BigInteger.valueOf(ok), BigInteger.valueOf(goodMod)).longValue();
    }
}
