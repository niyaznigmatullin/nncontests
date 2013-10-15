package lib.test.on2012_12.on2012_12_15_.E;



import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

import java.util.Arrays;
import java.util.Comparator;

public class E {

    static final int MOD = 1000000007;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][];
        int allLen = 1;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            a[i] = new int[s.length() + 1];
            for (int j = 0; j < s.length(); j++) {
                a[i][j] = s.charAt(j);
            }
            allLen += a[i].length;
        }
        Arrays.sort(a, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                for (int i = 0; i < o1.length; i++) {
                    if (o1[i] != o2[i]) {
                        return o1[i] - o2[i];
                    }
                }
                throw new AssertionError();
            }
        });
        int[] q = new int[allLen];
        int[] dep = new int[allLen];
        int head = 0;
        int tail = 1;
        q[head] = n - 1;
        int[] fact = new int[30];
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = (int) ((long) fact[i - 1] * i % MOD);
        }
        long ans = 1;
        while (head < tail) {
            int leftS = q[head] / n;
            int rightS = q[head] % n + 1;
            int d = dep[head];
            ++head;
            int letters = 0;
            while (leftS < rightS) {
                ++letters;
                int left = leftS;
                int right = rightS;
                while (left < right - 1) {
                    int mid = left + right >> 1;
                    if (a[mid][d] != a[leftS][d]) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
                if (right - leftS > 1) {
                    q[tail] = leftS * n + right - 1;
                    dep[tail] = d + 1;
                    ++tail;
                }
                leftS = right;
            }
            ans = ans * fact[letters] % MOD;
        }
        out.println(ans);
    }
}
