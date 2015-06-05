package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {

    static boolean check(String t, String s) {
        int cur = 0;
        for (int i = 0; i < t.length(); i++) {
            if (cur < s.length() && t.charAt(i) == s.charAt(cur)) {
                ++cur;
            }
        }
        return cur == s.length();
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.next();
        String s = in.next();
        String t = in.next();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                int ans = 0;
                {
                    String f = s.substring(0, i) + t.charAt(i) + s.substring(i);
                    if (check(f, s) && check(f, t)) ++ans;
                }
                {
                    String f = t.substring(0, i) + s.charAt(i) + t.substring(i);
                    if (check(f, s) && check(f, t)) ++ans;
                }
                out.println(ans);
                return;
            }
        }
    }
}
