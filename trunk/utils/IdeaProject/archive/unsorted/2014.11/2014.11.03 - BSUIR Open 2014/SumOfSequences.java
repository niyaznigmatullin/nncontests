package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Random;

public class SumOfSequences {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(m);
//        int[] a = new int[n];
//        int[] b = new int[m];
//        Random rand = new Random(123L);
//        for (int i = 0; i < n; i++) {
//            a[i] = rand.nextInt(10000) + 1;
//        }
//        for (int i = 0; i < m; i++) {
//            b[i] = rand.nextInt(10000) + 1;
//        }
        int mx = 0;
        for (int i : a) mx = Math.max(mx, i);
        for (int i : b) mx = Math.max(mx, i);
        int[] ca = new int[mx + 1];
        long[] sa = new long[mx + 1];
        int[] cb = new int[mx + 1];
        long[] sb = new long[mx + 1];
        for (int i = 0; i < a.length; i++) {
            ca[a[i]]++;
            sa[a[i]] += i;
        }
        for (int i = 0; i < m; i++) {
            cb[b[i]]++;
            sb[b[i]] += i;
        }
        long ans = 0;
        for (int i = 0; i <= mx; i++) {
            if (ca[i] == 0) continue;
            long a1 = sa[i];
            long a2 = ca[i];
            for (int j = 0; j <= mx; j++) {
                ans += (long) (i < j ? j - i : i - j) * (cb[j] * a1 - sb[j] * a2);
            }
        }
        out.println(ans);
    }
}
