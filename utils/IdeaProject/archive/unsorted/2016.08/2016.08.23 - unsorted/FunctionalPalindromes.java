package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.SparseTableMin;
import ru.ifmo.niyaz.DataStructures.SuffixArray;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class FunctionalPalindromes {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.nextInt();
        int q = in.nextInt();
        String wordString = in.next();
        int[] a = new int[wordString.length() + 1];
        for (int i = 0; i < wordString.length(); i++) {
            a[i] = wordString.charAt(i);
        }
        int[] sa = SuffixArray.buildSuffixArray(a);
        int[] lcp = SuffixArray.getLCP(sa, a);
        Queue<Edge> queue = new ArrayDeque<>();
        Edge startEdge = new Edge(0, 0, 0, wordString.length());
        queue.add(startEdge);
        List<Edge>[] edgesByStart = new List[a.length];
        for (int i = 0; i < edgesByStart.length; i++) {
            edgesByStart[i] = new ArrayList<>();
        }
        SparseTableMin sparseMin = new SparseTableMin(lcp);
        List<Edge> allEdges = new ArrayList<>();
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            int left = cur.left;
            int right = cur.right;
            if (left == right) continue;
            while (left <= right) {
                int l = left;
                int r = right + 1;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    if (a[(sa[l] + cur.lenTo) % a.length] == a[(sa[mid] + cur.lenTo) % a.length]) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                int gotLCP = left == l ? a.length - sa[l] : sparseMin.getMin(left, l);
                Edge nEdge = new Edge(cur.lenTo, gotLCP, left, l);
                edgesByStart[sa[nEdge.left]].add(nEdge);
                queue.add(nEdge);
                allEdges.add(nEdge);
                left = r;
            }
        }
        int[] b = new int[a.length * 2 + 1];
        for (int i = 0; i < a.length; i++) {
            b[i * 2 + 1] = a[i];
        }
        int[] d = new int[b.length];
        for (int i = 0, l = -1, r = -1; i < d.length; i++) {
            d[i] = i >= r ? 0 : Math.min(r - i, d[l + r - i]);
            while (i - d[i] >= 0 && i + d[i] < b.length && b[i - d[i]] == b[i + d[i]]) {
                ++d[i];
            }
            if (i + d[i] > r) {
                l = i - d[i];
                r = i + d[i];
            }
        }
        List<Integer>[] end = new List[b.length];
        for (int i = 0; i < end.length; i++) {
            end[i] = new ArrayList<>();
        }
        for (int i = 0; i < d.length; i++) {
            end[i - d[i] + 1].add(i);
        }
        Fenwick f = new Fenwick(d.length);
        for (int i = d.length - 1; i >= 0; i--) {
            f.add(i, 1);
            if ((i & 1) == 1) {
                int real = i >> 1;
                for (Edge e : edgesByStart[real]) {
//                    System.out.println(real + " " + e.lenFrom + " " + e.lenTo);
                    int left = 2 * (e.lenFrom + real) + 1;
                    int right = 2 * (e.lenTo + real - 1) + 1;
//                    System.out.println(left + " " + right);
                    left = (left + i) / 2;
                    right = (right + i) / 2;
//                    System.out.println(left + " " + right);
                    e.count = f.getSum(left, right + 1);
//                    System.out.println(e.count);
                }
            }
            for (int j : end[i]) {
                f.add(j, -1);
            }
        }
        Query[] qs = new Query[q];
        for (int i = 0; i < q; i++) {
            qs[i] = new Query(in.nextLong());
        }
        Collections.sort(allEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.left != o2.left) return Integer.compare(o1.left, o2.left);
                return -Integer.compare(o1.right, o2.right);
            }
        });
        Query[] sorted = qs.clone();
        Arrays.sort(sorted, new Comparator<Query>() {
            @Override
            public int compare(Query o1, Query o2) {
                return Long.compare(o1.k, o2.k);
            }
        });
        long got = 0;
        int cur = 0;
//        System.out.println(Arrays.toString(sa));
        for (Edge e : allEdges) {
//            System.out.println(e.left + " " + e.right + " " + e.lenFrom + " " + e.lenTo + " " + e.count);
            long count = (long) e.count * (e.right - e.left + 1);
            while (cur < q && sorted[cur].k < got + count) {
                Query query = sorted[cur];
                query.pos = (int) ((query.k - got) / (e.right - e.left + 1));
                e.toAnswer.add(query);
//                System.out.println(query.k + " " + e.left + " " + e.right);
                ++cur;
            }
            got += count;
        }
        Hash hashes = new Hash(a);
        for (int i = d.length - 1; i >= 0; i--) {
            f.add(i, 1);
            if ((i & 1) == 1) {
                int real = i >> 1;
                for (Edge e : edgesByStart[real]) {
                    int left = 2 * (e.lenFrom + real) + 1;
                    int right = 2 * (e.lenTo + real - 1) + 1;
                    left = (left + i) / 2;
                    right = (right + i) / 2;
                    for (Query cq : e.toAnswer) {
                        int l = left;
                        int r = right + 1;
                        while (l < r - 1) {
                            int mid = (l + r) >> 1;
                            if (f.getSum(left, mid) <= cq.pos) {
                                l = mid;
                            } else {
                                r = mid;
                            }
                        }
                        int from = real;
                        int to = (2 * r - i) / 2;
//                        System.out.println("   " + cq.k + " " + from + " " + to);
                        cq.answer = hashes.getHash(from, to);
                    }
                }
            }
            for (int j : end[i]) {
                f.add(j, -1);
            }
        }
        for (Query e : qs) {
            out.println(e.answer);
        }
    }

    static class Hash {
        int[] a;
        long[] h;
        static final int MOD = 1000000007;
        static final int A = 100001;
        long[] aPow;

        Hash(int[] a) {
            this.a = a;
            h = new long[a.length];
            long curHash = 0;
            for (int i = 0; i < a.length; i++) {
                curHash = (curHash * A + a[i]) % MOD;
                h[i] = curHash;
            }
            aPow = new long[a.length + 1];
            aPow[0] = 1;
            for (int i = 1; i < aPow.length; i++) {
                aPow[i] = aPow[i - 1] * A % MOD;
            }
        }

        long getHash(int left, int right) {
            long ret = h[right - 1];
            if (left > 0) {
                ret -= h[left - 1] * aPow[right - left] % MOD;
            }
            if (ret < 0) ret += MOD;
            return ret;
        }
    }

    static class Query {
        long k;
        long answer;
        int pos;

        public Query(long k) {
            this.k = k;
            answer = -1;
        }
    }

    static class Edge {
        int lenFrom;
        int lenTo;
        int left;
        int right;
        int count;
        List<Query> toAnswer;

        public Edge(int lenFrom, int lenTo, int left, int right) {
            this.lenFrom = lenFrom;
            this.lenTo = lenTo;
            this.left = left;
            this.right = right;
            count = 0;
            toAnswer = new ArrayList<>();
        }
    }
}
