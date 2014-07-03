package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Palindromes {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int[] dp = new int[0];
        int[] maximal = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            int[] next = new int[i];
            int cur = 0;
            for (int j = 0; j < i; j++) {
                if (c[i] != c[j]) next[j] = 0; else {
                    next[j] = 1 + (j > 0 ? dp[j - 1] : 0);
                }
                cur = Math.max(cur, next[j]);
            }
            maximal[i] = cur;
            dp = next;
        }
        int ans = 0;
        for (int i = 0; i < c.length; i++) {
            int j = 0;
            while (i - j >= 0 && i + j < c.length && c[i - j] == c[i + j]) {
                if (j + j + 1 > maximal[i + j]) {
                    ++ans;
                }
                ++j;
            }
        }
        for (int i = 0; i + 1 < c.length; i++) {
            int j = 0;
            while (i - j >= 0 && i + j + 1 < c.length && c[i - j] == c[i + j + 1]) {
                if (j + j + 2 > maximal[i + j + 1]) {
                    ++ans;
                }
                ++j;
            }
        }
        out.println(ans);
    }
}
