package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Iterative {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        ss = new int[m * 2];
        ff = new int[m * 2];
        he = new int[n];
        Arrays.fill(he, -1);
        ne = new int[m * 2];
        for (int i = 0; i < m; i++) {
            ss[i] = in.nextInt() - 1;
            ff[i] = in.nextInt() - 1;
            ss[i + m] = ff[i];
            ff[i + m] = ss[i];
        }
        for (int i = 0; i < m + m; i++) {
            ne[i] = he[ss[i]];
            he[ss[i]] = i;
        }
        q = new int[n];
        d = new int[n];
        int ans = 0;
        int ansI = -1;
        for (int i = 0; i < n; i++) {
            int got = bfs(i);
            if (got > ans) {
                ans = got;
                ansI = i;
            }
        }
        bfs(ansI);
        out.println(ans + 1);
        for (int i = 0; i < n; i++) {
            out.print(q[i] + 1 + " ");
        }
        out.println();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[q[i]] = i;
        }
        boolean[] printed = new boolean[m];
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int e = he[q[i]]; e >= 0; e = ne[e]) {
                int id = e >= m ? e - m : e;
                if (printed[id]) {
                    continue;
                }
                answer.add(id + 1);
                printed[id] = true;
            }
        }
        Collections.reverse(answer);
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(answer));
        out.println();
    }

    int[] q, d;
    int[] ss, ff, he, ne;

    int bfs(int start) {
        int head = 0;
        int tail = 0;
        q[tail++] = start;
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;
        int ans = 0;
        while (head < tail) {
            int v = q[head++];
            ans = Math.max(ans, d[v]);
            for (int e = he[v]; e >= 0; e = ne[e]) {
                if (d[ff[e]] == Integer.MAX_VALUE) {
                    d[ff[e]] = d[v] + 1;
                    q[tail++] = ff[e];
                }
            }
        }
        for (int i = 0; i < he.length; i++) {
            if (d[i] == Integer.MAX_VALUE) {
                q[tail++] = i;
            }
        }
        return ans;
    }
}
