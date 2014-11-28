package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.math.BigInteger;
import java.util.Arrays;

public class Factory {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            r[i] = in.nextInt();
        }
        boolean[][] has = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dx = x[i] - x[j];
                int dy = y[i] - y[j];
                int dr = r[i] + r[j];
                if (dx * dx + dy * dy == dr * dr) {
                    has[i][j] = has[j][i] = true;
                }
            }
        }
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        q[tail++] = 0;
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < n; i++) {
                if (d[i] == Integer.MAX_VALUE && has[v][i]) {
                    d[i] = d[v] + 1;
                    q[tail++] = i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (d[i] == Integer.MAX_VALUE) {
                out.println("not moving");
            } else {
                int num = r[0];
                int den = r[i];
                int g = MathUtils.gcd(num, den);
                num /= g;
                den /= g;
                if (den == 1) out.print(num);
                else out.print(num + "/" + den);
                if ((d[i] & 1) == 0) {
                    out.println(" clockwise");
                } else {
                    out.println(" counterclockwise");
                }
            }
        }
    }
}
