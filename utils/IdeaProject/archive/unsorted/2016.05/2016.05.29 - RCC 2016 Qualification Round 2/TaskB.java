package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(m);
        String s = in.next();
        int curA = 0;
        int curB = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        ArrayUtils.reverse(a);
        long money = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                money += a[curA++];
            } else {
                if (money >= b[curB]) {
                    money -= b[curB++];
                } else {
                    ++ans;
                }
            }
        }
        out.println(ans);
    }
}
