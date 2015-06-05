package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Tropes2 {

    static class State {
        int[] x;

        State(int[] x) {
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (!Arrays.equals(x, state.x)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return x != null ? Arrays.hashCode(x) : 0;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Running on test #" + testNumber);
        int n = in.nextInt();
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            a[i] = in.readIntArray(m);
        }
        final Map<State, Integer> d = new HashMap<>();
        int[] xs = new int[n];
        Arrays.fill(xs, -1);
        State start = new State(xs);
        Queue<State> queue = new ArrayDeque<>();
//        (123456, new Comparator<State>() {
//            @Override
//            public int compare(State o1, State o2) {
//                return -Integer.compare(d.get(o1), d.get(o2));
//            }
//        });
        d.put(start, 0);
        queue.add(start);
        int ans = 0;
        while (!queue.isEmpty()) {
            State state = queue.poll();
            int dd = d.get(state);
            ans = Math.max(ans, dd);
            all: for (int i = state.x[0] + 1; i < a[0].length; i++) {
                int[] y = new int[n];
                y[0] = i;
                for (int j = 1; j < n; j++) {
                    y[j] = state.x[j] + 1;
                    while (y[j] < a[j].length && a[j][y[j]] != a[0][y[0]]) {
                        ++y[j];
                    }
                    if (y[j] >= a[j].length) continue all;
                }
                State nState = new State(y);
                if (!d.containsKey(nState) || d.get(nState) < dd + 1) {
                    d.put(nState, dd + 1);
                    queue.add(nState);
                }
            }
        }
        out.println(ans);
    }
}
