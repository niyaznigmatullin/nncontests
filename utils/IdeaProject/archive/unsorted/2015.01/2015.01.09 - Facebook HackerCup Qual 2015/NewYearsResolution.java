package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class NewYearsResolution {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] z = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            z[i] = in.nextInt();
        }
        out.print("Case #" + testNumber + ": ");
        for (int mask = 0; mask < 1 << n; mask++) {
            int sa = 0;
            int sb = 0;
            int sc = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 0) continue;
                sa += x[i];
                sb += y[i];
                sc += z[i];
            }
            if (sa == a && sb == b && sc == c) {
                out.println("yes");
                return;
            }
        }
        out.println("no");  
    }
}
