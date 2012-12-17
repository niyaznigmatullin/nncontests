package lib.test.on2012_12.on2012_12_17_.Runaway;



import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Runaway {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long len = in.nextInt();
        int[] parent = new int[n];
        parent[0] = -1;
        final long[] depth = new long[n];
        int[] size = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = in.nextInt() - 1;
            depth[i] = depth[parent[i]] + in.nextLong();
        }
        for (int i = n - 1; i >= 0; i--) {
            size[i]++;
            if (parent[i] >= 0) {
                size[parent[i]] += size[i];
            }
        }
        int[] first = new int[n];
        int[] index = new int[n];
        index[0] = 0;
        first[0] = 1;
        for (int i = 1; i < n; i++) {
            int v = first[parent[i]];
            first[i] = v + 1;
            index[i] = v;
            first[parent[i]] += size[i];
        }
        Integer[] v = new Integer[n];
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        Arrays.sort(v, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Long.signum(depth[o2] - depth[o1]);
            }
        });
        Fenwick f = new Fenwick(n);
        int cur = 0;
        int[] answer = new int[n];
        for (int i : v) {
            f.add(index[i], 1);
            while (cur < n && depth[v[cur]] > len + depth[i]) {
                f.add(index[v[cur]], -1);
                ++cur;
            }
            answer[i] = f.getSum(index[i], first[i]);
        }
        for (int i : answer) {
            out.println(i);
        }
    }
}
