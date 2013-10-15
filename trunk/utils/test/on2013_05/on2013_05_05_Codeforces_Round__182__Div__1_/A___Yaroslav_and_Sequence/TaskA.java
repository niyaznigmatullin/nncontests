package lib.test.on2013_05.on2013_05_05_Codeforces_Round__182__Div__1_.A___Yaroslav_and_Sequence;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(2 * n - 1);
        int countNegative = 0;
        for (int i : a) {
            if (i < 0) countNegative++;
        }
        int ans = 0;
        for (int i : a) ans += Math.abs(i);
        if ((n & 1) == 1) {
            out.println(ans);
        } else {
            int min = Integer.MAX_VALUE;
            for (int i : a) min = Math.min(min, Math.abs(i));
            if ((countNegative & 1) == 1) {
                out.println(ans - 2 * min);
            } else {
                out.println(ans);
            }
        }
    }
}
