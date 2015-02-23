package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int[][] a = new int[2][2];
        for (int i = 0; i < s.length(); i++) {
            a[i % 2][s.charAt(i) - 'a']++;
        }
        long ans0 = 0;
        long ans1 = 0;
        for (int i = 0; i < 2; i++) {
            ans0 += (long) a[0][i] * a[1][i];
            ans1 += (long) a[0][i] * (a[0][i] + 1) / 2 + (long) a[1][i] * (a[1][i] + 1) / 2;
        }
        out.println(ans0 + " " + ans1);
    }
}
