package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskA {
    static class Lamp {
        int a;
        int b;

        public Lamp(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Lamp[] a = new Lamp[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Lamp(in.nextInt(), in.nextInt());
        }
        Arrays.sort(a, new Comparator<Lamp>() {
            @Override
            public int compare(Lamp o1, Lamp o2) {
                return Integer.compare(o1.a, o2.a);
            }
        });
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int l = -1;
            int r = i;
            while (l < r - 1) {
                int mid = l + r >> 1;
                if (a[mid].a >= a[i].a - a[i].b) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            if (l < 0) {
                dp[i] = 1;
            } else {
                dp[i] = dp[l] + 1;
            }
        }
        int ans = 0;
        for (int i : dp) {
            ans = Math.max(ans, i);
        }
        out.println(n - ans);
    }
}
