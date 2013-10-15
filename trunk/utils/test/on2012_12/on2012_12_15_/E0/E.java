package lib.test.on2012_12.on2012_12_15_.E0;




import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

import java.util.Arrays;
import java.util.Comparator;

public class E {

    static final int MOD = 1000000007;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        try {
            int n = in.nextInt();
            byte[][] a = new byte[n][];
            int allLen = 1;
            for (int i = 0; i < n; i++) {
                String s = in.next();
                a[i] = new byte[s.length() + 1];
                for (int j = 0; j < s.length(); j++) {
                    a[i][j] = (byte) s.charAt(j);
                }
                allLen += a[i].length;
            }
            Arrays.sort(a, new Comparator<byte[]>() {
                public int compare(byte[] o1, byte[] o2) {
                    for (int i = 0; i < o1.length; i++) {
                        if (o1[i] != o2[i]) {
                            return (int) o1[i] - o2[i];
                        }
                    }
                    throw new AssertionError();
                }
            });
            int[] lcp = new int[n];
            for (int i = 0; i + 1 < n; i++) {
                int k = 0;
                while (a[i][k] == a[i + 1][k]) {
                    ++k;
                }
                lcp[i] = k;
            }
            lcp[n - 1] = -1;
            int[] fact = new int[30];
            fact[0] = 1;
            for (int i = 1; i < fact.length; i++) {
                fact[i] = (int) ((long) fact[i - 1] * i % MOD);
            }
            long ans = 1;
            for (int i = 0; i + 1 < n; i++) {
                int last = -1;
                if (i > 0) {
                    last = lcp[i - 1];
                }
                int curLCP = lcp[i];
                int count = 2;
                for (int j = i + 1; j < n; j++) {
                    if (curLCP <= last) {
                        break;
                    }
                    if (lcp[j] < curLCP) {
//                        System.out.println(i + " " + j + " " + count + " " + lcp[j] + " " + curLCP);
                        ans = ans * fact[count] % MOD;
                        count = 2;
                        curLCP = lcp[j];
                    } else if (lcp[j] == curLCP) {
                        ++count;
                    }
                }
            }
            out.println(ans);
        } catch (OutOfMemoryError e) {

        }
    }
}
