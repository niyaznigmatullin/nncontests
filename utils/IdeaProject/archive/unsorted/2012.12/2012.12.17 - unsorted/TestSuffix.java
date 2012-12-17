package coding;

import ru.ifmo.niyaz.DataStructures.SuffixTreeList;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TestSuffix {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = "abacabadabacaba";
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i) - 'a';
        }
        SuffixTreeList st = new SuffixTreeList(a);
        long ans = 0;
        for (int i = 0; i < st.free; i++) {
            for (int e = st.head[i]; e >= 0; e = st.next[e]) {
                int v = st.to[e];
                System.out.print(i + " " + v + " " + st.parent[v] + " ");
                if (st.depth[v] < 0) {
                    ans += s.length() - st.start[v];
                    System.out.println(s.substring(st.start[v]));
                } else {
                    ans += st.depth[v] - st.depth[i];
                    System.out.println(s.substring(st.start[v], st.start[v] + st.depth[v] - st.depth[i]));
                    if (st.parent[v] != i) {
                        throw new AssertionError();
                    }
                }
            }
        }
        out.println(ans);
	}
}
