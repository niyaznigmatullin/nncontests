package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.next();
        String s = in.next();
        String t = in.next();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int dif = Math.abs(s.charAt(i) - t.charAt(i));
            if (dif > 10 - dif) dif = 10 - dif;
            ans += dif;
        }
        out.println(ans);
    }
}
