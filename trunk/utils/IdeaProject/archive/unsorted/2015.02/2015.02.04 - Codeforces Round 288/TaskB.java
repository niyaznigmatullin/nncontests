package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int first = -1;
        int second = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) % 2 == 0) {
                if (s.charAt(i) > s.charAt(s.length() - 1)) {
                    second = i;
                } else if (first < 0) {
                    first = i;
                }
            }
        }
        char[] ans = s.toCharArray();
        if (first >= 0) {
            char t = ans[first];
            ans[first] = ans[ans.length - 1];
            ans[ans.length - 1] = t;
            out.println(ans);
        } else if (second >= 0) {
            char t = ans[second];
            ans[second] = ans[ans.length - 1];
            ans[ans.length - 1] = t;
            out.println(ans);
        } else {
            out.println("-1");
        }
    }
}
