package lib.test.on2013_01.on2013_01_02_January_Challenge_2013.Little_Elephant_and_Alcohol;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class LittleElephantAndAlcohol {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt() - 1;
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] dp = new int[1 << k];
        for (int i = 0; i < 1 << k; i++) {
            dp[i] = Integer.bitCount(i);
        }
        for (int i = k; i < n; i++) {
            int[] next = new int[1 << k];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int maskH = 0; maskH < 1 << k; maskH++) {
                int val = dp[maskH];
                if (val == Integer.MAX_VALUE) {
                    continue;
                }
                for (int add = 0; add < 2; add++) {
                    int mask = (maskH << 1) | add;
                    int maximal = 0;
                    int count = 0;
                    for (int j = 0; j <= k; j++) {
                        maximal = Math.max(maximal, a[i - j] + ((mask >> j) & 1));
                    }
                    for (int j = 0; j <= k; j++) {
                        if (maximal == a[i - j] + ((mask >> j) & 1)) {
                            ++count;
                        }
                    }
                    if (count < m) {
                        int nMask = mask & ((1 << k) - 1);
                        next[nMask] = Math.min(next[nMask], val + add);
                    }
                }
            }
            dp = next;
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 1 << k; i++) {
            if (answer > dp[i]) {
                answer = dp[i];
            }
        }
        out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
