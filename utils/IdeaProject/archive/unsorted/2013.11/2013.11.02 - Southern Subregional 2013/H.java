package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class H {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() - 1;
        char[] c = in.next().toCharArray();
        char[] ans = new char[c.length + 1];
        for (int i = 0; i < c.length; ) {
            int j = i;
            char cur = 0;
            while (j < c.length && (c[j] == '=' || cur == 0 || cur == c[j])) {
                if (c[j] != '=') {
                    cur = c[j];
                }
                ++j;
            }
            if (cur == 0) {
                Arrays.fill(ans, 'a');
                out.println(ans);
                return;
            }
            if (cur == '<') {
                ans[i] = 'a';
                for (int k = i; k < j; k++) {
                    if (c[k] == '=') {
                        ans[k + 1] = ans[k];
                    } else {
                        ans[k + 1] = (char) (ans[k] + 1);
                    }
                }
                if (ans[j] - 'a' > n) {
                    out.println(-1);
                    return;
                }
                ans[j] = (char) ('a' + n);
                for (int k = j - 1; k >= i; k--) {
                    if (c[k] == '=') {
                        ans[k] = ans[j];
                    } else break;
                }
            } else {
                ans[i] = (char) ('a' + n);
                for (int k = i; k < j; k++) {
                    if (c[k] == '=') {
                        ans[k + 1] = ans[k];
                    } else {
                        ans[k + 1] = (char) (ans[k] - 1);
                    }
                }
                if (ans[j] < 'a') {
                    out.println(-1);
                    return;
                }
                ans[j] = 'a';
                for (int k = j - 1; k >= i; k--) {
                    if (c[k] == '=') {
                        ans[k] = ans[j];
                    } else break;
                }
            }
            i = j;
        }
        out.println(ans);
    }
}
