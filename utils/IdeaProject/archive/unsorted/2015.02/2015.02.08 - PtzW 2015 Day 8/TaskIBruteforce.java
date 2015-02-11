package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskIBruteforce {

    static boolean connected(boolean[][] a) {
        int n = a.length;
        int comps = n;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j]) {
                    if (dsu.union(i, j)) {
                        --comps;
                    }
                }
            }
        }
        return comps == 1;
    }

    static boolean[][] add(boolean[][] a) {
        int n = a.length;
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j]) {
                    deg[i]++;
                }
            }
        }
        int[][] distance = new int[n][n];
        int[] profile = new int[n];
        for (int start = 0; start < n; start++) {
            int[] q = new int[n];
            int head = 0;
            int tail = 0;
            q[tail++] = start;
            int[] d = distance[start];
            Arrays.fill(d, Integer.MAX_VALUE);
            d[start] = 0;
            while (head < tail) {
                int v = q[head++];
                for (int i = 0; i < n; i++) {
                    if (!a[v][i] || d[i] != Integer.MAX_VALUE) continue;
                    d[i] = d[v] + 1;
                    q[tail++] = i;
                }
            }
            int[] r = new int[n];
            for (int dist = 0; dist < n; dist++) {
                int[] f = new int[n];
                int cn = 0;
                for (int i = 0; i < n; i++) {
                    if (d[i] == dist) {
                        f[cn++] = deg[i];
                    }
                }
                f = Arrays.copyOf(f, cn);
                Arrays.sort(f);
                DegreeProfileAndSomethingElse hereSomething = new DegreeProfileAndSomethingElse(f.clone());
                int z = codeDegreeProfile(degreesSets, hereSomething);
                r[dist] = z;
            }
            profile[start] = codeDegreeProfile(degreeProfiles, new DegreeProfileAndSomethingElse(r));
            if (start > 0 && profile[start] <= profile[start - 1]) return null;
        }
        DegreeProfileAndSomethingElse w = new DegreeProfileAndSomethingElse(profile.clone());
        if (graphsByProfiles.containsKey(w)) {
//            return graphsByProfiles.get(w);
            ++epsilon;
            System.err.println("epsilon = " + epsilon);
        }
        boolean[][] b = a.clone();
        for (int i = 0; i < b.length; i++) b[i] = b[i].clone();
        graphsByProfiles.put(w, b);
        System.out.println(" " + graphsByProfiles.size());
        return null;
    }

    static int epsilon;

    static Map<DegreeProfileAndSomethingElse, boolean[][]> graphsByProfiles;
    static Map<DegreeProfileAndSomethingElse, Integer> degreeProfiles;
    static Map<DegreeProfileAndSomethingElse, Integer> degreesSets;

    static <T> int codeDegreeProfile(Map<T, Integer> map, T e) {
        Integer g = map.get(e);
        if (g == null) g = map.size();
        map.put(e, g);
        return g;
    }

    static class DegreeProfileAndSomethingElse {
        int[] a;
        int hash;

        DegreeProfileAndSomethingElse(int[] a) {
            this.a = a;
            hash = Integer.MIN_VALUE;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            DegreeProfileAndSomethingElse that = (DegreeProfileAndSomethingElse) o;
            if (!Arrays.equals(a, that.a)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            if (hash != Integer.MIN_VALUE) return hash;
            return hash = (a != null ? Arrays.hashCode(a) : 0);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = 8;
        degreeProfiles = new HashMap<>();
        degreesSets = new HashMap<>();
        graphsByProfiles = new HashMap<>();
        int cnt = 0;
        for (int mask = 0; mask < 1 << (n * (n - 1) / 2); mask++) {
            if (Integer.bitCount(mask) < 11) {
                continue;
            }
            ++cnt;
            if (cnt % 50000 == 0) {
                System.err.println(cnt);
            }
            boolean[][] a = new boolean[n][n];
            for (int i = 0, m = mask; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    a[j][i] = a[i][j] = (m & 1) == 1;
                    m >>= 1;
                }
            }
            if (!connected(a)) continue;
            boolean[][] got = add(a);
            if (got == null) continue;
            {
                out.println("{");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!a[i][j]) continue;
                        out.println("{" + i + ", " + j + "},");
                    }
                }
                out.println("}");
                out.println("{");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!got[i][j]) continue;
                        out.println("{" + i + ", " + j + "},");
                    }
                }
                out.println("}");
            }
            return;
        }
    }
}
