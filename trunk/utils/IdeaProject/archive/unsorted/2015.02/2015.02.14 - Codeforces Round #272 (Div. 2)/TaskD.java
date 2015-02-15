package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        out.println((n * 6 - 1) * k);
        for (int i = 0; i < n; i++) {
            int start = i * 6;
            out.println(k * (start + 1) + " " + k * (start + 2) + " " + k * (start + 3) + " " + k * (start + 5));
        }
    }
}
