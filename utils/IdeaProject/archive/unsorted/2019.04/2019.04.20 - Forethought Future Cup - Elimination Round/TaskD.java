package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int mx = Math.min(m, 4 * (a + b));
        final int[] d = new int[mx + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        TreeSet<Integer> q = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int c = Integer.compare(d[o1], d[o2]);
                if (c != 0) return c;
                return Integer.compare(o1, o2);
            }
        });
        q.add(0);
        int[] ans = new int[mx + 1];
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            ans[d[v]]++;
            if (v - b >= 0 && d[v - b] == Integer.MAX_VALUE) {
                d[v - b] = d[v];
                q.add(v - b);
            }
            if (v + a <= mx && d[v + a] == Integer.MAX_VALUE) {
                d[v + a] = Math.max(d[v], v + a);
                q.add(v + a);
            }
        }
        long answer = 0;
        long current = 0;
        for (int i = 0; i <= mx; i++) {
            current += ans[i];
            answer += current;
        }
//        if (mx == 4 * (a + b) && mx / MathUtils.gcd(a, b) + 1 != current) throw new AssertionError();
        int i = mx + 1;
        int g = MathUtils.gcd(a, b);
        while (i <= m && i % g != 0) {
            answer += i / g + 1;
            ++i;
        }
        if (i <= m) {
            int last = m / g * g;
            int from = i / g + 1;
            int to = last / g + 1;
            answer += ((long) to * (to - 1) - (long) from * (from - 1)) / 2L * g;
            while (last <= m) {
                answer += last / g + 1;
                last++;
            }
        }
        out.println(answer);
    }
}
