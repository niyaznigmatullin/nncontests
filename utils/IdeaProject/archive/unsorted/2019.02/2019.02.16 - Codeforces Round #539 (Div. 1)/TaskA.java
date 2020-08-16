package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] have = new int[2][1 << 20];
        have[1][0] = 1;
        long ans = 0;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            xor ^= x;
            ans += have[i & 1][xor];
            have[i & 1][xor]++;
        }
        out.println(ans);
    }
}
