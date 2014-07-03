package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class Task {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        char[] s = in.next().toCharArray();
        Arrays.sort(s);
        BigInteger n = new BigInteger(in.next()).subtract(BigInteger.ONE);
        for (int len = 1; ; len++) {
            BigInteger count = BigInteger.valueOf(s.length).pow(len);
            if (count.compareTo(n) <= 0) {
                n = n.subtract(count);
            } else {
                char[] ret = new char[len];
                for (int i = 0; i < len; i++) {
                    count = count.divide(BigInteger.valueOf(s.length));
                    for (int j = 0; j < s.length; j++) {
                        if (count.compareTo(n) <= 0) {
                            n = n.subtract(count);
                        } else {
                            ret[i] = s[j];
                            break;
                        }
                    }
                    if (ret[i] == 0) {
                        throw new AssertionError();
                    }
                }
                out.println(ret);
                return;
            }
        }
    }
}
