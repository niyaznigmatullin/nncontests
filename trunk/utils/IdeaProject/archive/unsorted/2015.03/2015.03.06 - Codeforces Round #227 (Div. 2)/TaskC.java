package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int[] len = new int[c.length + 1];
        int[] ans = new int[c.length + 1];
        len[c.length] = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] == '0') {
                len[i] = Integer.MAX_VALUE;
                continue;
            }
            ans[i] = Integer.MIN_VALUE;
            len[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j <= c.length; j++) {
                if (j - i < len[j]) {
                    continue;
                }
                if (j - i == len[j]) {
                    boolean ok = true;
                    for (int e = 0; e < len[j]; e++) {
                        if (c[i + e] < c[j + e]) {
                            ok = false;
                            break;
                        }
                        if (c[i + e] > c[j + e]) {
                            ok = true;
                            break;
                        }
                    }
                    if (ok) {
                        len[i] = len[j];
                        ans[i] = ans[j] + 1;
                        break;
                    }
                } else {
                    len[i] = j - i;
                    ans[i] = ans[j] + 1;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(len));
        System.out.println(Arrays.toString(ans));
        out.println(ans[0]);
    }
}
