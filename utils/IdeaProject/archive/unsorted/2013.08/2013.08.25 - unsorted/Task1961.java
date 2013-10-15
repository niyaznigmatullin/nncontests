package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1961 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int N = in.nextInt();
        int M = (int) ((long) N * m / n);
        if (M + 1 <= N) {
            long num = (long) (N - M - n + m) * (M + 1);
            long den = (long) (N - M) * (M - m + 1);
            if (num >= den) {
                ++M;
            }
        }
        out.println(M);
    }
}
