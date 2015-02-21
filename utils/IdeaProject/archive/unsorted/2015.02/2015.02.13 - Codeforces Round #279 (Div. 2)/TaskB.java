package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            if (b[i] < 0) --b[i];
        }
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
        Set<Integer> f = new HashSet<>();
        Map<Integer, Integer> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (f.contains(a[i])) f.remove(a[i]);
            else f.add(a[i]);
            if (f.contains(b[i])) f.remove(b[i]);
            else f.add(b[i]);
            g.put(a[i], b[i]);
        }
        int first = g.get(-1);
        int[] ans = new int[n];
        int cur;
        cur = 1;
        f.remove(-1);
        while (first >= 0) {
            ans[cur] = first + 1;
            if (f.contains(first)) break;
            cur += 2;
            first = g.get(first);
        }
        int prev = -1;
        for (int i = 0; i < n; i++) if (f.contains(a[i])) {
            prev = a[i];
            first = b[i];
        }
        f.remove(prev);
        ans[0] = prev + 1;
        cur = 2;
        while (first >= 0) {
            ans[cur] = first + 1;
            if (f.contains(first)) break;
            cur += 2;
            first = g.get(first);
        }
        out.printArray(ans);
    }
}
