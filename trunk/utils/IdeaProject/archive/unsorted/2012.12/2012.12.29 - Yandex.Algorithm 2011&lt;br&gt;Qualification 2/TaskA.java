package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() - 1;
        final String[] ANSWER = {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        for (int k = 1; ; k <<= 1) {
            if (n < 5 * k) {
                out.println(ANSWER[n / k]);
                return;
            } else {
                n -= 5 * k;
            }
        }
    }
}
