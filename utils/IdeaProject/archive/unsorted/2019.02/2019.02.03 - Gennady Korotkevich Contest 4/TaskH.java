package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TaskH {

    final int C = (1 << 24) - 100;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt(), k = in.nextInt();
        int start = 1;
        outer: for (int i = 0; i < n; i++) {
            while (start + C <= k) {
                char c = ask(in, out, start + C);
                if (c == '=') {
                    start = start + C + 1;
                    continue outer;
                }
                if (c == '>') {
                    start = start + C + 1;
                } else {
                    break;
                }
            }

            int l = start, r = Math.min(start + C, k + 1);
            while (true) {
                int m = (l + r) / 2;
                char c = ask(in, out, m);
                if (c == '=') {
                    start = m + 1;
                    break;
                }
                if (c == '<') {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }
    }

    private char ask(FastScanner in, FastPrinter out, int m) {
        out.println(m);
        out.flush();
        return in.next().charAt(0);
    }


}
