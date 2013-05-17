package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {


    static final int MOD = 1000000007;
    static long[][] C;
    static {
        C = new long[55][55];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                if (C[i][j] >= MOD) {
                    C[i][j] -= MOD;
                }
            }
        }
    }
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        k /= 50;
        int cn1 = 0;
        int cn2 = 0;
        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 50) {
                ++cn1;
            } else {
                ++cn2;
            }
        }
        int[][] tries = new int[cn1 + 1][cn2 + 1];
        int[][] ways = new int[cn1 + 1][cn2 + 1];
        for (int[] d : tries) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i <= cn1; i++) {
            for (int j = 0; j <= cn2; j++) {
                if (i + j == 0) {
                    continue;
                }
                if (i + 2 * j <= k) {
                    tries[i][j] = 1;
                    ways[i][j] = (int) (C[cn1][i] * C[cn2][j] % MOD);
                }
            }
        }
        boolean changed = true;
        int q = 1;
        while (changed) {
            changed = false;
            for (int c1 = 0; c1 <= cn1; c1++) {
                for (int c2 = 0; c2 <= cn2; c2++) {
                    int ct = tries[c1][c2];
                    if (ct != q) {
                        continue;
                    }
                    long cw = ways[c1][c2];
                    for (int b1 = 0; b1 <= c1 && b1 <= c1; b1++) {
                        for (int b2 = 0; b2 <= c2 && b2 <= c2; b2++) {
                            if (b1 + b2 == 0 || b1 + b2 * 2 > k) {
                                continue;
                            }
                            long dw = (cw * C[c1][b1] % MOD * C[c2][b2] % MOD);
                            int left1 = cn1 - c1 + b1;
                            int left2 = cn2 - c2 + b2;
                            for (int i = 0; i <= left1; i++) {
                                for (int j = 0; j <= left2; j++) {
                                    if (i + j == 0) continue;
                                    if (i + 2 * j > k) {
                                        continue;
                                    }
                                    int d1 = c1 - b1 + i;
                                    int d2 = c2 - b2 + j;
                                    if (tries[d1][d2] > ct + 2) {
                                        tries[d1][d2] = ct + 2;
                                        ways[d1][d2] = (int) (dw * C[left1][i] % MOD * C[left2][j] % MOD);
                                        changed = true;
                                    } else if (tries[d1][d2] == ct + 2) {
                                        ways[d1][d2] = (int) ((ways[d1][d2] + dw * C[left1][i] % MOD * C[left2][j] % MOD) % MOD);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            q += 2;
        }
        if (tries[cn1][cn2] == Integer.MAX_VALUE) {
            out.println(-1);
            out.println(0);
            return;
        }
        out.println(tries[cn1][cn2]);
        out.println(ways[cn1][cn2]);
    }

}
