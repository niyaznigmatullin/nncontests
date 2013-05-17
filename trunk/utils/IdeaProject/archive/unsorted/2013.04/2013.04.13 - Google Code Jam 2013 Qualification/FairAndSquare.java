package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class FairAndSquare {
    static List<Long> all;
    static final int MAXN = 10000000;

    static {
        all = new ArrayList<>();
        for (int i = 1; i <= MAXN; i++) {
            if (!isPalindrome(i)) {
                continue;
            }
            if (!isPalindrome((long) i * i)) {
                continue;
            }
            all.add((long) i * i);
        }
    }

    static boolean isPalindrome(long x) {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        String s = sb.toString();
        String t = sb.reverse().toString();
        return s.equals(t);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        long a = in.nextLong();
        long b = in.nextLong();
        long ans = 0;
        for (long e : all) {
            if (e >= a && e <= b) {
                ++ans;
            }
        }
        out.println(ans);
    }
}
