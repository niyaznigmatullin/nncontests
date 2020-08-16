package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int mn = Integer.MAX_VALUE;
        for (int x : a) mn = Math.min(mn, x);
        int count = 0;
        for (int x : a) if (x == mn) count++;
        if (count * 2 > n) {
            out.println("Bob");
            return;
        }
        out.println("Alice");
    }
}
