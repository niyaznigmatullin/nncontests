package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int k = in.nextInt();
        String[] ans = solve(s, k);
        if (ans == null) {
            out.println("NO");
        } else {
            out.println("YES");
            for (String e : ans) {
                out.println(e);
            }
        }
    }

    static String[] solve(String s, int k) {
        if (s.length() > 15) {
            String[] ans = new String[k];
            for (int i = 0; i < k - 1; i++) {
                ans[i] = s.substring(0, i + 1);
                s = s.substring(i + 1);
            }
            ans[k - 1] = s;
            return ans;
        }
        prev = new String[k];
        if (go(s, 0, 0, k)) {
            return prev;
        }
        return null;
    }

    static String[] prev;

    static boolean go(String s, int start, int x, int k) {
        if (x == k) {
            return start == s.length();
        }
        all:
        for (int i = start + 1; i <= s.length(); i++) {
            String cur = s.substring(start, i);
            for (int j = 0; j < x; j++) {
                if (cur.equals(prev[j])) {
                    continue all;
                }
            }
            prev[x] = cur;
            if (go(s, i, x + 1, k)) {
                return true;
            }
        }
        return false;
    }
}
