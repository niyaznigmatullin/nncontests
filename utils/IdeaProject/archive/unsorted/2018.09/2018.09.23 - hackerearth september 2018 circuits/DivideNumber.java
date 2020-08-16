package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DivideNumber {

    static final int MAXN = 40000;

    static int[][] divisors = new int[MAXN + 1][];
    static {
        int[] count = new int[MAXN + 1];
        for (int i = 1; i <= MAXN; i++) {
            for (int j = i; j <= MAXN; j += i) {
                count[j]++;
            }
        }
        for (int i = 0; i <= MAXN; i++) divisors[i] = new int[count[i]];
        Arrays.fill(count, 0);
        for (int i = 1; i <= MAXN; i++) {
            for (int j = i; j <= MAXN; j += i) {
                divisors[j][count[j]++] = i;
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long ans = -1;
        for (int a : divisors[n]) {
            for (int b : divisors[n]) {
                if (a + b > n - 2) continue;
                for (int c : divisors[n]) {
                    if (a + b + c > n - 1) {
                        continue;
                    }
                    int d = n - a - b - c;
                    if (Arrays.binarySearch(divisors[n], d) >= 0) {
                        ans = Math.max(ans, (long) a * b * c * d);
                    }
                    if (c == b) break;
                }
                if (a == b) break;
            }
        }
        out.println(ans);
    }
}
