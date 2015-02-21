package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskF {
    static List<Integer>[] edges;
    static int[] val;
    static int[] vs;
    static int[] es;
    static int cn;
    static short[][] pr;
    static int T;
    static int[] en;
    static int[] ex;

    static void dfs(int v, int p) {
        en[v] = T++;
        for (int i = 0; i < cn; i++) {
            pr[vs[i]][v] = (short) es[i];
        }
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (to == p) continue;
            vs[cn] = v;
            es[cn++] = i;
            dfs(to, v);
            --cn;
        }
        ex[v] = T;
    }

    static int getEdge(int from, int to) {
        if (en[from] <= en[to] && ex[to] <= ex[from]) {
            return pr[from][to];
        } else {
            return -1;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        en = new int[n];
        ex = new int[n];
        vs = new int[n];
        es = new int[n];
        cn = 0;
        T = 0;
        val = in.readIntArray(n);
        pr = new short[n][n];
        for (short[] e : pr) Arrays.fill(e, (short) -1);
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        dfs(0, -1);
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(val[o1], val[o2]);
            }
        });
        int[] m1 = new int[n];
        int[] e1 = new int[n];
        int[] m2 = new int[n];
        Arrays.fill(m1, 1);
        Arrays.fill(m2, Integer.MAX_VALUE);
        Arrays.fill(e1, -2);
        for (int it = 0; it < n; it++) {
            int i = id[it];
            for (int jt = 0; jt < it; jt++) {
                int j = id[jt];
                if (val[i] <= val[j]) break;
                int curmax;
                int ce = getEdge(j, i);
                if (m1[j] == 1 || ce != e1[j]) {
                    curmax = m1[j];
                } else {
                    curmax = m2[j];
                }
                if (curmax == Integer.MAX_VALUE) continue;
                ++curmax;
                int curedge = getEdge(i, j);
                if (m1[i] < curmax) {
                    if (e1[i] != curedge) {
                        m2[i] = m1[i];
                    }
                    m1[i] = curmax;
                    e1[i] = curedge;
                } else if (curmax > m2[i] && e1[i] != curedge) {
                    m2[i] = curmax;
                }
            }
//            System.out.println(Arrays.toString(m1));
//            System.out.println(Arrays.toString(e1));
//            System.out.println(Arrays.toString(m2));
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(i == j ? -2 : getEdge(i, j));
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//        System.out.println(Arrays.toString(m1));
//        System.out.println(Arrays.toString(e1));
//        System.out.println(Arrays.toString(m2));
        int ans = 0;
        for (int i = 0; i < n; i++) ans = Math.max(ans, m1[i]);
        out.println(ans);
    }
}
