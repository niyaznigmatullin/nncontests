package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        final int[] a = in.readIntArray(n);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum < k) {
            out.println(-1);
            return;
        }
        if (sum == k) {
            return;
        }
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return a[o1] - a[o2];
            }
        });
        int last = 0;
        for (int man = 0; man < n; ) {
            int next = man;
            while (next < n && a[id[man]] == a[id[next]]) {
                ++next;
            }
            int cur = a[id[man]];
            if (k >= (long) (n - man) * (cur - last)) {
                k -= (long) (n - man) * (cur - last);
            } else {
                long howMany = k / (n - man);
                int day = (int) (last + howMany + 1);
                k %= n - man;
                int i = 0;
                while (k > 0) {
                    if (a[i] >= day) {
                        --k;
                    }
                    ++i;
                }
                for (int j = i; j < n; j++) {
                    if (a[j] >= day) {
                        out.print(j + 1 + " ");
                    }
                }
                for (int j = 0; j < i; j++) {
                    if (a[j] > day) {
                        out.print(j + 1 + " ");
                    }
                }
                return;
            }
            man = next;
            last = cur;
        }
    }
}
