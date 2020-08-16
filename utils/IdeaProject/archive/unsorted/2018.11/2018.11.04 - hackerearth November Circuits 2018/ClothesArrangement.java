package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.FenwickKth;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class ClothesArrangement {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n < 1 || n > 500000) throw new AssertionError();
        int[] a = in.readIntArray(n);
        int q = in.nextInt();
        if (q < 1 || q > 500000) throw new AssertionError();
        int[] type = new int[q];
        int[] colorQ = new int[q];
        int[] kQ = new int[q];
        final int MAXN = 1000001;
        int[] count = new int[MAXN];
        for (int i : a) count[i]++;
        for (int i : a) if (i < 1 || i > 500000) throw new AssertionError();
        for (int i = 0; i < q; i++) {
            type[i] = in.nextInt();
            if (type[i] != 1 && type[i] != 2) throw new AssertionError();
            colorQ[i] = in.nextInt();
            if (colorQ[i] < 1 || colorQ[i] > 500000) throw new AssertionError();
            if (type[i] == 2) {
                kQ[i] = in.nextInt();
                if (kQ[i] < 1 || kQ[i] > 1000000) throw new AssertionError();
            } else {
                count[colorQ[i]]++;
            }
        }
        int[][] numbers = new int[MAXN][];
        FenwickKth[] f = new FenwickKth[MAXN];
        Fenwick globalF = new Fenwick(n + q);
        for (int i = 0; i < MAXN; i++) {
            if (count[i] > 0) {
                numbers[i] = new int[count[i]];
                f[i] = new FenwickKth(count[i]);
                count[i] = 0;
            }
        }
        int pos = 0;
        for (int x : a) {
            numbers[x][count[x]] = pos;
            f[x].add(count[x], 1);
            globalF.add(pos, 1);
            pos++;
            count[x]++;
        }
        for (int curQ = 0; curQ < q; curQ++) {
            int x = colorQ[curQ];
            if (type[curQ] == 1) {
                numbers[x][count[x]] = pos;
                globalF.add(pos, 1);
                f[x].add(count[x], 1);
                count[x]++;
                pos++;
            } else {
                int k = kQ[curQ];
                int have = f[x] == null ? 0 : f[x].getSum(Integer.MAX_VALUE);
                if (have < k) {
                    out.println(-1);
                } else {
                    int localPos = f[x].getKth(have - k + 1);
                    f[x].add(localPos, -1);
                    int globalPos = numbers[x][localPos];
                    globalF.add(globalPos, -1);
                    out.println(globalF.getSum(globalPos, Integer.MAX_VALUE));
                }
            }
        }
    }
}
