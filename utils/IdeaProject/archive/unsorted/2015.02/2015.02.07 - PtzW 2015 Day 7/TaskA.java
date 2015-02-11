package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskA {
    static final int MOD = 120586241;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int modInverse(int a) {
        return MathUtils.modPow(a, MOD - 2, MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int b = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
        }
//        if (2 * b == n) {
//            if (a[0] < a[b]) {
//                out.println(1);
//            } else {
//                out.println(2);
//            }
//            return;
//        }
        List<Integer>[] mods = new List[b];
        for (int i = 0; i < b; i++) mods[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mods[i % b].add(a[i]);
        }
        for (List<Integer> e : mods) {
            Collections.sort(e);
        }
        Fenwick[] f = new Fenwick[b];
        int[] count = new int[b];
        for (int i = 0; i < b; i++) {
            f[i] = new Fenwick(mods[i].size());
            count[i] = mods[i].size();
            for (int j = 0; j < count[i]; j++) f[i].add(j, 1);
        }
//        int[] fact = new int[n + 1];
//        fact[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            fact[i] = mul(fact[i - 1], i);
//        }
//        int curFact = 1;
//        for (int i = 0; i < b; i++) {
//            curFact = mul(curFact, fact[count[i]]);
//        }
        int[] ways = new int[n + 1];
        for (int i = 0; i < 2 * b; i++) ways[i] = 1;
        for (int i = 2 * b; i <= n; i++) {
            ways[i] = mul(ways[i - 1], (i + b - 1) / b);
        }
        int ans = 0;
        for (int i = 0; i + 2 * b <= n; i++) {
            int block = i % b;
//            curFact = mul(curFact, modInverse(fact[count[block]]));
//            --count[block];
//            curFact = mul(curFact, fact[count[block]]);
            int pos = Collections.binarySearch(mods[block], a[i]);
            f[block].add(pos, -1);
            int cur = f[block].getSum(pos);
//            ans = (ans + mul(curFact, cur)) % MOD;
            ans = (ans + mul(ways[n - i  - 1], cur)) % MOD;
        }
        out.println((ans + 1) % MOD);
    }
}
