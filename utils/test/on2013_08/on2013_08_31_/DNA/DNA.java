package lib.test.on2013_08.on2013_08_31_.DNA;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DNA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int[] a = new int[s.length() + 2];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            a[i + 1] = c >= 'A' && c <= 'Z' ? c - 'A' : c - '0' + 26;
        }
        a[0] = 37;
        a[a.length - 1] = 38;
        SuffixAutomaton sa = new SuffixAutomaton(a);
        SuffixAutomaton sb = new SuffixAutomaton(ArrayUtils.reverse(a));
        Hash h = new Hash(a);
        Set<Element> set1 = new HashSet<>();
        for (int i = 1; i < sa.free; i++) {
            int count = 0;
            for (int j = 0; j < sa.link.length; j++) {
                if (sa.link[j][i] >= 0) ++count;
            }
            if (count > 1) {
                int pos = a.length - sa.shortestPath[i];
                for (int len = sa.length[sa.sufLink[i]] + 1; len <= sa.length[i]; len++) {
                    long h1 = h.getHash1(pos - len, pos);
                    long h2 = h.getHash2(pos - len, pos);
                    set1.add(new Element(h1, h2, len));
                }
            }
        }
        Set<Element> set2 = new HashSet<>();
        for (int i = 1; i < sb.free; i++) {
            int count = 0;
            for (int j = 0; j < sb.link.length; j++) {
                if (sb.link[j][i] >= 0) ++count;
            }
            if (count > 1) {
                int pos = sb.shortestPath[i];
                for (int len = sb.length[sb.sufLink[i]] + 1; len <= sb.length[i]; len++) {
                    long h1 = h.getHash1(pos, pos + len);
                    long h2 = h.getHash2(pos, pos + len);
                    set2.add(new Element(h1, h2, len));
                }
            }
        }
        int countAll = 0;
        int maximal = 0;
        int numberOfMaximal = 0;
        for (Element e : set1) {
            if (!set2.contains(e)) {
                continue;
            }
            countAll++;
            if (maximal < e.length) {
                maximal = e.length;
                numberOfMaximal = 1;
            } else if (maximal == e.length) {
                numberOfMaximal++;
            }
        }
        out.println(countAll);
        out.println(maximal);
        out.println(numberOfMaximal);
    }

    static class Element {
        long h1;
        long h2;
        int length;

        Element(long h1, long h2, int length) {
            this.h1 = h1;
            this.h2 = h2;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Element)) return false;

            Element element = (Element) o;

            if (h1 != element.h1) return false;
            if (h2 != element.h2) return false;
            if (length != element.length) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (h1 ^ (h1 >>> 32));
            result = 31 * result + (int) (h2 ^ (h2 >>> 32));
            result = 31 * result + length;
            return result;
        }
    }

    static class Hash {
        int[] a;
        long[] h1;
        long[] h2;
        static final long P = 33533;
        static final long MOD1 = 1000000007;
        static final long MOD2 = 999999937;
        static long[] POW1;
        static long[] POW2;

        {
            POW1 = new long[123456];
            POW2 = new long[123456];
            POW1[0] = POW2[0] = 1;
            for (int i = 1; i < POW1.length; i++) {
                POW1[i] = POW1[i - 1] * P % MOD1;
                POW2[i] = POW2[i - 1] * P % MOD2;
            }
        }

        Hash(int[] a) {
            this.a = a;
            h1 = new long[a.length];
            h2 = new long[a.length];
            for (int i = 0; i < a.length; i++) {
                h1[i] = ((i == 0 ? 0 : h1[i - 1]) * P + a[i] + 1) % MOD1;
                h2[i] = ((i == 0 ? 0 : h2[i - 1]) * P + a[i] + 1) % MOD2;
            }
        }

        long getHash1(int left, int right) {
            long ret = h1[right - 1];
            if (left > 0) {
                ret = (ret - h1[left - 1] * POW1[right - left] + MOD1 * MOD1) % MOD1;
            }
            return ret;
        }

        long getHash2(int left, int right) {
            long ret = h2[right - 1];
            if (left > 0) {
                ret = (ret - h2[left - 1] * POW2[right - left] + MOD2 * MOD2) % MOD2;
            }
            return ret;
        }
    }

    static class SuffixAutomaton {
        int[][] link;
        int[] sufLink;
        int[] length;
        int[] shortestPath;
        boolean[] terminal;
        int free;

        SuffixAutomaton(int[] a) {
            int max = 0;
            for (int i : a) {
                max = Math.max(max, i);
            }
            ++max;
            int all = a.length * 2 + 1;
            link = new int[max][all];
            sufLink = new int[all];
            length = new int[all];
            terminal = new boolean[all];
            shortestPath = new int[all];
            free = 0;
            int v = newNode(0);
            for (int i : a) {
                v = append(v, i);
            }
            while (v >= 0) {
                terminal[v] = true;
                v = sufLink[v];
            }
            Arrays.fill(shortestPath, -1);
            dfs(0);
        }

        void dfs(int v) {
            if (shortestPath[v] >= 0) return;
            if (terminal[v]) shortestPath[v] = 0;
            else shortestPath[v] = Integer.MAX_VALUE;
            for (int i = 0; i < link.length; i++) {
                int to = link[i][v];
                if (to < 0) continue;
                dfs(to);
                shortestPath[v] = Math.min(shortestPath[v], shortestPath[to] + 1);
            }
        }

        int append(int v, int c) {
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
            int copy = copy(q, length[v] + 1);
            while (v >= 0 && link[c][v] == q) {
                link[c][v] = copy;
                v = sufLink[v];
            }
            sufLink[u] = sufLink[q] = copy;
            return u;
        }

        int newNode(int len) {
            length[free] = len;
            sufLink[free] = -1;
            for (int i = 0; i < link.length; i++) {
                link[i][free] = -1;
            }
            return free++;
        }

        int copy(int v, int len) {
            length[free] = len;
            sufLink[free] = sufLink[v];
            for (int i = 0; i < link.length; i++) {
                link[i][free] = link[i][v];
            }
            return free++;
        }
    }
}
