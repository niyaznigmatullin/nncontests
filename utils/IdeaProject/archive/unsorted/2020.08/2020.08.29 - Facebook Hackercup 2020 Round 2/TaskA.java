package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {

    static int[] readArray(FastScanner in, int n, int k) {
        int[] ret = new int[n];
        for (int i = 0; i < k; i++) {
            ret[i] = in.nextInt();
        }
        long a = in.nextInt();
        long b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        for (int i = k; i < n; i++) {
            ret[i] = (int) ((ret[i - 2] * a + ret[i - 1] * b + c) % d);
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] s = readArray(in, n, k);
        int[] x = readArray(in, n, k);
        int[] y = readArray(in, n, k);
        for (int i = 0; i < n; i++) y[i] += x[i];
        long incL = 0;
        long incR = 0;
        long decL = 0;
        long decR = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] < x[i]) {
                incL += x[i] - s[i];
            }
            if (s[i] < y[i]) {
                incR += y[i] - s[i];
            }
            if (s[i] > y[i]) {
                decL += s[i] - y[i];
            }
            if (s[i] > x[i]) {
                decR += s[i] - x[i];
            }
        }
        out.print("Case #" + testNumber + ": ");
        if (incR < decL || decR < incL) {
            out.println(-1);
            return;
        }
        out.println(Math.max(incL, decL));
    }
}
