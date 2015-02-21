package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
//    static long[] F;
//    static final int N = 55;
//
//    static {
//        F = new long[N];
//        F[0] = 1;
//        for (int i = 1; i < N; i++) {
//            long bad = (1L << (i + 1 >> 1));
//            if ((i & 1) == 0) {
//                bad += (1L << (i >> 1));
//            }
//            F[i] = (((1L << i) - bad) >> 2) + (bad >> 1);
//        }
//    }

    static long getCountWithPrefix(int[] c, int prefix) {
        if (c[0] == '1') return 0;
        int n = c.length;
        long ans = 0;
//        System.out.println(Arrays.toString(c) + " " + prefix);
        for (int last = 0; last < 2; last++) {
            loop:
            for (int same = 1; same <= n; same++) {
                int[] cur = c.clone();
//                System.out.println("same = " + same);
                long ways = 1;
                for (int i = 0; i < same; i++) {
                    if (cur[i] < 0) {
                        cur[i] = 0;
                        ways *= 2;
                    }
                    int put = last ^ cur[i];
                    if (cur[n - i - 1] >= 0 && cur[n - i - 1] != put) continue loop;
                    cur[n - i - 1] = put;
                    if ((last ^ cur[i]) != cur[n - i - 1]) continue loop;
                }
                if (same == n) {
                    ans += ways;
//                    System.out.println(last + " " + same + " " + Arrays.toString(cur) + " " + ways);
                    continue;
                }
                if (cur[same] >= 0 && cur[same] != 0) continue;
                if (cur[n - same - 1] >= 0 && cur[n - same - 1] != (last ^ 1)) continue;
                cur[same] = 0;
                cur[n - same - 1] = (last ^ 1);
                if (cur[same] >= 0 && cur[same] != 0) continue;
                if (cur[n - same - 1] >= 0 && cur[n - same - 1] != (last ^ 1)) continue;
                for (int i = 0; i < n; i++) if (cur[i] < 0) ways *= 2;
                ans += ways;
//                System.out.println(last + " " + same + " " + Arrays.toString(cur) + " " + ways);
            }
        }
//        System.out.println("ans = " + ans);
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        if (getCountWithPrefix(ans, 1) <= k) {
            out.println(-1);
            return;
        }
        for (int prefix = 1; prefix < n; prefix++) {
            boolean found = false;
            for (int put = 0; put < 2; put++) {
                ans[prefix] = put;
                long count = getCountWithPrefix(ans, prefix + 1);
                if (count <= k) {
                    k -= count;
                } else {
                    found = true;
                    break;
                }
            }
            if (!found) throw new AssertionError();
        }
        for (int i : ans) out.print(i);
        out.println();
    }
}
