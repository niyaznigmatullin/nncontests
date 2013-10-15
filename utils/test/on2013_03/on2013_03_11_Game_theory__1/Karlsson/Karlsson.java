package lib.test.on2013_03.on2013_03_11_Game_theory__1.Karlsson;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Karlsson {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] a = in.readIntArray(3);
        int n = Math.max(a[2], Math.max(a[0], a[1])) + 1;
        int[] g = new int[n];
        int[] set = new int[n];
        g[1] = 0;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= i - j; j++) {
                set[g[i - j]] = i;
            }
            for (int j = 0; ; j++) {
                if (set[j] != i) {
                    g[i] = j;
                    break;
                }
            }
        }
        int xor = 0;
        for (int i : a) {
            xor ^= g[i];
        }
        if (xor == 0) {
            out.println("NO");
        } else {
            out.println("YES");
            all: for (int i = 0; i < 3; i++) {
                for (int j = 1; j <= a[i] - j; j++) {
                    if ((xor ^ g[a[i]]) == g[a[i] - j]) {
                        a[i] -= j;
                        break all;
                    }
                }
            }
            out.printArray(a);
        }
    }
}
