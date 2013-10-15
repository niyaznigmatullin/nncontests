package lib.test.on2013_02.on2013_02_02_Facebook_Hacker_Cup_2013_Round_1.Security;



import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Security {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.println("Case #" + testNumber + ": ");
        int m = in.nextInt();
        String s = in.next();
        String t = in.next();

        int k = s.length() / m;
        boolean[][] edges = new boolean[m][m];
        for (int i0 = 0; i0 < m; i0++) {
            for (int j0 = 0; j0 < m; j0++) {
                int i = i0 * k;
                int j = j0 * k;
                boolean ok = true;
                for (int l = 0; l < k; l++) {
                    if (s.charAt(i + l) != '?' && t.charAt(j + l) != '?' && s.charAt(i + l) != t.charAt(j + l)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    edges[i0][j0] = true;
                }
            }
        }
        KuhnMatchingGraph g = new KuhnMatchingGraph(m, m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (edges[i][j]) {
                    g.addEdge(i, j);
                }
            }
        }
        if (g.getMaximalMatching() != m) {
            out.println("IMPOSSIBLE");
            return;
        }
        for (int i = 0; i < m; i++) {
            final String[] d = new String[m];
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (edges[i][j]) {
                    cnt++;
                }
            }
            Integer[] id = new Integer[cnt];
            cnt = 0;
            for (int j = 0; j < m; j++) {
                if (!edges[i][j]) {
                    continue;
                }
                id[cnt++] = j;
                char[] q = new char[k];
                for (int l = 0; l < k; l++) {
                    char c1 = s.charAt(i * k + l);
                    char c2 = t.charAt(j * k + l);
                    if (c1 == '?' && c2 == '?') {
                        q[l] = 'a';
                    } else {
                        q[l] = c1;
                    }
                }
                d[j] = new String(q);
            }
            Arrays.sort(id, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return d[o1].compareTo(d[o2]);
                }
            });
            g.getEdges()[i].clear();
            for (int j = 0; j < cnt; ) {
                int e = j;
                while (e < cnt && d[id[j]].equals(d[id[e]])) {
                    ++e;
                }
                for (int z = j; z < e; z++) {
                    g.addEdge(i, id[j]);
                }
                if (g.getMaximalMatching() == m) {
                    break;
                }
                j = e;
            }
            if (g.getMaximalMatching() != m) {
                throw new AssertionError();
            }
        }
        g.getMaximalMatching();
        int[] p1 = g.getPaired1();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                char c1 = s.charAt(i * k + j);
                char c2 = t.charAt(p1[i] * k + j);
                if (c1 == '?' && c2 == '?') {
                    sb.append('a');
                } else {
                    if (c1 != c2) throw new AssertionError();
                    sb.append(c1);
                }
            }
        }
        out.println(sb);
    }
}
