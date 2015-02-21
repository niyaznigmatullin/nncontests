package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt() + in.nextInt() + in.nextInt();
        int b = in.nextInt() + in.nextInt() + in.nextInt();
        int n = in.nextInt();
        if ((a + 4) / 5 + (b + 9) / 10 > n) {
            out.println("NO");
        } else {
            out.println("YES");
        }
    }
}
