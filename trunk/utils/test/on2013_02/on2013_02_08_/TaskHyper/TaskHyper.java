package lib.test.on2013_02.on2013_02_08_.TaskHyper;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class TaskHyper {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        BigInteger n = new BigInteger(in.next());
        BigInteger m = new BigInteger(in.next());
        for (int i = 0; n.signum() > 0 && i < 100; i++) {
            BigInteger d = m.add(n).subtract(BigInteger.ONE).divide(n);
            out.println(d);
            BigInteger a = n.multiply(d).subtract(m);
            BigInteger b = m.multiply(d);
            BigInteger g = a.gcd(b);
            n = a.divide(g);
            m = b.divide(g);
        }
    }
}
