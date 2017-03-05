package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class SellingNumbersBacktrack {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        s = in.next();
        int n = in.nextInt();
        t = new String[n];
        for (int i = 0; i < n; i++) t[i] = in.next();
        cost = in.readIntArray(10);
        ans = 0;
        go(0);
        out.println(ans);
        out.println(best);
    }

    static void go(int x) {
        if (x == s.length()) {
            BigInteger have = BigInteger.ZERO;
            BigInteger add = new BigInteger(new String(num));
            for (String e : t) {
                have = have.add(new BigInteger(e).add(add));
            }
            int cur = 0;
            for (char e : have.toString().toCharArray()) {
                cur += cost[e - '0'];
            }
            if (ans < cur) {
                ans = cur;
                best = new String(num);
            }
            return;
        }
        if (s.charAt(x) == '?') {
            for (int i = 0; i < 10; i++) {
                if (x == 0 && i == 0) continue;
                num[x] = (char) (i + '0');
                go(x + 1);
            }
        } else {
            num[x] = s.charAt(x);
            go(x + 1);
        }
    }

    static int ans;
    static String best;
    static char[] num;
    static String s;
    static String[] t;
    static int[] cost;
}
