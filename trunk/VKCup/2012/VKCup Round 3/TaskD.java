package mypackage;

import com.sun.jdi.IntegerType;
import graphalgorithms.DinicGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TaskD {

    static int[] a;
    static int[] b;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        long l = -1;
        long r = 64000L * 64000 + 1;
        a = new int[n * n];
        b = new int[n * n];
        while (l < r - 1) {
            long mid = l + r >> 1;
            DinicGraph d = new DinicGraph(2 + 2 * n);
            for (int i = 0; i < n; i++) {
                d.addEdge(0, i + 1, 0, 1);
                d.addEdge(i + n + 1, n + n + 1, 0, 1);
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    if (dx * dx + dy * dy > mid) {
                        d.addEdge(i, j + n + 1, 0, 1);
                        a[count] = i;
                        b[count] = j;
                        ++count;
                    }
                }
            }
            if (d.getMaxFlow(0, 1 + n + n) / 2 > k) {
                l = mid;
                continue;
            }
            if (solveMAX2Sat(a, b, count, n) > k) {
                l = mid;
            } else {
                r = mid;
            }
        }


        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long dx = x[i] - x[j];
                long dy = y[i] - y[j];
                if (dx * dx + dy * dy > r) {
                    a[count] = i;
                    b[count] = j;
                    ++count;
                }
            }
        }
//        for (int i = 0; i < count
//                ; i++) {
//            System.err.println(a[i] + " " + b[i]);
//        }
        int ans = solveMAX2Sat(a, b, count, n);
//        System.err.println(ans);
        int zz = 0;
        for (int i = 0; i < n; i++) {
            if (answer[i]) {
                ++zz;
            }
        }
        for (int i = 0; i < n; i++) {
            if (zz < k && !answer[i]) {
                zz++;
                answer[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (answer[i]) {
                out.print(i + 1 + " ");
            }
        }
    }

    static int solveMAX2Sat(int[] a, int[] b, int count, int n) {
        Set<Integer> set = new TreeSet<Integer>();
        for (int i : a) {
            set.add(i);
        }
        for (int i : b) {
            set.add(i);
        }
        Integer[] all = set.toArray(new Integer[set.size()]);
        best = Integer.MAX_VALUE;
        current = new boolean[n];
        go(0, all, all.length, a, b, count, 0);
        return best;
    }

    static boolean[] answer;
    static boolean[] current;
    static int best;

    static void go(int x, Integer[] all, int n, int[] a, int[] b, int count, int z) {
        if (z >= best) {
            return;
        }
        if (x == all.length) {
            best = z;
            answer = current.clone();
            return;
        }
        if (z + 1 < best) {
            current[all[x]] = true;
            boolean ok = check(all[x], a, b, count);
            if (ok) {
                go(x + 1, all, n, a, b, count, z + 1);
            }
            current[all[x]] = false;
        }
        current[all[x]] = false;
        boolean ok = check(all[x], a, b, count);
        if (ok) {
            go(x + 1, all, n, a, b, count, z);
        }
    }

    private static boolean check(Integer integer, int[] a, int[] b, int count) {
        boolean ok = true;
        for (int i = 0; i < count; i++) {
            if (a[i] > integer || b[i] > integer) {
                continue;
            }
            if (!current[a[i]] && !current[b[i]]) {
                ok = false;
                break;
            }
        }
        return ok;
    }
}
