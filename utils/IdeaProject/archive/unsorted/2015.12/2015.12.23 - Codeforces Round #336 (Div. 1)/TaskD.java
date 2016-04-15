package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD {

    static final int MOD = 1000000007;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static class SegmentTree {
        private int[] sum;
        private int[] add;
        final int n;

        public SegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            sum = new int[this.n << 1];
            add = new int[this.n << 1];
            Arrays.fill(add, 1);
        }

        private void add(int node, int x) {
            add[node] = mul(add[node], x);
        }

        private void relax(int node) {
            if (add[node] == 1) {
                return;
            }
            sum[node] = getSum(node);
            add((node << 1) | 1, add[node]);
            add((node << 1) + 2, add[node]);
            add[node] = 1;
        }

        private int getSum(int node) {
            return mul(sum[node], add[node]);
        }

        private void add(int node, int l, int r, int left, int right, int x) {
            if (right <= l || r <= left) {
                return;
            }
            if (left <= l && r <= right) {
                add(node, x);
                return;
            }
            relax(node);
            int m = (l + r) >> 1;
            add((node << 1) | 1, l, m, left, right, x);
            add((node << 1) + 2, m, r, left, right, x);
            sum[node] = (getSum((node << 1) | 1) + getSum((node << 1) + 2)) % MOD;
        }

        private void change(int node, int l, int r, int id, int x) {
            if (l == r - 1) {
                sum[node] = x;
                add[node] = 1;
                return;
            }
            relax(node);
            int m = (l + r) >> 1;
            if (id < m)
                change((node << 1) | 1, l, m, id, x);
            else
                change((node << 1) + 2, m, r, id, x);
            sum[node] = (getSum((node << 1) | 1) + getSum((node << 1) + 2)) % MOD;
        }


        private int getSum(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                return 0;
            }
            if (left <= l && r <= right) {
                return getSum(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            return (getSum((node << 1) | 1, l, m, left, right)
                    + getSum((node << 1) + 2, m, r, left, right)) % MOD;
        }

        public void add(int l, int r, int x) {
            if (l >= r) {
                return;
            }
            add(0, 0, n, l, r, x);
        }

        public void change(int i, int x) {
            change(0, 0, n, i, x);
        }

        public int getSum(int l, int r) {
            if (l >= r) {
                return 0;
            }
            return getSum(0, 0, n, l, r);
        }

    }

    static List<Integer>[] edges;
    static int T;
    static int[] en, ex;
    static int[] children;

    static void dfs(int v) {
        en[v] = T++;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            dfs(to);
        }
        ex[v] = T;
    }

    static int modInverse(int a) {
        return MathUtils.modPow(a, MOD - 2, MOD);
    }

    static int[] parent;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int v1 = in.nextInt();
        int m = in.nextInt();
        int[] op = new int[m];
        int[] vq = new int[m];
        int[] valQ = new int[m];
        int[] newV = new int[m];
        int n = 1;
        for (int i = 0; i < m; i++) {
            op[i] = in.nextInt();
            vq[i] = in.nextInt() - 1;
            if (op[i] == 1) {
                valQ[i] = in.nextInt();
                newV[i] = n++;
            }
        }
        en = new int[n];
        ex = new int[n];
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] val = new int[n];
        val[0] = v1;
        parent = new int[n];
        parent[0] = -1;
        for (int i = 0; i < m; i++) {
            if (op[i] == 1) {
                val[newV[i]] = valQ[i];
                edges[vq[i]].add(newV[i]);
                parent[newV[i]] = vq[i];
            }
        }
        T = 0;
        dfs(0);
        SegmentTree tree = new SegmentTree(n);
        SegmentTree tree2 = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            tree2.change(i, 1);
        }
        tree.change(en[0], val[0]);
        children = new int[n];
        Arrays.fill(children, 1);
        for (int i = 0; i < m; i++) {
            if (op[i] == 1) {
                int parent = vq[i];
                int q = modInverse(children[parent]);
                children[parent]++;
                q = mul(q, children[parent]);
                tree.add(en[parent], ex[parent], q);
                tree2.add(en[parent], ex[parent], q);
                tree.change(en[newV[i]], mul(valQ[i], tree2.getSum(en[parent], en[parent] + 1)));
            } else {
                int v = vq[i];
                int div = parent[v] < 0 ? 1 : modInverse(tree2.getSum(en[parent[v]], en[parent[v]] + 1));
                out.println(mul(tree.getSum(en[v], ex[v]), div));
            }
        }
    }
}
