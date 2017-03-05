package coding;

import ru.ifmo.niyaz.DataStructures.FenwickRev;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class MancunianAndDNAPurity {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            a[i] = new int[s.length()];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = s.charAt(j) - 'a';
            }
            sum += s.length();
        }
        int[] val = in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            if (val[i] <= 0 || val[i] > 1000000000) throw new AssertionError();
        }
        int q = in.nextInt();
        MyAhoCorasick aho = new MyAhoCorasick(a, 26);
        aho.init();
        List<Query>[] qs = new List[n + 1];
        for (int i = 0; i <= n; i++) qs[i] = new ArrayList<>();
        long[] ans = new long[q];
        for (int qq = 0; qq < q; qq++) {
            int left = in.nextInt();
            int right = in.nextInt() + 1;
            if (right > n || right <= 0) throw new AssertionError();
            if (left >= n || left < 0) throw new AssertionError();
            String s = in.next();
            sum += s.length();
            int[] b = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                b[i] = s.charAt(i) - 'a';
            }
            qs[right].add(new Query(b, 1, qq));
            qs[left].add(new Query(b, -1, qq));
        }
        if (sum > 2000000) throw new AssertionError();
        for (int i = 0; i <= n; i++) {
            for (Query e : qs[i]) {
                ans[e.id] += e.sign * aho.answer(e.a);
            }
            if (i == n) break;
            int v = 0;
            for (int e : a[i]) {
                v = aho.getLink(v, e);
            }
            aho.add(v, val[i]);
        }
        long mn = Long.MAX_VALUE;
        long mx = Long.MIN_VALUE;
        for (long e : ans) {
            mn = Math.min(mn, e);
            mx = Math.max(mx, e);
        }
        out.println(mn + " " + mx);
    }

    static class Query {
        int[] a;
        int sign;
        int id;

        public Query(int[] a, int sign, int id) {
            this.a = a;
            this.sign = sign;
            this.id = id;
        }
    }

//    static class Tree {
//        MyAhoCorasick[] aho;
//        int n;
//
//        Tree(int[][] a, int[] val) {
//            n = Integer.highestOneBit(a.length) << 1;
//            aho = new MyAhoCorasick[2 * n];
//            build(1, a, val, 0, n);
//        }
//
//        long answer(int left, int right, int[] a) {
//            --right;
//            left += n;
//            right += n;
//            long ret = 0;
//            while (left <= right) {
//                if ((left & 1) == 1) {
//                    ret += aho[left++].answer(a);
//                }
//                if ((right & 1) == 0) {
//                    ret += aho[right--].answer(a);
//                }
//                left >>= 1;
//                right >>= 1;
//            }
//            return ret;
//        }
//
//        void build(int v, int[][] a, int[] val, int left, int right) {
//            int from = left;
//            int to = right;
//            to = Math.min(to, a.length);
//            if (from < to) {
//                aho[v] = new MyAhoCorasick(Arrays.copyOfRange(a, from, to), Arrays.copyOfRange(val, from, to), 26);
//            }
//            if (left >= right - 1) {
//                return;
//            }
//            int mid = (left + right) >>> 1;
//            build(v * 2, a, val, left, mid);
//            build(v * 2 + 1, a, val, mid, right);
//        }
//    }

    static class MyAhoCorasick {
        int[][] g;
        int[] sufLink;
        long[] sum;
        int free;
        int alphabet;
        List<Integer>[] edges;

        int[] en, ex;
        int T;
        FenwickRev f;


        void dfs(int v) {
            en[v] = T++;
            for (int i = 0; i < edges[v].size(); i++) {
                int to = edges[v].get(i);
                dfs(to);
            }
            ex[v] = T;
        }

        void init() {
            en = new int[free];
            ex = new int[free];
            dfs(0);
            f = new FenwickRev(free);
        }

        void add(int v, int val) {
            f.add(en[v], ex[v], val);
        }

        public MyAhoCorasick(int[][] a, int alphabet) {
            this.alphabet = alphabet;
            T = 0;
            int len = 3;
            for (int[] d : a) {
                len += d.length;
            }
            g = new int[alphabet][len];
            edges = new List[len];
            for (int i = 0; i < len; i++) edges[i] = new ArrayList<>();
            for (int[] d : g) {
                Arrays.fill(d, -1);
            }
            sum = new long[len];
            sufLink = new int[len];
            Arrays.fill(sufLink, -1);
            free = 1;
            for (int i = 0; i < a.length; i++) {
                add(a[i]);
            }
            buildSuf();
        }

        long answer(int[] a) {
            int v = 0;
            long ret = 0;
            for (int e : a) {
                v = g[e][v];
                ret += f.getElement(en[v]);
            }
            return ret;
        }

        int add(int[] s) {
            int v = 0;
            for (int c : s) {
                if (g[c][v] == -1) {
                    g[c][v] = free++;
                }
                v = g[c][v];
            }
            return v;
        }

        void buildSuf() {
            Queue<Integer> q = new ArrayDeque<Integer>();
            q.add(0);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int i = 0; i < alphabet; i++) {
                    int u = g[i][v];
                    int w = sufLink[v] == -1 ? 0 : g[i][sufLink[v]];
                    if (u >= 0) {
                        sufLink[u] = w;
                        q.add(u);
                    } else {
                        g[i][v] = w;
                    }
                }
//                if (sufLink[v] >= 0) {
//                    sum[v] += sum[sufLink[v]];
//                }
                if (sufLink[v] >= 0) {
                    edges[sufLink[v]].add(v);
                }
            }
        }

        public int getLink(int v, int c) {
            return g[c][v];
        }

        public int countNodes() {
            return free;
        }

    }


}
