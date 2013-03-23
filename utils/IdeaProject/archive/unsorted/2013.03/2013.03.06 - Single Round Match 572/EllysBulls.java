package coding;

import java.util.*;

public class EllysBulls {
    static String[] s;
    static int[] a;
    static int[] p;

    public String getNumber(String[] s, int[] a) {
        EllysBulls.s = s;
        EllysBulls.a = a;
        int half = s[0].length() / 2;
        p = new int[half];
        Map<Vector, List<Vector>> m1 = new HashMap<Vector, List<Vector>>();
        Map<Vector, List<Vector>> m2 = new HashMap<Vector, List<Vector>>();
        go(0, half, 0, m1);
        p = new int[s[0].length() - half];
        go(0, p.length, half, m2);
        Vector a1 = null;
        Vector a2 = null;
        for (Vector e : m1.keySet()) {
            int[] z = new int[s.length];
            for (int i = 0; i < z.length; i++) {
                z[i] = a[i] - e.a[i];
            }
            List<Vector> list = m2.get(new Vector(z));
            if (list == null) {
                continue;
            }
            List<Vector> list2 = m1.get(e);
            if (list2.size() > 1 || list.size() > 1 || a1 != null) {
                return "Ambiguity";
            }
            a1 = list2.get(0);
            a2 = list.get(0);
        }
        if (a1 == null) return "Liar";
        StringBuilder ans = new StringBuilder();
        for (int i : a1.a) {
            ans.append(i);
        }
        for (int i : a2.a) {
            ans.append(i);
        }
        return ans.toString();
    }

    void go(int x, int n, int sh, Map<Vector, List<Vector>> m) {
        if (x == n) {
            check(n, sh, m);
            return;
        }
        for (int i = 0; i < 10; i++) {
            p[x] = i;
            go(x + 1, n, sh, m);
        }
    }

    void check(int n, int sh, Map<Vector, List<Vector>> m) {
        int[] z = new int[s.length];
        for (int id = 0; id < s.length; id++) {
            for (int i = 0; i < n; i++) {
                if (s[id].charAt(i + sh) - '0' == p[i]) {
                    z[id]++;
                }
            }
        }
        for (int i = 0; i < z.length; i++) {
            if (z[i] > a[i]) {
                return;
            }
        }
        Vector v = new Vector(z);
        List<Vector> s = m.get(v);
        if (s == null) {
            s = new ArrayList<Vector>();
            m.put(v, s);
        }
        s.add(new Vector(p.clone()));
    }

    static class Vector {
        int[] a;

        Vector(int[] a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            Vector vector = (Vector) o;

            if (!Arrays.equals(a, vector.a)) return false;

            return true;
        }

        @Override
        public String toString() {
            return "Vector{" +
                    "a=" + Arrays.toString(a) +
                    '}';
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }
}
