package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Yachtzee {

    static double get(long from, long to, int[] c, double length) {
        long cur = 0;
        double ret = 0;
        for (int i : c) {
            long left = cur;
            long right = cur + i;
            left = Math.max(left, from);
            right = Math.min(right, to);
            if (left <= right) {
                left -= cur;
                right -= cur;
                ret += 0.5 * (left + right) * (right - left) / length;
            }
            cur += i;
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] c = in.readIntArray(n);
        long sum = 0;
        for (int i : c) {
            sum += i;
        }
        double expFull = get(0, sum, c, b - a);
        double ans = 0;
        if (a / sum != b / sum) {
            ans += (b / sum - a / sum - 1) * expFull;
            ans += get(a % sum, sum, c, b - a);
            ans += get(0, b % sum, c, b - a);
        } else {
            ans += get(a % sum, b % sum, c, b - a);
        }
        out.println("Case #" + testNumber + ": " + ans);
        System.err.println("Case #" + testNumber + ": " + ans);
    }
}
