package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskF {

    static int[] readString(String s) {
        int[] ret = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ret[i] = s.charAt(i) - 'a';
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] a = readString(in.next());
        int[] b = readString(in.next());
        ArrayUtils.reverse(b);
        SuffixAutomaton sa = new SuffixAutomaton(new int[][]{a}, 26);
        SuffixAutomaton sb = new SuffixAutomaton(new int[][]{b}, 26);
        int[] ways1 = new int[26];
        for (int i = 0; i < sa.free; i++) {
            for (int j = 0; j < sa.link.length; j++) {
                if (sa.link[j][i] < 0) {
                    ways1[j] += sa.ways[i];
                    if (ways1[j] >= MOD) ways1[j] -= MOD;
                }
            }
        }
        int[] ways2 = new int[26];
        for (int i = 1; i < sb.free; i++) {
            int f = sb.last[i];
            ways2[f] += sb.ways[i];
            if (ways2[f] >= MOD) ways2[f] -= MOD;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += (int) ((long) ways1[i] * ways2[i] % MOD);
            if (ans >= MOD) ans -= MOD;
        }
        for (int i = 0; i < sa.free; i++) {
            ans += sa.ways[i];
            if (ans >= MOD) ans -= MOD;
        }
        out.println(ans);
    }

    static final int MOD = 998244353;

    static class SuffixAutomaton {
        public int[][] link;
        public int[] sufLink;
        public int[] length;
        public int free;
        public int[] ways;
        public int[] last;

        public SuffixAutomaton(int[][] a, int alphabet) {
            int all = 1;
            for (int[] i : a) {
                all += i.length;
            }
            all = all * 2 + 1;
            link = new int[alphabet][all];
            sufLink = new int[all];
            length = new int[all];
            for (int[] d : link) {
                Arrays.fill(d, -1);
            }
            Arrays.fill(sufLink, -1);
            free = 0;
            int root = newNode(0);
            for (int[] d : a) {
                int v = root;
                for (int i : d) {
                    v = append(v, i);
                }
            }
            last = new int[free];
            List<Integer>[] edges = new List[free];
            for (int i = 0; i < free; i++) edges[i] = new ArrayList<>();
            for (int i = 0; i < free; i++) {
                for (int j = 0; j < link.length; j++) {
                    if (link[j][i] >= 0) {
                        edges[i].add(link[j][i]);
                        last[link[j][i]] = j;
                    }
                }
            }
            int[] q = new int[free];
            int head = 0;
            int tail = 0;
            int[] deg = new int[free];
            for (int i = 0; i < free; i++) {
                for (int j : edges[i]) {
                    deg[j]++;
                }
            }
            for (int i = 0; i < free; i++) if (deg[i] == 0) q[tail++] = i;
            ways = new int[free];
            while (head < tail) {
                int v = q[head++];
                if (v == 0) ways[v] = 1;
                for (int to : edges[v]) {
                    ways[to] += ways[v];
                    if (ways[to] >= MOD) ways[to] -= MOD;
                    --deg[to];
                    if (deg[to] == 0) {
                        q[tail++] = to;
                    }
                }
            }
        }

//        void dfs(int v) {
//            if (ways[v] >= 0) return;
//            ways[v] = 0;
//            if (isTerminal[v]) ways[v] = 1;
//            for (int i = 0; i < link.length; i++) {
//                int to = link[i][v];
//                if (to >= 0) {
//                    dfs(to);
//                    ways[v] += ways[to];
//                }
//            }
//        }

        int newNode(int len) {
            length[free] = len;
            return free++;
        }

        int copyNode(int v, int len) {
            length[free] = len;
            for (int i = 0; i < link.length; i++) {
                link[i][free] = link[i][v];
            }
            sufLink[free] = sufLink[v];
            return free++;
        }

        int append(int v, int c) {
            if (link[c][v] >= 0) {
                int q = link[c][v];
                if (length[q] == length[v] + 1) {
                    return q;
                }
                int copy = copyNode(q, length[v] + 1);
                while (v >= 0 && link[c][v] == q) {
                    link[c][v] = copy;
                    v = sufLink[v];
                }
                sufLink[q] = copy;
                return copy;
            }
            int u = newNode(length[v] + 1);
            while (v >= 0 && link[c][v] < 0) {
                link[c][v] = u;
                v = sufLink[v];
            }
            if (v < 0) {
                sufLink[u] = 0;
                return u;
            }
            int q = link[c][v];
            if (length[q] == length[v] + 1) {
                sufLink[u] = q;
                return u;
            }
            int copy = copyNode(q, length[v] + 1);
            while (v >= 0 && link[c][v] == q) {
                link[c][v] = copy;
                v = sufLink[v];
            }
            sufLink[q] = sufLink[u] = copy;
            return u;
        }
    }

}
