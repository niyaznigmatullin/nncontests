package lib.test.on2013_04.on2013_04_21_Gran_Prix_of_Taganrog.A;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class A {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int inv = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) ++inv;
            }
        }
        int all = n * (n - 1) / 2;
        if ((inv & 1) != (all & 1)) {
            out.println("First");
        } else {
            out.println("Second");
        }
    }
}
