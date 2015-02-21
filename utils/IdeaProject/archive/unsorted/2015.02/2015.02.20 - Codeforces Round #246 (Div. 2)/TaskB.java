package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[1234567];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
            c[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            a[b[i]]++;
        }
        int[] home = new int[n];
        int[] away = new int[n];
        Arrays.fill(home, (n - 1));
        Arrays.fill(away, (n - 1));
        for (int i = 0; i < n; i++) {
            int x = a[c[i]];
            away[i] -= x;
            home[i] += x;
        }
        for (int i = 0; i < n; i++) out.println(home[i] + " " + away[i]);
    }
}
