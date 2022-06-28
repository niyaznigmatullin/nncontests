package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class ChainExplosions {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int k = in.nextInt();
        int free = 1;
        int cur = 0;
        List<int[]> edges = new ArrayList<>();
        while (k > 0) {
            int vs = 0;
            while ((vs + 1) * vs <= k) {
                ++vs;
            }
            k -= vs * (vs - 1);
            int need = vs - 1;
            if (cur == 0) ++need;
            for (int i = 0; i < need; i++) {
                edges.add(new int[]{cur, free++});
            }
            cur++;
        }
        out.println("Case #" + testNumber + ": " + free + " " + edges.size());
        for (int[] e : edges) {
            out.println((e[0] + 1) + " " + (e[1] + 1));
        }
        if (free > 2000 || edges.size() > 2000) throw new AssertionError();
    }
}
