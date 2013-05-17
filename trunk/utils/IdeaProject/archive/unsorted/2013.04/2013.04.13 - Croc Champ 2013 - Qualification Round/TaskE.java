package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    static int[] parent;
    static String[] edge;
    static int[] pos;
    static int[] p;
    static String t;
    static long ans;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        parent = new int[n];
        edge = new String[n];
        parent[0] = -1;
        for (int i = 1; i < n; i++) {
            parent[i] = in.nextInt() - 1;
            edge[i] = in.next();
        }
        t = in.next();
        p = new int[t.length()];
        p[0] = 0;
        for (int i = 1, k = 0; i < t.length(); i++) {
            while (k > 0 && t.charAt(k) != t.charAt(i)) k = p[k - 1];
            if (t.charAt(k) == t.charAt(i)) ++k;
            p[i] = k;
        }
        ans = 0;
        pos = new int[n];
        Arrays.fill(pos, Integer.MIN_VALUE);
        pos[0] = 0;
        for (int i = 1; i < n; i++) {
            if (pos[i] == Integer.MIN_VALUE) {
                getPos(i);
            }
        }
        out.println(ans);
    }

    static int getPos(int v) {
        if (pos[parent[v]] == Integer.MIN_VALUE) {
            getPos(parent[v]);
        }
        int k = pos[parent[v]];
        String e = edge[v];
        for (int i = 0; i < e.length(); i++) {
            while (k > 0 && (k >= p.length || e.charAt(i) != t.charAt(k))) k = p[k - 1];
            if (k < p.length && e.charAt(i) == t.charAt(k)) ++k;
            if (k == t.length()) {
                ++ans;
            }
        }
        return pos[v] = k;
    }
}
