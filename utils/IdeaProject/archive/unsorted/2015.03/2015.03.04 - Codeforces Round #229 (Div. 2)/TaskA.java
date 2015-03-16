package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.BitSet;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        BitSet a = new BitSet();
        BitSet b = new BitSet();
        for (int i = 0; i < n; i++) {
            a.set(in.nextInt());
            b.set(in.nextInt());
        }
        out.println(Math.min(a.cardinality(), b.cardinality()));
    }
}
