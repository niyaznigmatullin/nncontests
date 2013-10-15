package lib.test.on2013_02.on2013_02_17_Facebook_HackerCup_Round_3.DigitsWar;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Matrix;

import java.util.Random;

public class DigitsWar {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        test();
        System.err.println("test " + testNumber);
        long k = in.nextLong();
        out.print("Case #" + testNumber + ": ");
        int[][] a = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                a[i][j] = in.nextInt();
            }
        }
        out.println(solve(a, k));
    }

    static final int MOD = 1_000_000_007;

    static void test() {
        Random rand = new Random(12323L);
        while (true) {
            int[][] a = new int[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = i; j < 10; j++) {
                    a[i][j] = a[j][i] = rand.nextInt(10) == 0 ? 1 : 0;
                }
            }
//            int n = rand.nextInt(6) + 1;
            long n = Math.abs(rand.nextLong()) % 1000000000_000000000L + 1;
            int ans1 = solve(a, n);
//            int ans2 = stupid(n, a);
//            if (ans1 != ans2) {
//                throw new AssertionError();
//            }
            System.out.println("OK " + ans1);
        }
    }

    static int solve(int[][] war, long k) {
        int[][] a = new int[100][100];
        for (int i = 0; i < 100; i++) {
            int d1 = i / 10;
            int d2 = i % 10;
            if (war[d1][d2] == 1) {
                continue;
            }
            for (int d = 0; d < 10; d++) {
                if (war[d1][d] == 1 || war[d2][d] == 1) {
                    continue;
                }
                a[i][i % 10 * 10 + d] = 1;
            }
        }
        if (k == 1) {
            return 9;
        }
        Matrix m = new Matrix(a);
        m = powSumMod(m, k - 2, MOD);
        long ans = 9;
        for (int i = 0; i < 100; i++) {
            int d1 = i / 10;
            int d2 = i % 10;
            if (war[d1][d2] == 1) {
                continue;
            }
            long cur = 0;
            for (int j = 0; j < 100; j++) {
                if (j / 10 == 0) {
                    continue;
                }
                cur = (cur + m.get(j, i)) % MOD;
            }
            ans = (ans + cur) % MOD;
        }
        return (int) (ans % MOD);
    }

    static int stupid(long k, int[][] war) {
        int n = (int) k;
        return go(0, n, 0, war);
    }

    static int go(int x, int n, int num, int[][] war) {
        int ret = 0;
        if (x != 0) {
            ret++;
        }
        if (x == n) {
            return ret;
        }
        int last = x == 0 ? -1 : num % 10;
        int prelast = x <= 1 ? -1 : num / 10 % 10;
        for (int d = 0; d < 10; d++) {
            if (last >= 0 && war[last][d] == 1) {
                continue;
            }
            if (prelast >= 0 && war[prelast][d] == 1) {
                continue;
            }
            if (x == 0 && d == 0) {
                continue;
            }
            ret += go(x + 1, n, num * 10 + d, war);
        }
        return ret;
    }

    static Matrix powSumMod(Matrix m, long a, int mod) {
        Matrix ans = new Matrix(m.n, m.n);
        Matrix sum = m;
        while (a > 0) {
            if ((a & 1) == 1) {
                ans = ans.multiplyMod(m, mod).addMod(sum, mod);
            }
            sum = sum.multiplyMod(m, mod).addMod(sum, mod);
            m = m.multiplyMod(m, mod);
            a >>= 1;
        }
        int[][] ret = new int[m.n][m.n];
        for (int i = 0; i < ret.length; i++) {
            ret[i][i] = 1;
        }
        ans = ans.addMod(new Matrix(ret), mod);
        return ans;
    }
}
