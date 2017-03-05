package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KindergartenAdventures {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        a = Arrays.copyOf(a, 2 * n);
        for (int i = 0; i < n; i++) {
            a[i + n] = a[i];
        }
        List<Integer>[] events = new List[2 * n];
        for (int i = 0; i < 2 * n; i++) events[i] = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++) {
            if (i - a[i] >= 0) events[i - a[i]].add(i);
        }
        int[] f = new int[2 * n];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (i - a[i] >= 0) {
                f[i] = 1;
                cur++;
            }
        }
        int ans = cur;
        int ansI = 0;
        for (int i = n; i < 2 * n; i++) {
            if (i - a[i] >= 0) {
                f[i] = 1;
                cur++;
            }
            for (int j : events[i - n]) {
                f[j]--;
                --cur;
            }
            if (ans < cur) {
                ans = cur;
                ansI = i - n + 1;
            }
        }
        out.println(ansI + 1);
    }
}
