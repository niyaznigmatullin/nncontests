package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class SubstringsOnATree {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[] s = in.next().toCharArray();
        int edcnt = 0;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] next = new int[2 * n];
        int[] v = new int[2 * n];
        for (int i = 0; i + 1 < n; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            edcnt = addEdge(head, next, v, x, y, edcnt);
            edcnt = addEdge(head, next, v, y, x, edcnt);
        }
        SuffixAutomaton sa = new SuffixAutomaton(n + 1);
        int qh = 0;
        int qt = 0;
        int[] queue = new int[n];
        int[] state = new int[n];
        boolean[] was = new boolean[n];
        queue[qt++] = 0;
        state[0] = sa.append(s[0] - 'a', 0);
        was[0] = true;
        while (qh < qt) {
            int u = queue[qh++];
            for (int e = head[u]; e >= 0; e = next[e]) {
                int go = v[e];
                if (was[go]) {
                    continue;
                }
                was[go] = true;
                state[go] = sa.append(s[go] - 'a', state[u]);
                queue[qt++] = go;
            }
        }
        sa.countDP();
        long all = sa.dp[0];
        out.println(all);
        for (int q = 0; q < m; q++) {
            char[] p = in.next().toCharArray();
            long k = in.nextLong() - 1;
            if (k >= all) {
                out.println(-1);
                continue;
            }
            StringBuilder ans = new StringBuilder();
            int u = 0;
            while (true) {
                if (k == 0) {
                    out.println(ans);
                    break;
                }
                --k;
                for (int i = 0; i < ALPHABET; i++) {
                    int go = sa.link[p[i] - 'a'][u];
                    if (go < 0) {
                        continue;
                    }
                    long got = sa.dp[go];
                    if (k < got) {
                        u = go;
                        ans.append(p[i]);
                        break;
                    } else {
                        k -= got;
                    }
                }
            }
        }
    }

    static final int ALPHABET = 26;

    class SuffixAutomaton {
        int[][] link;
        int[] sufLink;
        int[] length;
        int free;
        long[] dp;

        SuffixAutomaton(int allLen) {
            allLen = 2 * allLen + 1;
            link = new int[ALPHABET][allLen];
            for (int i = 0; i < ALPHABET; i++) {
                Arrays.fill(link[i], -1);
            }
            sufLink = new int[allLen];
            Arrays.fill(sufLink, -1);
            length = new int[allLen];
            length[0] = 0;
            free = 1;
        }


        int getNewNode(int len) {
            length[free] = len;
            for (int i = 0; i < ALPHABET; i++) {
                link[i][free] = -1;
            }
            sufLink[free] = -1;
            return free++;
        }

        int getCopy(int copyFrom, int len) {
            length[free] = len;
            for (int i = 0; i < ALPHABET; i++) {
                link[i][free] = link[i][copyFrom];
            }
            sufLink[free] = sufLink[copyFrom];
            return free++;
        }

        int append(int c, int last) {
            if (link[c][last] >= 0) {
                return link[c][last];
            }
            int newNode = getNewNode(length[last] + 1);
            for (; last >= 0 && link[c][last] < 0; last = sufLink[last]) {
                link[c][last] = newNode;
            }
            if (last < 0) {
                sufLink[newNode] = 0;
                return newNode;
            }
            int q = link[c][last];
            if (length[q] == length[last] + 1) {
                sufLink[newNode] = q;
                return newNode;
            }
            int copy = getCopy(q, length[last] + 1);
            for (; last >= 0 && link[c][last] == q; last = sufLink[last]) {
                link[c][last] = copy;
            }
            sufLink[newNode] = sufLink[q] = copy;
            return newNode;
        }

        void countDP() {
            int[] z = new int[free];
            for (int i = 0; i < free; i++) {
                z[i] = i;
            }
            int[] count = new int[free + 1];
            for (int i = 0; i < free; i++) {
                count[length[i]]++;
            }
            for (int i = 1; i <= free; i++) {
                count[i] += count[i - 1];
            }
            for (int i = 0; i < free; i++) {
                z[--count[length[i]]] = i;
            }
            dp = new long[free];
            for (int e = free - 1; e >= 0; e--) {
                int i = z[e];
                dp[i] = 1;
                for (int j = 0; j < ALPHABET; j++) {
                    int go = link[j][i];
                    if (go < 0) {
                        continue;
                    }
                    dp[i] += dp[go];
                }
            }
        }

    }

    int addEdge(int[] head, int[] next, int[] v, int x, int y, int edcnt) {
        next[edcnt] = head[x];
        head[x] = edcnt;
        v[edcnt] = y;
        return ++edcnt;
    }
}
