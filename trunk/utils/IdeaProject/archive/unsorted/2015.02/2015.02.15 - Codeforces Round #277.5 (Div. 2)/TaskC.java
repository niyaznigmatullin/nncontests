package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int s = in.nextInt();
        if (s == 0) {
            if (m == 1) {
                out.println("0 0");
                return;
            }
            out.println("-1 -1");
            return;
        }
        if (s > 9 * m || s < 1) {
            out.println("-1 -1");
            return;
        }
        out.println(getMin(m, s) + " " + getMax(m, s));
    }

    static String getMin(int m, int s) {
        int first = Math.max(s - 9 * (m - 1), 1);
        s -= first;
        char[] ansMin = new char[m];
        ansMin[0] = (char) (first + '0');
        for (int i = m - 1; i > 0; i--) {
            int cur = Math.min(9, s);
            s -= cur;
            ansMin[i] = (char) (cur + '0');
        }
        return new String(ansMin);
    }

    static String getMax(int m, int s) {
        char[] ans = new char[m];
        for (int i = 0; i < m; i++) {
            int cur = Math.min(9, s);
            s -= cur;
            ans[i] = (char) (cur + '0');
        }
        return new String(ans);
    }
}
