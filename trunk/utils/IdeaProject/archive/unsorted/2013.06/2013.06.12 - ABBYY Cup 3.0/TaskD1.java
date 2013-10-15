package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;

public class TaskD1 {

    static final int MOD = 1000000007;

    static int get(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (int) ((dp[i - 1] + (long) (i - 1) * dp[i - 2]) % MOD);
        }
        return dp[n];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int ones = 0;
        for (int i : a) {
            if (i == 1) {
                ones++;
            }
        }
        long dp = get(ones);
        for (int i = ones + 1; i <= n; i++) {
            dp = dp * i % MOD;
        }
        out.println(dp);
//        if (dp != solve(a)) {
//            throw new AssertionError();
//        }
//        for (int ones = 8; ones <= 8; ones++) {
//            for (int n = ones; n <= 8; n++) {
//                int twos = n - ones;
//                int[] a = new int[n];
//                for (int i = 0; i < n; i++) {
//                    a[i] = i < ones ? 1 : 2;
//                }
//                System.out.println(n + " " + ones + " " + solve(a));
//            }
//        }
//        int n = in.nextInt();
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = in.nextInt();
//        }
//        out.println(solve(a));
    }

    static class State {
        int[] a;

        State(int[] a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (!Arrays.equals(a, state.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }

    static HashSet<State> state;
    static int[] p;
    static int[] a;

    static void go() {
        state.add(new State(p.clone()));
        for (int i = 0; i < p.length; i++) {
            if (a[i] == 0) continue;
            for (int j = i + 1; j < p.length; j++) {
                if (a[j] == 0) continue;
                --a[i];
                --a[j];
                int t = p[i];
                p[i] = p[j];
                p[j] = t;
                go();
                t = p[i];
                p[i] = p[j];
                p[j] = t;
                ++a[i];
                ++a[j];
            }
        }
    }

    static int solve(int[] a) {
        TaskD1.a = a;
        state = new HashSet<>();
        p = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            p[i] = i;
        }
        go();
        return state.size();
    }
}
