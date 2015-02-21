package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String last = "00000000";
        int[] ans = new int[n];
        for (int currentNumber = 0; currentNumber < n; currentNumber++) {
            String s = in.next();
            if (s.startsWith("?")) s = "!" + s.substring(1);
            while (s.length() < 8) s = "0" + s;
            int found = -1;
            for (int len = 7; len >= 0; len--) {
                boolean canFirst = true;
                for (int i = 0; i < len; i++) {
                    if (s.charAt(i) == last.charAt(i)) continue;
                    if (s.charAt(i) == '!' && last.charAt(i) > '0') continue;
                    if (s.charAt(i) == '?') continue;
                    canFirst = false;
                    break;
                }
                if (!canFirst) continue;
                char[] c = s.toCharArray();
                if (last.charAt(len) == '9') continue;
                if (s.charAt(len) >= '0' && s.charAt(len) <= '9' && last.charAt(len) >= s.charAt(len)) continue;
                if (s.charAt(len) == '?' || s.charAt(len) == '!') c[len] = (char) (last.charAt(len) + 1);
                for (int i = 0; i < len; i++) {
                    if (c[i] == '?' || c[i] == '!') c[i] = last.charAt(i);
                }
                for (int i = len + 1; i < c.length; i++) if (c[i] == '?') c[i] = '0';
                s = new String(c);
                found = Integer.parseInt(s);
                break;
            }
            if (found < 0) {
                out.println("NO");
                return;
            }
            last = s;
            ans[currentNumber] = found;
        }
        out.println("YES");
        for (int i : ans) {
            out.println(i);
        }
    }
}
