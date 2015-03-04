package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int xor1 = 0;
        for (int i = 0; i < n; i++) xor1 ^= in.nextInt();
        int xor2 = 0;
        for (int i = 0; i + 1 < n; i++) xor2 ^= in.nextInt();
        int xor3 = 0;
        for (int i = 0; i + 2 < n; i++) xor3 ^= in.nextInt();
        out.println(xor1 ^ xor2);
        out.println(xor2 ^ xor3);
    }
}
