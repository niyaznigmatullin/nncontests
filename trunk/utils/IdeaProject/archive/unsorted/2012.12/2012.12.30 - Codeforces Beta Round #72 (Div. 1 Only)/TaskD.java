package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        for (long i = 2; i * i <= k; i++) {
            if (k % i == 0) {
                out.println(0);
                return;
            }
        }
        out.println(get(b, k) - get(a - 1, k));
    }

    static int get(int n, int k) {
        if (n < k) {
            return 0;
        }
        if ((long) k * k > n) {
            return 1;
        }
        int[] primes = MathUtils.genPrimes(k);
        int m = n / k;
        int z = primes.length;
        if (z <= 20) {
            int answer = 0;
            for (int mask = 0; mask < 1 << z; mask++) {
                long mul = 1;
                for (int i = 0; i < z; i++) {
                    if (((mask >> i) & 1) == 0) {
                        continue;
                    }
                    mul *= primes[i];
                }
                int got = (int) (m / mul);
                if ((Integer.bitCount(mask) & 1) == 0) {
                    answer += got;
                } else {
                    answer -= got;
                }
            }
            return answer;
        } else {
            boolean[] good = new boolean[m + 1];
            for (int i : primes) {
                for (int j = i; j <= m; j += i) {
                    good[j] = true;
                }
            }
            int answer = 0;
            for (int i = 1; i <= m; i++) {
                if (!good[i]) {
                    ++answer;
                }
            }
            return answer;
        }
    }
}
