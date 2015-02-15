package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] a = new int[6];
        for (int i = 0; i < 6; i++) a[i] = in.nextInt();
        Arrays.sort(a);
        if (a[0] != a[3] && a[1] != a[4] && a[2] != a[5]) {
            out.println("Alien");
        } else {
            int x = 0;
            for (int i : a) x ^= i;
            if (x == 0) out.println("Elephant"); else out.println("Bear");
        }
    }
}
