package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ShortSystem {

    static final int[] ANSWER = {0, 1, 1, 2, 6, 41, 990};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
//        if (n == 5) throw new AssertionError();
        out.println(ANSWER[n]);
        if (true) return;
        ans = 0;
        all = new HashSet<>();
        n = in.nextInt();
        mask = new int[n][n];
        for (int[] d : mask) {
            Arrays.fill(d, -1);
        }
//        count = 0;
        go(0, 1);
        out.println(all.size());
    }

//    static int count;
    static class Element {
        int[] a;

        Element(int[][] b, int n) {
//            System.err.println(++count);
            int[] p = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
            a = new int[n * (n - 1) / 2];
            for (int i = 0, k = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    a[k++] = b[i][j];
                }
            }
            do {
                boolean ok = false;
                int[] d = new int[n * (n - 1) / 2];
                all:
                for (int i = 0, z = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        int mask = p[i] > p[j] ? b[p[j]][p[i]] : b[p[i]][p[j]];
                        if (mask < 0) throw new AssertionError();
                        int v = 0;
                        for (int k = 0; k < n; k++) {
                            if (((mask >> p[k]) & 1) == 1) {
                                v |= 1 << k;
                            }
                        }
                        if (a[z] > v) {
                            ok = true;
                        } else if (!ok && a[z] < v) {
                            break all;
                        }
                        d[z++] = v;
                    }
                }
//                int[][] c = new int[n][n];
//                for (int i = 0; i < n; i++) {
//                    for (int j = i + 1; j < n; j++) {
//                        int v = 0;
//                        for (int k = 0; k < n; k++) {
//                            if (((b[i][j] >> k) & 1) == 1) {
//                                v |= 1 << p[k];
//                            }
//                        }
//                        if (p[i] > p[j]) c[p[j]][p[i]] = v;
//                        else c[p[i]][p[j]] = v;
//                    }
//                }
//                int[] d = new int[n * (n - 1) / 2];
//                for (int i = 0, k = 0; i < n; i++) {
//                    for (int j = i + 1; j < n; j++) {
//                        d[k++] = c[i][j];
//                    }
//                }
                if (ok) {
                    a = d;
                }
            } while (ArrayUtils.nextPermutation(p));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Element)) return false;

            Element element = (Element) o;

            if (!Arrays.equals(a, element.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }

    static int n;
    static long ans;
    static int[][] mask;

    static void go(int v, int u) {
        if (v == n - 1) {
            ++ans;
            all.add(new Element(mask, n));
//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < n; j++) {
//                    System.out.println(i + " " + j + " " + getMask(i, j));
//                }
//            }
            return;
        }
        if (u == n) {
            go(v + 1, v + 2);
            return;
        }
        int[] path = new int[n];
        path[0] = v;
        makePath(v, u, 1, path);
    }

    static HashSet<Element> all;

    static void makePath(int v, int u, int count, int[] path) {
        if (path[count - 1] == u) {
            go(v, u + 1);
            return;
        }
        all:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < count; j++) {
                if (path[j] == i) {
                    continue all;
                }
            }
            int cMask = 1 << i;
            for (int j = count - 1; j >= 0; j--) {
                cMask |= 1 << path[j];
                int need = getMask(path[j], i);
                if (need >= 0 && need != cMask) {
                    continue all;
                }
            }
            int back = 0;
            cMask = 1 << i;
            for (int j = count - 1; j >= 0; j--) {
                cMask |= 1 << path[j];
                int need = getMask(path[j], i);
                if (need < 0) {
                    back |= 1 << j;
                    setMask(path[j], i, cMask);
                }
            }
            path[count] = i;
            makePath(v, u, count + 1, path);
            for (int j = count - 1; j >= 0; j--) {
                if (((back >> j) & 1) == 1) {
                    setMask(path[j], i, -1);
                }
            }
        }
    }

    static int getMask(int v, int u) {
        if (v > u) return mask[u][v];
        else return mask[v][u];
    }

    static void setMask(int v, int u, int c) {
        if (v > u) {
            int t = v;
            v = u;
            u = t;
        }
        mask[v][u] = c;
    }
}
