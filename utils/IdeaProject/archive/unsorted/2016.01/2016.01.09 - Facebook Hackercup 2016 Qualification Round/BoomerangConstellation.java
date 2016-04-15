package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BoomerangConstellation {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> d = new HashMap<>();
            for (int j = 0; j < n; j++) {
                int dx = x[i] - x[j];
                int dy = y[i] - y[j];
                Integer q = dx * dx + dy * dy;
                Integer f = d.get(q);
                if (f != null) {
                    ans += f;
                } else {
                    f = 0;
                }
                d.put(q, f + 1);
            }
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
