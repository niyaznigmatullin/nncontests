package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class Ackerman {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int mod = in.nextInt();
        int[] phi = new int[mod + 1];
        for (int i = 2; i <= mod; i++) {
            for (int j = 1; j < i; j++) {
                if (MathUtils.gcd(i, j) == 1) {
                    phi[i]++;
                }
            }
        }
        int[] a = new int[mod + 1];
        int cn = 0;
        for (int i = mod; i >= 1; i = phi[i]) {
            a[cn++] = i;
        }
        int[] answers = new int[101];
        for (int i = 1; i < answers.length; i++) {
            if (i >= cn) answers[i] = answers[i - 1]; else {
                int ans = 2 % a[i - 1];
                for (int j = i - 2; j >= 0; j--) {
                    ans = MathUtils.modPow(2, ans, a[j]);
                }
                answers[i] = ans;
            }
        }
        int test = 0;
        while (true) {
            ++test;
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            out.print("Case " + test + ": ");
            if (n == 1) {
                out.println(2 * m % mod);
            } else if (n == 2) {
                int ans = 1;
                for (int j = 0; j < m; j++) {
                    ans = ans * 2 % mod;
                }
                out.println(ans);
            } else if (n == 3) {
                out.println(answers[m]);
            } else {
                if (m <= 2) out.println(answers[m]); else out.println(answers[100]);
            }
        }
    }
}
