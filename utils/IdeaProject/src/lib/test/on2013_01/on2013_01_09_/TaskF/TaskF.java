package lib.test.on2013_01.on2013_01_09_.TaskF;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        Element[] a = new Element[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Element(in.nextInt(), in.nextInt());
        }
        Arrays.sort(a, new Comparator<Element>() {
            public int compare(Element o1, Element o2) {
                return o1.b - o2.b;
            }
        });
        int[] dp = new int[k + 1];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += a[i].a;
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int bestAnswer = 0;
        for (int i = 0; i < n; i++) {
            int dif = a[i].b - a[i].a;
            for (int j = 0; j <= k; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                bestAnswer = Math.min(bestAnswer, dp[j] - j);
                if (k - j >= dif) {
                    int maximal = Math.min(k - j - dif, a[i].a);
                    bestAnswer = Math.min(bestAnswer, dp[j] - j - maximal);
                }
            }
            for (int j = k; j >= a[i].b; j--) {
                int val = dp[j - a[i].b];
                if (val == Integer.MAX_VALUE) {
                    continue;
                }
                dp[j] = Math.min(dp[j], val + dif);
            }
        }
        out.println(bestAnswer + answer);
    }

    static class Element {
        int a;
        int b;

        Element(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
