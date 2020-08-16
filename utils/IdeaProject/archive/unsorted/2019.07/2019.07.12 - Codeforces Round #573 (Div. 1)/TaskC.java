package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        char[] c = in.next().toCharArray();
        NavigableSet<Integer> ones = new TreeSet<>();
        NavigableSet<Integer> zeros = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (c[i] == '0') zeros.add(i);
            else ones.add(i);
        }
        if (ones.isEmpty() || zeros.isEmpty() || ones.last() - ones.first() + 1 <= k || zeros.last() - zeros.first() + 1 <= k) {
            out.println("tokitsukaze");
            return;
        }
        if (check(ones, n, k) && check(zeros, n, k)) {
            out.println("quailty");
            return;
        }
        out.println("once again");
    }

    private boolean check(NavigableSet<Integer> ones, int n, int k) {
        for (int i = 0; i + k <= n; i++) {
            int left = ones.first();
            int right = ones.last();
            if (left >= i) {
                left = ones.higher(i + k - 1);
            }
            if (right < i + k) {
                right = ones.lower(i);
            }
            if (right - left + 1 > k) {
                return false;
            }
        }
        return true;
    }


}
