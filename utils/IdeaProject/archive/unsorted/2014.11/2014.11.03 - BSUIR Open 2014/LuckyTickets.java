package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class LuckyTickets {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        BigInteger all = new BigInteger(in.next()).add(BigInteger.ONE);
        char[] c = all.toString(2).toCharArray();
        long ans = 0;
        for (int bits = 1; bits <= c.length; bits++) {
            ans += solve(bits, c);
        }
        out.println(new BigInteger(Long.toBinaryString(ans), 2));
    }

    static long solve(int bits, char[] c) {
        int len = c.length;
        long[][][] dp = new long[len + 1][bits][len + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int mod = 0; mod < bits; mod++) {
                for (int have = 0; have <= len; have++) {
                    long val = dp[i - 1][mod][have];
                    if (val == 0) continue;
                    for (int last = 0; last < 2 && have + last <= len; last++) {
                        dp[i][(mod * 2 + last) % bits][have + last] += val;
                    }
                }
            }
        }
        long ans = 0;
        int need = bits;
        int needMod = 0;
        for (int i = 0; need >= 0 && i < len; i++) {
            if (c[i] == '1') {
                ans += dp[len - i - 1][needMod][need];
                need--;
                int two = 1 % bits;
                for (int j = i + 1; j < len; j++) two = (two * 2) % bits;
                needMod = (needMod + bits - two) % bits;
            }
        }
        return ans;
    }
}
