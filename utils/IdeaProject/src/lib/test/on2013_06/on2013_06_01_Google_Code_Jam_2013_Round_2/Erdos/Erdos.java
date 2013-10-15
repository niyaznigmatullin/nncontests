package lib.test.on2013_06.on2013_06_01_Google_Code_Jam_2013_Round_2.Erdos;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Erdos {

    static class Hash {
        int[] a;
        int[] b;

        @Override
        public boolean equals(Object o) {

            Hash hash = (Hash) o;

            if (!Arrays.equals(a, hash.a)) return false;
            if (!Arrays.equals(b, hash.b)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = a != null ? Arrays.hashCode(a) : 0;
            result = 31 * result + (b != null ? Arrays.hashCode(b) : 0);
            return result;
        }

        Hash(int[] a, int[] b) {

            this.a = a;
            this.b = b;
        }
    }

    static int[] a;
    static int[] b;
    static int n;
    static boolean[] was;
    static int[] p;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        asd();
        out.print("Case #" + testNumber + ":");
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        a = in.readIntArray(n);
        b = in.readIntArray(n);
        was = new boolean[n];
        p = new int[n];
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        if (!go(0, n)) {
            out.println("no solution");
            return;
        }
        for (int i = 0; i < n; i++) {
            out.print(" " + (p[i] + 1));
        }
        out.println();
    }

    static boolean go(int x, int n) {
        if (x == n) {
            for (int i = n - 1; i >= 0; i--) {
                int cur = 1;
                for (int j = n - 1; j > i; j--) {
                    if (p[j] < p[i] && b[j] + 1 > cur) cur = b[j] + 1;
                }
                if (cur != b[i]) return false;
            }
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (was[i]) {
                continue;
            }
            p[x] = i;
            int cur = 1;
            for (int j = 0; j < x; j++) {
                if (p[j] < p[x] && cur < a[j] + 1) {
                    cur = a[j] + 1;
                }
            }
            if (cur != a[x]) {
                continue;
            }
            int cn = 0;
            for (int j = 0; j < i; j++) {
                if (!was[j]) {
                    ++cn;
                }
            }
            if (cn + 1 < b[x]) {
                continue;
            }
            int countMore = 0;
            for (int j = x + 1; j < n; j++) {
                if (b[j] + 1 > b[x]) {
                    ++countMore;
                }
            }
            cn = 0;
            for (int j = i + 1; j < n; j++) {
                if (!was[j]) {
                    ++cn;
                }
            }
            if (cn < countMore) {
                continue;
            }
            was[i] = true;
            if (go(x + 1, n)) {
                return true;
            }
            was[i] = false;
        }
        return false;
    }

    static void asd() {
        for (int n = 2; n < 8; n++) {
            int[] p = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
            HashMap<Hash, List<int[]>> z = new HashMap<>();
            do {
                System.out.println(Arrays.toString(p));
                int[] a = new int[n];
                int[] b = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = 1;
                    for (int j = 0; j < i; j++) {
                        if (p[i] > p[j] && a[j] + 1 > a[i]) a[i] = a[j] + 1;
                    }
                }
                for (int i = n - 1; i >= 0; i--) {
                    b[i] = 1;
                    for (int j = n - 1; j > i; j--) {
                        if (p[i] > p[j] && b[j] + 1 > b[i]) b[i] = b[j] + 1;
                    }
                }
                Hash h = new Hash(a, b);
                List<int[]> u = z.get(h);
                if (u == null) {
                    u = new ArrayList<>();
                }
                u.add(p.clone());
                z.put(h, u);
            } while (ArrayUtils.nextPermutation(p));
            for (Hash h : z.keySet()) {
                List<int[]> u = z.get(h);
                if (u.size() > 1) {
                    System.out.println("here");
                    for (int[] e : u) {
                        System.out.println(Arrays.toString(e));
                    }
                    System.out.println("hash = ");
                    System.out.println(Arrays.toString(h.a));
                    System.out.println(Arrays.toString(h.b));
                }
            }
        }
    }
}
