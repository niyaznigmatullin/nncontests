package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int n = in.nextInt();
        boolean[] reversed = new boolean[s.length() + 1];
        for (int i = 0; i< n; i++) {
            reversed[in.nextInt() - 1] ^= true;
        }
        boolean rev = false;
        char[] ans = new char[s.length()];
        for (int i = 0; i <= s.length() - i - 1; i++) {
            rev ^= reversed[i];
            char c = s.charAt(i);
            char d = s.charAt(s.length() - i - 1);
            if (rev) {
                char t = c;
                c = d;
                d = t;
            }
            ans[i] = c;
            ans[s.length() - i - 1] = d;
        }
        out.println(ans);
    }
}
