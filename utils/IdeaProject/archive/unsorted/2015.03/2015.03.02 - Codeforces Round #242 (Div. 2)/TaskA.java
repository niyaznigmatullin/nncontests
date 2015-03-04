package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[] c = in.next().toCharArray();
        int lower = 0;
        for (char e : c) {
            if (e == 'x') ++lower;
        }
        int ans = 0;
        all: while (lower < n / 2) {
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 'X') {
                    c[i] = 'x';
                    lower++;
                    ++ans;
                    continue all;
                }
            }
        }
        all: while (lower > n / 2) {
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 'x') {
                    c[i] = 'X';
                    lower--;
                    ++ans;
                    continue all;
                }
            }
        }
        out.println(ans);
        out.println(c);
    }
}
