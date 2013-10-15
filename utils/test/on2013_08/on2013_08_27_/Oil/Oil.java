package lib.test.on2013_08.on2013_08_27_.Oil;



import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Oil {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long s = in.nextLong();
        int[] from = new int[m];
        int[] to = new int[m];
        final int[] cost = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            cost[i] = in.nextInt();
        }
        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) {
            order[i] = i;
        }
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(cost[o1], cost[o2]);
            }
        });
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        boolean[] taken = new boolean[m];
        for (int it = 0; it < m; it++) {
            int i = order[it];
            if (dsu.get(from[i]) != dsu.get(to[i])) {
                dsu.union(from[i], to[i]);
                taken[i] = true;
            }
        }
        List<Integer> answer = new ArrayList<>();
        for (int it = m - 1; it >= 0; it--) {
            int i = order[it];
            if (taken[i]) continue;
            if (cost[i] <= s) {
                s -= cost[i];
                answer.add(i + 1);
            }
        }
        Collections.sort(answer);
        out.println(answer.size());
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(answer));
    }
}
