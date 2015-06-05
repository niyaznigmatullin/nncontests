package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String s = in.next();
        int[] have = new int[26];
        int ans = 0;
        for (int i = 0; i + 1 < n; i++) {
            char key = s.charAt(2 * i);
            char door = Character.toLowerCase(s.charAt(2 * i + 1));
            have[key - 'a']++;
            if (have[door - 'a'] > 0) {
                --have[door - 'a'];
            } else {
                ++ans;
            }
        }
        out.println(ans);
    }
}
