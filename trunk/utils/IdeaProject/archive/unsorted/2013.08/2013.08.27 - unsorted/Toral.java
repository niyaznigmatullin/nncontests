package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class Toral {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.println(new BigInteger(in.next()).gcd(new BigInteger(in.next())));
    }
}
