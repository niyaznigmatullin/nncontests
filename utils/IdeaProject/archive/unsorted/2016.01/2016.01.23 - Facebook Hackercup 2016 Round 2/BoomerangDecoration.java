package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class BoomerangDecoration {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        in.nextInt();
        String s = in.next();
        String t = in.next();
        int[] prefix = get(s, t);
        int[] suffix = get(new StringBuilder(s).reverse().toString(), new StringBuilder(t).reverse().toString());
        ArrayUtils.reverse(suffix);
        int ans = Integer.MAX_VALUE;
        for (int i = -1; i < prefix.length; i++) {
            int val1 = i >= 0 ? prefix[i] : 0;
            int val2 = i + 1 < suffix.length ? suffix[i + 1] : 0;
            ans = Math.min(ans, Math.max(val1, val2));
        }
        out.println("Case #" + testNumber + ": " + ans);
    }

    static int[] get(String s, String t) {
        int n = s.length();
        int[] ret = new int[n];
        int[] blocks = new int[n];
        for (int i = 0, b = 1; i < n; b++) {
            int j = i;
            while (j < n && t.charAt(j) == t.charAt(i)) {
                ++j;
            }
            while (i < j) {
                blocks[i++] = b;
            }
        }
        int val = 0;
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && s.charAt(j) == t.charAt(j)) {
                ++j;
            }
            while (i < j) {
                ret[i++] = val;
            }
            if (i < n) {
                val = blocks[i];
                ret[i++] = val;
            }
        }
        return ret;
    }
}
