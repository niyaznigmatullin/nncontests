package coding;

import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskD {
    static class Author {
        long left;
        long right;
        long w;
        int id;

        public Author(long left, long right, long w, int id) {
            this.left = left;
            this.right = right;
            this.w = w;
            this.id = id;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Author[] a = new Author[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Author(in.nextLong(), in.nextLong(), in.nextLong(), i);
        }
        Arrays.sort(a, new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return Long.compare(o1.right, o2.right);
            }
        });
        long A = in.nextLong();
        long B = in.nextLong();
        final long MAX = (long) 1e18;
        long l = -1;
        long r = MAX + 1;
        while (l < r - 1) {
            long mid = (l + r) >>> 1;
            long upTo = A;
            for (Author e : a) {
                if (e.w > mid) continue;
                if (e.left <= upTo) {
                    upTo = Math.max(upTo, e.right);
                }
            }
            if (upTo >= B) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (r > MAX) {
            out.println(-1);
            return;
        }
        List<Integer> ans = new ArrayList<>();
        for (Author e : a) {
            if (e.w > r) continue;
            ans.add(e.id);
        }
        Collections.sort(ans);
        out.println(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) out.print(' ');
            out.print(ans.get(i) + 1);
        }
        out.println();
    }
}
