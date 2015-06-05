package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class ATMWithdrawal {

    static int[] q = new int[]{1, 2, 3, 5};
    static int[] dp1 = new int[200];
    static long[] dp2 = new long[200];

    static {
        Arrays.fill(dp1, Integer.MAX_VALUE);
        dp1[0] = 0;
        dp2[0] = 1;
        for (int i : q) {
            for (int j = i; j < dp1.length; j++) {
                if (dp1[j] > dp1[j - i] + 1) {
                    dp1[j] = dp1[j - i] + 1;
                    dp2[j] = 0;
                }
                if (dp1[j] == dp1[j - i] + 1) {
                    dp2[j] += dp2[j - i];
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long w = in.nextLong();
        int c = in.nextInt();
        out.println(solveIt(w, c));
//        if (!solveIt(w, c).equals(stupid(w, c))) {
//            System.out.println("BAAAAAAAAAAAAAAAAAAAAAAAAD");
//            System.out.println(w + " " + c);
//            throw new AssertionError();
//        }
//        System.out.println("stupid = " + stupid(w, c));
    }

    static String stupid(long w_, int c) {
        if (w_ % 1000 != 0) {
            return "0";
        }
        w_ /= 1000;
        int w = (int) w_;
        int[] dp1 = new int[w + 1];
        long[] dp2 = new long[w + 1];
        Arrays.fill(dp1, Integer.MAX_VALUE);
        dp1[0] = 0;
        dp2[0] = 1;
        for (int i : q) {
            for (int e = 0; e <= c; e++, i *= 10) {
                for (int j = i; j < dp1.length; j++) {
                    if (dp1[j] > dp1[j - i] + 1) {
                        dp1[j] = dp1[j - i] + 1;
                        dp2[j] = 0;
                    }
                    if (dp1[j] == dp1[j - i] + 1) {
                        dp2[j] += dp2[j - i];
                    }
                }
            }
        }
        return dp1[w] + " " + dp2[w];
    }

    static String solveIt(long w, int c) {
        if (w % 1000 != 0) {
            return "0";
        }
        w /= 1000;
        long ways = 1;
        long need = 0;
        for (int i = 0; i < c; i++) {
            int d = (int) (w % 10);
            ways *= dp2[d];
            need += dp1[d];
            w /= 10;
        }
        if (w >= 100) {
            need += 20 * (w / 100 - 1);
            w %= 100;
            w += 100;
        }
        need += dp1[(int) w];
        ways *= dp2[(int) w];
        return need + " " + ways;
    }
}
