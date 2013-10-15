package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class TaskA2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int countFree = 10;
        boolean[] wasLetter = new boolean[10];
        BigInteger ans = BigInteger.ONE;
        int tens = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '?') {
                if (i == 0) {
                    ans = ans.multiply(BigInteger.valueOf(9));
                } else {
                    ++tens;
                }
            } else if (c >= 'A' && c <= 'J') {
                if (!wasLetter[c - 'A']) {
                    if (i == 0) {
                        ans = ans.multiply(BigInteger.valueOf(9));
                    } else {
                        ans = ans.multiply(BigInteger.valueOf(countFree));
                    }
                    --countFree;
                }
                wasLetter[c - 'A'] = true;
            }
        }
        out.print(ans);
        for (int i = 0; i < tens; i++) out.print('0');
        out.println();
    }
}
