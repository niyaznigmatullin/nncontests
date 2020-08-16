package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] first = new int[n];
        int[] last = new int[n];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        for (int i = 0; i < k; i++) {
            int x = in.nextInt() - 1;
            last[x] = i;
            if (first[x] < 0) first[x] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (first[i] < 0) ans++;
        for (int i = 0; i + 1 < n; i++) {
            if (last[i + 1] < 0 || first[i] < 0 || last[i + 1] < first[i]) ans++;
        }
        for (int i = 1; i < n; i++) {
            if (last[i - 1] < 0 || first[i] < 0 || last[i - 1] < first[i]) ans++;
        }
        out.println(ans);
    }
}
