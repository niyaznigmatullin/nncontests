package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Random;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        n++;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.equals("?")) {
                a[i] = Integer.MIN_VALUE;
            } else {
                a[i] = Integer.parseInt(s);
            }
        }
        int who = 0;
        int no = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != Integer.MIN_VALUE) {
                who ^= 1;
            } else no++;
        }
        if (k == 0) {
            if (a[0] == 0) {
                out.println("Yes");
            } else if (a[0] != Integer.MIN_VALUE) {
                out.println("No");
            } else {
                if (who == 0) {
                    out.println("No");
                } else {
                    out.println("Yes");
                }
            }
            return;
        }
        if (no == 0) {
            long z = 0;
            int MOD = BigInteger.probablePrime(30, new Random(System.nanoTime())).intValue();
            for (int i = n - 1; i >= 0; i--) {
                z = z * k + a[i];
                z %= MOD;
                if (z < 0) z += MOD;
            }
            if (z == 0) {
                out.println("Yes");
            } else {
                out.println("No");
            }
            return;
        }
        if (n % 2 == 1) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }
}
