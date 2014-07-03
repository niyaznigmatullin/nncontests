package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class Merge {

    static BigInteger[][] C;
    static BigInteger answer;

    static {
        C = new BigInteger[222][222];
        for (BigInteger[] e : C) {
            Arrays.fill(e, BigInteger.ZERO);
        }
        for (int i = 0; i < C.length; i++) {
            C[i][0] = BigInteger.ONE;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1].add(C[i - 1][j]);
            }
        }
    }


    static int comps;
    static int[] ans;

    static void merge(int l, int r) {
        if (l == r) return;
        int m = (l + r) / 2;
        if (r - m > 1) {
            int t = ans[m];
            ans[m] = ans[r - 1];
            ans[r - 1] = t;
            Arrays.sort(ans, m + 1, r);
        }
        comps += r - l;
        answer = answer.multiply(C[r - m - 1 + m - l][m - l]);
        answer = answer.shiftLeft(1);
        merge(l, m);
        merge(m + 1, r);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        answer = BigInteger.ONE;
        ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }
        comps = 0;
        merge(0, n - 1);
        out.println(comps);
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(ans[i] + 1);
        }
        out.println();
        out.println(answer);
    }
}
