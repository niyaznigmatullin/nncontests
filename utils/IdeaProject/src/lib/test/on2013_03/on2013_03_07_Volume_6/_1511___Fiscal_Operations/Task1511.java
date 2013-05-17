package lib.test.on2013_03.on2013_03_07_Volume_6._1511___Fiscal_Operations;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Task1511 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String b = rev(in.next());
        String c = rev(in.next());
        String a = rev(in.next());
        if (a.length() < b.length() || a.length() < c.length()) {
            out.println(-1);
            return;
        }
        int[] cost = new int[]{0, Integer.MAX_VALUE};
        for (int i = 0; i < a.length(); i++) {
            int[] next = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int carry = 0; carry < 2; carry++) {
                if (cost[carry] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int d1 = 0; d1 < 10; d1++) {
                    if (d1 != 0 && i >= b.length() || d1 == 0 && i + 1 == b.length()) {
                        continue;
                    }
                    int c1 = i >= b.length() ? 0 : Math.abs(d1 - (b.charAt(i) - '0'));
                    for (int d2 = 0; d2 < 10; d2++) {
                        if (d2 != 0 && i >= c.length() || d2 == 0 && i + 1 == c.length()) {
                            continue;
                        }
                        int c2 = i >= c.length() ? 0 : Math.abs(d2 - (c.charAt(i) - '0'));
                        int g = d1 + d2 + carry;
                        int d0 = g % 10;
                        int nc = g / 10;
                        if (d0 == 0 && i + 1 == a.length()) {
                            continue;
                        }
                        next[nc] = Math.min(next[nc], cost[carry] + c1 + c2 + Math.abs(d0 - a.charAt(i) + '0'));
                    }
                }
            }
            cost = next;
        }
        out.println(cost[0] == Integer.MAX_VALUE ? -1 : cost[0]);
    }

    static String rev(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
