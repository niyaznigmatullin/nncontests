package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextInt();
        boolean isPrime = isPrime(n);
        if (isPrime) {
            out.println(1);
            return;
        }
        if ((n & 1) == 0) {
            out.println(2);
            return;
        }
        if (isPrime(n - 2)) {
            out.println(2);
            return;
        }
        out.println(3);
    }

    private boolean isPrime(long n) {
        boolean isPrime = true;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
