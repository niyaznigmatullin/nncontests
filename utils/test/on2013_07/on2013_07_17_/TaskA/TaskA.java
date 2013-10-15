package lib.test.on2013_07.on2013_07_17_.TaskA;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long sum = 0;
        HashMap<Integer, Long> map = new HashMap<>();
        HashMap<Integer, Integer> last = new HashMap<>();
        long ans = Long.MIN_VALUE;
        int ansI = -1;
        int ansJ = -1;
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            int x = a[i];
            Long z = map.get(x);
            if (z != null) {
                long cur = sum + x + x - z;
                if (x >= 0) {
                    sum += x;
                }
                if (cur > ans) {
                    ans = cur;
                    ansI = last.get(x);
                    ansJ = i;
                }
            } else {
                if (x >= 0) {
                    sum += x;
                }
                map.put(x, sum);
                last.put(x, i);
            }
        }
        boolean[] cut = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) {
                cut[i] = true;
            }
        }
        cut[ansI] = false;
        cut[ansJ] = false;
        for (int i = 0; i < ansI; i++) cut[i] = true;
        for (int i = ansJ + 1; i < n; i++) cut[i] = true;
        int count = 0;
        for (int i = 0; i < n; i++) if (cut[i]) ++count;
        out.println(ans + " " + count);
        for (int i = 0; i < n; i++) {
            if (cut[i]) {
                out.print(i + 1 + " ");
            }
        }
    }
}
