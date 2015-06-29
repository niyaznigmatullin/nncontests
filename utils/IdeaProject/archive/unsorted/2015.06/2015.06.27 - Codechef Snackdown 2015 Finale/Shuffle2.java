package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Random;

public class Shuffle2 {
    static class Node {
        Node left;
        Node right;
        int size;
        int x;

        public Node(int x) {
            this.x = x;
            size = 1;
        }
    }

    static final Random rnd = new Random(23823L);

    static int getSize(Node v) {
        return v == null ? 0 : v.size;
    }

    static void update(Node v) {
        v.size = 1 + getSize(v.left) + getSize(v.right);
    }

    static Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        Node ret;
        if (rnd.nextInt(a.size + b.size) < a.size) {
            (ret = a).right = merge(a.right, b);
        } else {
            (ret = b).left = merge(a, b.left);
        }
        update(ret);
        return ret;
    }

    static void split(Node v, int k, Node[] ans) {
        if (v == null) {
            ans[0] = ans[1] = null;
            return;
        }
        if (getSize(v.left) + 1 <= k) {
            split(v.right, k - getSize(v.left) - 1, ans);
            v.right = ans[0];
            ans[0] = v;
        } else {
            split(v.left, k, ans);
            v.left = ans[1];
            ans[1] = v;
        }
        update(v);
    }

    static long china(long ans1, long mod1, long ans2, long mod2) {
        long m = MathUtils.lcm(mod1, mod2);
        if (mod1 > mod2) {
            long t = ans1;
            ans1 = ans2;
            ans2 = t;
            t = mod1;
            mod1 = mod2;
            mod2 = t;
        }
        for (long i = 0; i < mod1; i++) {
            long v = (i * mod2 + ans2) % m;
            if (v % mod1 == ans1) {
                return v;
            }
        }
        throw new AssertionError();
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        Node root = null;
        for (int i = 0; i < n; i++) root = merge(root, new Node(i));
        Node[] t1 = new Node[2];
        Node[] t2 = new Node[2];
        for (int i = 0; i < m; i++) {
            int left = in.nextInt() - 1;
            int right = in.nextInt();
            split(root, right, t1);
            split(t1[0], left, t2);
            root = merge(t2[1], merge(t2[0], t1[1]));
        }
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            split(root, 1, t1);
            p[i] = t1[0].x;
            root = t1[1];
        }
        if (t == 0) {
            int[] s = in.readIntArray(n);
            for (int i = 0; i < n; i++) s[i]--;
            boolean[] was = new boolean[n];
            int[] mp = new int[n];
            long ans = 0;
            long ansMod = 1;
            for (int i = 0; i < n; i++) {
                if (was[i]) continue;
                int v = i;
                int cn = 0;
                while (!was[v]) {
                    was[v] = true;
                    mp[v] = cn++;
                    v = p[v];
                }
                int move = mp[s[i]];
                ans = china(ans, ansMod, move, cn);
                ansMod = MathUtils.lcm(ansMod, cn);
                if (ansMod > 2e12) {
                    break;
                }
            }
            out.println(ans);
        } else {
            long q = in.nextLong();
            boolean[] was = new boolean[n];
            int[] z = new int[n];
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                if (was[i]) continue;
                int v = i;
                int cn = 0;
                while (!was[v]) {
                    was[v] = true;
                    z[cn++] = v;
                    v = p[v];
                }
                int rot = (int) (q % cn);
                for (int j = 0; j < cn; j++) {
                    ans[z[j]] = z[(j + rot) % cn];
                }
            }
            for (int i = 0; i < n; i++) ++ans[i];
            out.printArray(ans);
        }
    }
}
