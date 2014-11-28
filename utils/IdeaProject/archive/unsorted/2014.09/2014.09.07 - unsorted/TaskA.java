package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {

    static boolean ok(char[] c, int i) {
        if (i > 0 && c[i] == c[i - 1]) return false;
        if (i > 1 && c[i] == c[i - 2]) return false;
        return true;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        char[] c = in.next().toCharArray();
        for (int i = n - 1; i >= 0; i--) {
            char[] d = c.clone();
            all:
            for (int j = d[i] - 'a' + 1; j < p; j++) {
                d[i] = (char) ('a' + j);
                if (!ok(d, i)) {
                    continue;
                }
                for (int k = i + 1; k < n; k++) {
                    boolean found = false;
                    for (int letter = 0; letter < p; letter++) {
                        d[k] = (char) ('a' + letter);
                        if (ok(d, k)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        continue all;
                    }
                }
                out.println(d);
                return;
            }
        }
        out.println("NO");
    }
}
