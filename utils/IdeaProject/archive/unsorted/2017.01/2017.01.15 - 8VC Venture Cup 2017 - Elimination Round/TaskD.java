package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static int get(Fenwick f, int from, int to, int n) {
        int ret = 0;
        if (from <= to) {
            ret += f.getSum(from, to + 1);
        } else {
            ret += f.getSum(from, n) + f.getSum(0, to + 1);
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Fenwick fout = new Fenwick(n);
        Fenwick fin = new Fenwick(n);
        int k = in.nextInt();
        int cur = 0;
        long ans = 1;
        if (k > n - k) k = n - k;
        int left = Math.max(1, 2 * k - n + 1);
        int right = Math.min(k - 1, n - k - 1);
        if (left > right) throw new AssertionError();
        for (int i = 0; i < n; i++) {
            int have = 0;
            have += get(fout, (cur + 1) % n, (cur + right) % n, n);
            have += get(fin, (cur + left) % n, (cur + k - 1) % n, n);
            ans += have + 1;
            fout.add(cur, 1);
            cur = (cur + k) % n;
            fin.add(cur, 1);
            if (i > 0) out.print(' ');
            out.print(ans);
        }
        out.println();
    }
}
