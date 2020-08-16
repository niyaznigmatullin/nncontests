package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class SortingPermutationsStupid {

    static class Permutation {
        int[] a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Permutation that = (Permutation) o;
            return Arrays.equals(a, that.a);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }

        public Permutation(int[] a) {
            this.a = a;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) a[i]--;
        Permutation start = new Permutation(a);
        Queue<Permutation> q = new ArrayDeque<>();
        q.add(start);
        Set<Permutation> was = new HashSet<>();
        was.add(start);
        int[] need = new int[n];
        for (int i = 0; i < n; i++) need[i] = i;
        while (!q.isEmpty()) {
            Permutation p = q.poll();
            for (int i = 0; i + 5 <= p.a.length; i++) {
                int[] b = p.a.clone();
                ArrayUtils.reverse(b, i, i + 5);
                Permutation np = new Permutation(b);
                if (was.add(np)) {
                    q.add(np);
                }
            }
        }
        out.println(was.size());
//        out.println(was.contains(new Permutation(need)) ? "yes" : "no");
    }
}
