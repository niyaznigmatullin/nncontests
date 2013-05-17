package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Similar {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        Element[] a = new Element[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Element();
        }
        int[] best = new int[n];
        int[] pair = new int[n];
        Arrays.fill(pair, -1);
        Arrays.fill(best, -1);
        for (int mask = 0; mask < 1 << k; mask++) {
            Element.len = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) {
                a[i].id = i;
//                int cn = 0;
                a[i].hash = 0;
                for (int j = 0; j < k; j++) {
                    if (((mask >> j) & 1) == 0) continue;
                    a[i].hash = a[i].hash * 200 + s[i].charAt(j);
//                    a[i].c[cn++] = s[i].charAt(j);
                }
            }
            Arrays.sort(a);
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && a[i].compareTo(a[j]) == 0) {
                    ++j;
                }
                if (j - i > 1) {
                    int id1 = a[i].id;
                    int id2 = a[i + 1].id;
                    for (int cur = i; cur < j; cur++) {
                        if (best[a[cur].id] < Element.len) {
                            best[a[cur].id] = Element.len;
                            pair[a[cur].id] = a[cur].id == id1 ? id2 : id1;
                        }
                    }
                }
                i = j;
            }
        }
        for (int i = 0; i < n; i++) {
            out.println((k - best[i]) + " " + (pair[i] + 1));
        }
    }

    static class Element implements Comparable<Element> {
//        char[] c = new char[7];
        long hash;
        static int len;
        int id;

        @Override
        public int compareTo(Element o) {
//            for (int i = 0; i < len; i++) {
//                int cc = c[i] - o.c[i];
//                if (cc != 0) return cc;
//            }
//            return 0;
            return Long.compare(hash, o.hash);
        }
    }
}
