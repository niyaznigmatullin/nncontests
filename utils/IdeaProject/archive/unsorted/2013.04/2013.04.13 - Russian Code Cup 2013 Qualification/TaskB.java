package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        Arrays.sort(a);
        long ans = 0;
        long z = 0;
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                ++j;
            }
            if (j - i > 1) {
                b.add(a[i]);
                c.add(j - i);
                int d = j - i;
                ans += (long) d * (d - 1) * (d - 2) * (d - 3) / 24;
                int cd = d * (d - 1) / 2;
                ans += z * cd;
                z += cd;
                long ccd = (long) d * (d - 1) * (d - 2) / 6;
                for (int k = 0; k < n; k++) {
                    if (a[k] == a[i]) continue;
                    if (a[k] >= 3 * a[i]) {
                        break;
                    }
                    ans += ccd;
                }
            }
            i = j;
        }
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                ++j;
            }
            int f = j;
            for (int k = 0; k < b.size(); k++) {
                int e = b.get(k);
                if (e == a[i]) {
                    continue;
                }
                while (f < n && a[i] + 2 * e > a[f]) {
                    ++f;
                }
                int ways = f - j;
                int ways2 = j - i;
                int p = c.get(k) * (c.get(k) - 1) >> 1;
                if (e > a[i]) {
                    ways -= c.get(k);
                }
                ans += (long) p * ways * ways2;
            }
            i = j;
        }
        out.println(ans);
    }
}
