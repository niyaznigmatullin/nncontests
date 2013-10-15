package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        char[] t = new char[s.length()];
        int cur = 0;
        for (char c : s.toCharArray()) {
            if (cur > 0 && t[cur - 1] == c) {
                --cur;
            } else {
                t[cur++] = c;
            }
        }
        out.println(cur == 0 ? "Yes" : "No");
    }
}
