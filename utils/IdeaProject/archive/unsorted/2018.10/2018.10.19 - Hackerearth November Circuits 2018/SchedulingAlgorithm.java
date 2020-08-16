package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class SchedulingAlgorithm {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int L = in.nextInt();
        int[][] a = in.readInt2DArray(n, L);
        NavigableSet<Suffix> d = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            d.add(new Suffix(a[i], i));
        }
        boolean first = true;
        while (!d.isEmpty()) {
            Suffix v = d.pollFirst();
            if (first) {
                first = false;
            } else {
                out.print(' ');
            }
            out.print(v.value[v.pos]);
            v.pos++;
            if (v.pos < v.value.length) {
                d.add(v);
            }
        }
        out.println();
    }

    static class Suffix implements Comparable<Suffix> {
        static final int P = 239017;
        static final int MAXN = 100000;
        static final long[] POWS = new long[MAXN];
        static {
            POWS[0] = 1;
            for (int i = 1; i < MAXN; i++) {
                POWS[i] = POWS[i - 1] * P;
            }
        }
        int[] value;
        long[] hashes;
        int pos;
        int id;

        public Suffix(int[] value, int id) {
            this.value = value;
            this.id = id;
            this.hashes = new long[value.length];
            this.pos = 0;
            long h = 0;
            for (int i = 0; i < hashes.length; i++) {
                h = h * P + value[i];
                hashes[i] = h;
            }
        }

        long getHash(int left, int right) {
            long ret = hashes[right - 1];
            if (left > 0) {
                ret -= hashes[left - 1] * POWS[right - left];
            }
            return ret;
        }

        @Override
        public int compareTo(Suffix o) {
            int left = 0;
            int n1 = value.length;
            int n2 = o.value.length;
            int right = Math.min(n1 - pos, n2 - o.pos) + 1;
            while (left < right - 1) {
                if (value[pos + left] == o.value[o.pos + left]) {
                    left++;
                } else break;
            }
//            while (left < right - 1) {
//                int mid = (left + right) >>> 1;
//                if (getHash(pos, pos + mid) == o.getHash(o.pos, o.pos + mid)) {
//                    left = mid;
//                } else {
//                    right = mid;
//                }
//            }
            int pos1 = left + pos;
            int pos2 = left + o.pos;
            if (pos1 < n1 && pos2 < n2) {
                return Integer.compare(value[pos1], o.value[pos2]);
            } else if (pos1 == n1 && pos2 == n2) {
                return Integer.compare(id, o.id);
            } else if (pos1 == n1) return -1; else return 1;
         }
    }
}
