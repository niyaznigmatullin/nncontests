package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashSet;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String[][] s = new String[n][];
        Set<String> all = new HashSet<>();
        Set<String> bad = new HashSet<>();
        for (int i = 0; i < n; i++) {
            s[i] = new String[]{in.next(), in.next()};
            String z = s[i][0].substring(0, 3);
            if (!all.add(z)) {
                bad.add(z);
            }
        }
        String[] ans = new String[n];
        while (true) {
            boolean changed = false;
            for (int i = 0; i < n; i++) {
                if (ans[i] != null) continue;
                if (bad.contains(s[i][0].substring(0, 3))) {
                    ans[i] = s[i][0].substring(0, 2) + s[i][1].charAt(0);
                    bad.add(ans[i]);
                    changed = true;
                }
            }
            if (!changed) break;
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == null) {
                ans[i] = s[i][0].substring(0, 3);
            }
        }
        all.clear();;
        for (int i = 0; i < n; i++) {
            if (!all.add(ans[i])) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.println(ans[i]);
        }
    }
}
