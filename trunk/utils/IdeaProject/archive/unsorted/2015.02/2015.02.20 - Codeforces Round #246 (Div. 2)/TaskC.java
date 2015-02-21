package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskC {
    static void swap(int i, int j, int[] a, int[] pos) {
        if (i == j) return;
        if (i > j) {
            int t = i;
            i = j;
            j = t;
        }
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        pos[a[i]] = i;
        pos[a[j]] = j;
        ax[ac] = i;
        ay[ac++] = j;
    }

    static int ac;
    static int[] ax;
    static int[] ay;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        ac = 0;
        int[] primes = MathUtils.genPrimes(123456);
        boolean[] isPrime = MathUtils.genPrimesBoolean(123456);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] pos = new int[n];
        ax = new int[5 * n];
        ay = new int[5 * n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
            pos[a[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            int where = pos[i];
            int dif = where - i;
            if (dif == 0) {
                continue;
            }
            if (dif == 1) {
                swap(i, i + 1, a, pos);
                continue;
            }
            if ((dif & 1) == 1) {
                dif--;
                int found = findSwap(primes, isPrime, dif);
                int second = dif - (found - 1) + 1;
                swap(where, where - found + 1, a, pos);
                swap(i + 1 + second - 1, i + 1, a, pos);
                swap(i, i + 1, a, pos);
            } else {
                int found = findSwap(primes, isPrime, dif);
                int second = dif - (found - 1) + 1;
                swap(where, where - found + 1, a, pos);
                swap(i + second - 1, i, a, pos);
            }
        }
        for (int i = 0; i < n; i++) if (a[i] != i) throw new AssertionError();
        out.println(ac);
        for (int i = 0; i < ac; i++) {
            out.println((ax[i] + 1) + " " + (ay[i] + 1));
        }
    }

    private int findSwap(int[] primes, boolean[] isPrime, int dif) {
        int found = -1;
        for (int j : primes) {
            if (isPrime[dif - (j - 1) + 1]) {
                found = j;
                break;
            }
        }
        if (found < 0) throw new AssertionError();
        return found;
    }

}
