package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int w = in.nextInt();
        int n = in.nextInt();
        Shop[] s = new Shop[n];
        for (int i = 0; i < n; i++) {
            s[i] = new Shop(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(s, new Comparator<Shop>() {
            public int compare(Shop o1, Shop o2) {
                return o1.cost - o2.cost;
            }
        });
        int[] dp = new int[w + 1];
        int[] next = new int[w + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (Shop e : s) {
            System.arraycopy(dp, 0, next, 0, w + 1);
            int pay = e.count * e.cost + e.entrance;
            for (int i = 0; i <= w; i++) {
                int val = dp[i];
                if (val == Integer.MAX_VALUE) {
                    continue;
                }
                if (i + e.count <= w) {
                    next[i + e.count] = Math.min(next[i + e.count], val + pay);
                } else {
                    next[w] = Math.min(next[w], val + e.entrance + (w - i) * e.cost);
                }
            }
            int[] t = dp;
            dp = next;
            next = t;
        }
        out.println(dp[w]);
    }

    static class Shop {
        int count;
        int cost;
        int entrance;

        Shop(int count, int cost, int entrance) {
            this.count = count;
            this.cost = cost;
            this.entrance = entrance;
        }
    }
}
