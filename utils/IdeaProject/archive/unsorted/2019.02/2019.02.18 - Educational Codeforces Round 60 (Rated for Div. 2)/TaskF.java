package coding;

import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.List;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        char[] c = in.next().toCharArray();
        boolean[][][] can = new boolean[p][p][1 << p];
        for (boolean[][] e : can) for (boolean[] f : e) Arrays.fill(f, true);
        boolean[][] neigh = new boolean[p][p];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                neigh[i][j] = in.nextInt() == 1;
            }
        }
        int[] a = new int[p];
        int sz = 0;
        int[] has = new int[p];
        for (char e : c) has[e - 'a']++;
        int[] hasInMask = new int[1 << p];
        hasInMask[0] = 0;
        for (int i = 1; i < 1 << p; i++) {
            hasInMask[i] = hasInMask[i & (i - 1)] + has[Integer.numberOfTrailingZeros(i)];
        }
        for (int i = n - 1; i >= 0; i--) {
            int cur = c[i] - 'a';
            int mask = 0;
            for (int j = 0; j < sz; j++) {
                if (!neigh[cur][a[j]]) {
                    can[cur][a[j]][mask] = false;
                }
                mask |= 1 << a[j];
                if (a[j] == cur) break;
            }
            int where = -1;
            for (int j = 0; j < sz; j++) {
                if (a[j] == cur) {
                    where = j;
                    break;
                }
            }
            if (where < 0) where = sz++;
            while (where > 0) {
                a[where] = a[where - 1];
                --where;
            }
            a[where] = cur;
        }
        boolean[] good = new boolean[1 << p];
        Arrays.fill(good, true);
        for (int e = 0; e < p; e++) {
            for (int f = 0; f < p; f++) {
                for (int mask = 0; mask < 1 << p; mask++) {
                    if (((mask >> e) & 1) == 1 || ((mask >> f) & 1) == 1) continue;
                    if (can[e][f][mask]) continue;
                    good[mask] = false;
                    for (int i = 0; i < p; i++) {
                        if (i == e || i == f) continue;
                        if (((mask >> i) & 1) == 0) {
                            can[e][f][mask | (1 << i)] = false;
                        }
                    }
                }
            }
        }
        boolean[] canGetTo = new boolean[1 << p];
        canGetTo[0] = true;
        int ans = n;
        for (int i = 0; i < 1 << p; i++) {
            if (!canGetTo[i]) continue;
            ans = Math.min(ans, hasInMask[((1 << p) - 1) & ~i]);
            for (int j = 0; j < p; j++) {
                int ni = i | (1 << j);
                if (i != ni && good[ni]) {
                    canGetTo[ni] = true;
                }
            }
        }
        out.println(ans);
    }
}
