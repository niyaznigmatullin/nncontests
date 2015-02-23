package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            long n = in.nextLong();
            long k = in.nextLong();
            long d2 = in.nextLong();
            long d1 = in.nextLong();
            boolean can = false;
            for (int f = -1; f <= 1; f += 2) {
                for (int g = -1; g <= 1; g += 2) {
                    long q = (k - f * d1 - g * d2);
                    if (q < 0 || q % 3 != 0) continue;
                    long x = q / 3;
                    long y = x + f * d1;
                    long z = x + g * d2;
                    if (y < 0 || z < 0) continue;
                    if (n % 3 != 0 || n / 3 < Math.max(x, Math.max(y, z))) {
                        continue;
                    }
                    can = true;
                }
            }
            out.println(can ? "yes" : "no");
        }
    }
}
