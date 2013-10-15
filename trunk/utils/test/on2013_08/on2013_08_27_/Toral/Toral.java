package lib.test.on2013_08.on2013_08_27_.Toral;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class Toral {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.println(new BigInteger(in.next()).gcd(new BigInteger(in.next())));
    }
}
