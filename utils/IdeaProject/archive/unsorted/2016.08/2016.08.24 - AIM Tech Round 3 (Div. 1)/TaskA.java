package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int start = 0;
        while (start + 1 < s.length() && s.charAt(start) == 'a') {
            ++start;
        }
        int end = start + 1;
        while (end < s.length() && s.charAt(end) != 'a') {
            end++;
        }
        char[] ans = s.toCharArray();
        for (int i = start; i < end; i++) {
            if (ans[i] == 'a') {
                ans[i] = 'z';
            } else {
                --ans[i];
            }
        }
        out.println(ans);
    }
}
