package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Comparator;
import java.util.PriorityQueue;

public class G {

    final int MAXN = 1234567;
    int[] depth = new int[MAXN];
    int[] left = new int[MAXN];
    int[] right = new int[MAXN];
    int[] parent = new int[MAXN];
    char[] c;
    int cur;
    int free;

    int pE() {
        int v = pF();
        while (cur < c.length && (c[cur] == '+' || c[cur] == '-')) {
            ++cur;
            int u = free++;
            left[u] = v;
            right[u] = pF();
            v = u;
        }
        return v;
    }

    int pF() {
        int v = pV();
        while (cur < c.length && c[cur] == '*') {
            ++cur;
            int u = free++;
            left[u] = v;
            right[u] = pV();
            v = u;
        }
        return v;
    }

    int pV() {
        if (c[cur] == '(') {
            ++cur;
            int v = pE();
            ++cur;
            return v;
        }
        if (c[cur] == '-' || (c[cur] >= '0' && c[cur] <= '9')) {
            if (c[cur] == '-') ++cur;
            while (cur < c.length && (c[cur] >= '0' && c[cur] <= '9')) {
                ++cur;
            }
            int v = free++;
            left[v] = -1;
            right[v] = -1;
            return v;
        }
        if (c[cur] >= 'a' && c[cur] <= 'z') {
            while (cur < c.length && (c[cur] >= 'a' && c[cur] <= 'z')) {
                ++cur;
            }
            int v = free++;
            left[v] = -1;
            right[v] = -1;
            return v;
        }
        throw new AssertionError();
    }

    void dfs(int v, int d, int p) {
        depth[v] = d;
        parent[v] = p;
        if (left[v] < 0) return;
        dfs(left[v], d + 1, v);
        dfs(right[v], d + 1, v);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        c = in.next().toCharArray();
        free = 0;
        cur = 0;
        int root = pE();
        dfs(root, 0, -1);
        PriorityQueue<Integer> q = new PriorityQueue<>(MAXN, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(depth[o2], depth[o1]);
            }
        });
        boolean[] added = new boolean[free];
        for (int i = 0; i < free; i++) {
            if (parent[i] < 0 || added[parent[i]]) continue;
            if (left[i] < 0) {
                int other = left[parent[i]] ^ right[parent[i]] ^ i;
                if (left[other] < 0) {
                    added[parent[i]] = true;
                    q.add(parent[i]);
                }
            }
        }
        int ans = 0;
        int[] z = new int[k];
        while (!q.isEmpty()) {
            int ac = 0;
            for (int i = 0; !q.isEmpty() && i < k; i++) {
                z[ac++] = q.poll();
            }
            for (int i = 0; i < ac; i++) {
                left[z[i]] = -1;
                right[z[i]] = -1;
                int p = parent[z[i]];
                if (p < 0) continue;
                int other = left[p] ^ right[p] ^ z[i];
                if (left[other] < 0) {
                    added[p] = true;
                    q.add(p);
                }
            }
            ++ans;
        }
        out.println(ans);
    }
}
