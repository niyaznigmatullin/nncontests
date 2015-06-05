package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                String f = s.substring(0, i) + s.substring(j);
                if (f.equals("CODEFORCES")) {
                    out.println("YES");
                    return;
                }
            }
        }
        out.println("NO");
    }
}
