package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Digit {


    static Map<BigInteger, BigInteger> map;

    static BigInteger go(BigInteger n) {
        if (n.signum() == 0) {
            return BigInteger.ONE;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        BigInteger ans;
        if (n.and(BigInteger.ONE).equals(BigInteger.ONE)) {
            ans = go(n.subtract(BigInteger.ONE).shiftRight(1));
        } else {
            ans = go(n.shiftRight(1)).add(go(n.shiftRight(1).subtract(BigInteger.ONE)));
        }
        map.put(n, ans);
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        map = new HashMap<>();
        out.println(go(new BigInteger(in.next())));
    }
}
