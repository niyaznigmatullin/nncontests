package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] s = in.next().toCharArray();
        long ans = 0;
        for (int at = 0; at < s.length; at++) {
            if (s[at] != '@') continue;
            int f = at + 1;
            while (f < s.length && (Character.isLetter(s[f]) || Character.isDigit(s[f]))) ++f;
            if (f >= s.length || s[f] != '.' || f - at < 2) continue;
            ++f;
            int end = 0;
            while (f < s.length && Character.isLetter(s[f])) {
                ++end;
                ++f;
            }
            int start = at - 1;
            while (start >= 0 && (s[start] == '_' || Character.isLetter(s[start]) || Character.isDigit(s[start]))) {
                if (Character.isLetter(s[start])) {
                    ans += end;
                }
                --start;
            }
        }
        out.println(ans);
    }
}
