package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] g = new int[n + 1];
        int[] g2 = new int[n + 1];
        g[1] = 0;
        int[] v = new int[n + 1];
        int[] move = new int[n + 1];
        Arrays.fill(move, -1);
        for (int i = 2; i <= n; i++) {
            for (int z = 2; z * (z + 1) / 2 <= i; z++) {
                if ((z & 1) == 1) {
                    if (i % z == 0) {
                        int q = g2[i / z + z / 2] ^ g2[i / z - z / 2 - 1];
                        v[q] = i;
                        if (q == 0 && move[i] < 0) {
                            move[i] = z;
                        }
                    }
                } else {
                    if (i % z == z / 2) {
                        int q = g2[i / z + z / 2] ^ g2[i / z - z / 2];
                        v[q] = i;
                        if (q == 0 && move[i] < 0) {
                            move[i] = z;
                        }
                    }
                }
            }
            for (int k = 0; ; k++) {
                if (v[k] != i) {
                    g[i] = k;
                    break;
                }
            }
            g2[i] = g2[i - 1] ^ g[i];
        }
        if (g[n] == 0) {
            out.println(-1);
            return;
        }
        out.println(move[n]);
    }
}
