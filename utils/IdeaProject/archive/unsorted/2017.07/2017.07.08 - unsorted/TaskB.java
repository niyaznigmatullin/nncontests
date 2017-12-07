package coding;

import com.sun.org.apache.bcel.internal.generic.DSUB;
import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskB {

    static boolean[][] was;

    static boolean check() {
        int n = was.length;
        int m = was[0].length;
        DisjointSetUnion dsu = new DisjointSetUnion(n * m);
        int c1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (((i + j) & 1) == 0 || was[i][j]) continue;
                int deg = 0;
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int x = i + dx;
                        int y = j + dy;
                        if (x < 0 || y < 0 || x >= n || y >= m || was[x][y]) continue;
                        dsu.union(i * m + j, x * m + y);
                        deg++;
                    }
                }
                if (deg == 1) {
                    c1++;
                }
            }
        }
        if (c1 > 1) return false;
        int have = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (((i + j) & 1) == 0 || was[i][j]) continue;
                int cur = (dsu.get(i * m + j));
                if (have >= 0 && have != cur) return false;
                have = cur;
            }
        }
        return true;
    }

    static boolean go(int x, int y, int n, int m, int steps) {
        if (steps == n * m / 2) {
            System.out.println(x + " " + y);
            return true;
        }
//        if (!check()) {
//            return false;
//        }
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || was[nx][ny]) continue;
                was[nx][ny] = true;
                if (go(nx, ny, n, m, steps + 1)) {
                    System.out.println(x + " " + y);
                    return true;
                }
                was[nx][ny] = false;
            }
        }
        return false;
    }

    static List<int[]> solve(int n, int m) {
        if (m == 1) {
            if (n == 1) {
                return new ArrayList<>();
            }
            if (n == 3) {
                List<int[]> ret = new ArrayList<>();
                ret.add(new int[]{1, 0});
                return ret;
            }
            throw new AssertionError(n + " " + m);
        }
        if (m == 3) {
            if (n == 3) {
                List<int[]> ret = new ArrayList<>();
                ret.add(new int[]{1, 0});
                ret.add(new int[]{0, 1});
                ret.add(new int[]{1, 2});
                ret.add(new int[]{2, 1});
                return ret;
            }
            if (n == 5) {
                List<int[]> ret = new ArrayList<>();
                ret.add(new int[]{1, 0});
                ret.add(new int[]{0, 1});
                ret.add(new int[]{1, 2});
                ret.add(new int[]{2, 1});
                ret.add(new int[]{3, 2});
                ret.add(new int[]{4, 1});
                ret.add(new int[]{3, 0});
                return ret;
            }
            throw new AssertionError();
        }
        List<int[]> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ret.add(new int[]{~i & 1, i});
        }
        for (int i = 2; i < n; i++) {
            ret.add(new int[]{i, m - 1 - (~i & 1)});
        }
        for (int i = m - 3; i >= 0; i--) {
            ret.add(new int[]{n - 1 - (~i & 1), i});
        }
        for (int i = n - 3; i >= 2; i--) {
            ret.add(new int[]{i, ~i & 1});
        }
        List<int[]> got = solve(n - 4, m - 4);
        for (int[] e : got) {
            ret.add(new int[]{e[0] + 2, e[1] + 2});
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int nn = n;
        int mm = m;
        boolean toSwap = false;
        if (n > m) {
            toSwap = true;
            int t = n;
            n = m;
            m = t;
        }
        String color = in.next();
        List<int[]> answer = new ArrayList<>();
        if (n == 2) {
            if (color.equals("W")) {
                for (int i = 0; i < m; i++) {
                    answer.add(new int[]{i & 1, i});
                }
            } else {
                for (int i = 0; i < m; i++) {
                    answer.add(new int[]{~i & 1, i});
                }
            }
        } else {
            if (n % 2 == 0 || m % 2 == 0 || color.equals("W") || m > n + 2) {
                out.println("impossible");
                out.println();
                return;
            }
            answer = solve(m, n);
            toSwap = !toSwap;
        }
        for (int[] e : answer) {
            if (toSwap) {
                ArrayUtils.reverse(e);
            }
            out.println(e[0] + 1 + " " + (e[1] + 1));
        }
        if (answer.size() != n * m / 2) {
            throw new AssertionError();
        }
        Set<Integer> f = new HashSet<>();
        for (int[] a : answer) {
            if (a[0] < 0 || a[0] >= nn || a[1] < 0 || a[1] >= mm) throw new AssertionError();
            if (((a[0] + a[1]) % 2 == 0) != color.equals("W")) throw new AssertionError();
            if (!f.add(a[0] * 1000 + a[1])) {
                throw new AssertionError();
            }
        }
        for (int i = 1; i < answer.size(); i++) {
            int[] a = answer.get(i - 1);
            int[] b = answer.get(i);
            if (Math.abs(a[0] - b[0]) == 1 && Math.abs(a[1] - b[1]) == 1) {

            } else throw new AssertionError();
        }
        out.println();
//        int n = 9;
//        int m = 13;
//        was = new boolean[n][m];
//        was[1][0] = true;
//        go(1, 0, n, m, 1);
//        System.out.println(testNumber);
    }
}
