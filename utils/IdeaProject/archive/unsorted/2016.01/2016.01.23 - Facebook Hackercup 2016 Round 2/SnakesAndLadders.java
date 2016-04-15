package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class SnakesAndLadders {

    final int MOD = 1000000007;

    int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    int sub(int a, int b) {
        return add(a, MOD - b);
    }


    static class Element {
        int x;
        int h;

        public Element(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int n = in.nextInt();
        Element[] h = new Element[n + 1];
        for (int i = 0; i < n; i++) {
            h[i] = new Element(in.nextInt(), in.nextInt());
        }
        h[n] = new Element(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Arrays.sort(h, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.compare(o1.x, o2.x);
            }
        });
        int[] st = new int[n];
        int cn = 0;
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            int sum = 0;
            int sum2 = 0;
            int count = 0;
            int curh = Integer.MAX_VALUE;
            while (cn > 0 && h[st[cn - 1]].h < h[i].h) {
                --cn;
                if (h[st[cn]].h != curh) {
                    sum = 0;
                    sum2 = 0;
                    count = 0;
                }
                int x1 = h[st[cn]].x;
                int x2 = mul(x1, x1);
                curh = h[st[cn]].h;
                ans = add(ans, sum2);
                ans = sub(ans, mul(2, mul(sum, x1)));
                ans = add(ans, mul(count, x2));
                sum2 = add(sum2, x2);
                sum = add(sum, x1);
                count++;
            }
            st[cn++] = i;
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
