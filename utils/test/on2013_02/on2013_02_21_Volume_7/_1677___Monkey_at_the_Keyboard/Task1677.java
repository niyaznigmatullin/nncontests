package lib.test.on2013_02.on2013_02_21_Volume_7._1677___Monkey_at_the_Keyboard;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class Task1677 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[] c = in.next().toCharArray();
        int[] p = new int[c.length];
        p[0] = -1;
        int k = -1;
        for (int i = 1; i < c.length; i++) {
            while (k > -1 && c[k + 1] != c[i]) {
                k = p[k];
            }
            if (c[k + 1] == c[i]) {
                ++k;
            }
            p[i] = k;
        }
        BigInteger ans = BigInteger.ZERO;
        BigInteger nb = BigInteger.valueOf(n);
        int z = 5;
        BigInteger nb10 = nb.pow(z);
        for (int i = c.length - 1; i >= 0; ) {
            ans = ans.add(BigInteger.ONE);
            int j = p[i];
            while (i - z >= j) {
                i -= z;
                ans = ans.multiply(nb10);
            }
            while (i > j) {
                i--;
                ans = ans.multiply(nb);
            }
        }
        out.println(ans);
    }
}
