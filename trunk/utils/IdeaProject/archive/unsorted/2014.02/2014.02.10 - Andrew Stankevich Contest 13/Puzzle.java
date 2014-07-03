package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Puzzle {

    static class State {
        int[] a;

        State(int[] a) {
            this.a = a.clone();
            Arrays.sort(this.a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;

            State state = (State) o;

            if (!Arrays.equals(a, state.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }

    static Map<State, Integer> map;

    static int go(State state) {
        if (map.containsKey(state)) return map.get(state);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < state.a.length; i++) {
            for (int j = 1; j < state.a[i]; j++) {
                int[] a1 = state.a.clone();
                int[] a2 = state.a.clone();
                a1[i] = j;
                a2[i] = state.a[i] - j;
                ans = Math.min(ans, 1 + Math.max(go(new State(a1)), go(new State(a2))));
            }
        }
        map.put(state, ans);
        return ans;
    }

    static int go2(int a, int b, int c) {
        return go2f(a) + go2f(b) + go2f(c);
    }

    static int go2f(int n) {
        int ret = 0;
        while (n > 1) {
            ret++;
            n = (n + 1) / 2;
        }
        return ret;
    }

    static int f(BigInteger n) {
        int ret = 0;
        while (n.compareTo(BigInteger.ONE) > 0) {
            ret++;
            n = n.add(BigInteger.ONE).shiftRight(1);
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        BigInteger a = new BigInteger(in.next());
        BigInteger b = new BigInteger(in.next());
        BigInteger c = new BigInteger(in.next());
        out.println(f(a) + f(b) + f(c));
//        map = new HashMap<>();
//        map.put(new State(new int[]{1, 1, 1}), 0);
//        for (int i = 1; i <= 10; i++) {
//            for (int j = i; j <= 10; j++) {
//                for (int k = j; k <= 10; k++) {
//                    System.err.println(i + " " + j + " " + k + " " + go(new State(new int[]{i, j, k})) + " " + go2(i, j, k));
//                }
//            }
//        }
    }
}
