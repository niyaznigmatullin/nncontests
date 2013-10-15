package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class Stable {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n1 = in.nextInt();
        int[] p1 = in.readIntArray(n1 + 1);
        int n2 = in.nextInt();
        int[] p2 = in.readIntArray(n2 + 1);
        boolean[] ok1 = cycleDo(p1, m);
        boolean[] ok2 = cycleDo(p2, m);
//        System.out.println(Arrays.toString(ok1));
//        System.out.println(Arrays.toString(ok2));
        Set<List<Integer>> set = new HashSet<>();
        all:
        for (int i = 0; i < m; i++) {
            if (!ok1[i] || !ok2[i]) continue;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            boolean[] was = new boolean[m];
            was[i] = true;
            List<Integer> list = new ArrayList<>();
            while (!q.isEmpty()) {
                int v = q.poll();
                list.add(v);
                int u1 = calc(p1, v, m);
                if (!ok1[u1] || !ok2[u1]) {
                    continue all;
                }
                if (!was[u1]) {
                    q.add(u1);
                    was[u1] = true;
                }
                int u2 = calc(p2, v, m);
                if (!ok2[u2] || !ok1[u2]) {
                    continue all;
                }
                if (!was[u2]) {
                    q.add(u2);
                    was[u2] = true;
                }
            }
            Collections.sort(list);
            set.add(list);
        }
//        System.out.println(set);
        out.println(BigInteger.ONE.shiftLeft(set.size()));
    }

    static boolean[] cycleDo(int[] p, int m) {
        boolean[] ret = new boolean[m];
        boolean[] was = new boolean[m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(was, false);
            int x = i;
            while (!was[x]) {
                was[x] = true;
                x = calc(p, x, m);
            }
            if (x == i) ret[i] = true;
        }
        return ret;
    }

    static int calc(int[] p, int x, int m) {
        int ret = 0;
        for (int i = 0; i < p.length; i++) {
            ret = ret * x + p[i];
            ret %= m;
        }
        return ret;
    }
}
