package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class Solid {

    static class State {
        int maskH;
        boolean gotIt;
        int row;
        int mask;

        State(int maskH, boolean gotIt, int row, int mask) {
            this.maskH = maskH;
            this.gotIt = gotIt;
            this.row = row;
            this.mask = mask;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;

            State state = (State) o;

            if (gotIt != state.gotIt) return false;
            if (mask != state.mask) return false;
            if (maskH != state.maskH) return false;
            if (row != state.row) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = maskH;
            result = 31 * result + (gotIt ? 1 : 0);
            result = 31 * result + row;
            result = 31 * result + mask;
            return result;
        }

        State dontPut() {
            if (((mask >> row) & 1) == 0) return null;
            return new State(maskH, gotIt, row + 1, mask & (~(1 << row)));
        }

        State putVertical() {
            if (row == 0 || ((mask >> row - 1) & 1) == 1 || ((mask >> row) & 1) == 0) return null;
            return new State(maskH | (1 << row - 1), gotIt, row + 1, mask | (1 << row) | (1 << row - 1));
        }

        State putHorizontal() {
            if (((mask >> row) & 1) == 1) return null;
            return new State(maskH, true, row + 1, mask | (1 << row));
        }

        State[] move() {
            return new State[]{dontPut(), putHorizontal(), putVertical()};
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Map<State, Long> map = new HashMap<>();
        map.put(new State(0, false, 0, (1 << n) - 1), 1L);
        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n; row++) {
                Map<State, Long> newMap = new HashMap<>();
                for (State e : map.keySet()) {
                    long val = map.get(e);
                    for (State f : e.move()) {
                        if (f == null) continue;
                        add(newMap, f, val);
                    }
                }
                map = newMap;
            }
            {
                Map<State, Long> newMap = new HashMap<>();
                for (State e : map.keySet()) {
                    long val = map.get(e);
                    if (col > 0 && !e.gotIt) continue;
                    add(newMap, new State(e.maskH, false, 0, e.mask), val);
                }
                map = newMap;
            }
        }
        long ans = 0;
        for (State e : map.keySet()) {
            if (e.maskH != ((1 << (n - 1)) - 1) || e.mask != ((1 << n) - 1)) {
                continue;
            }
            ans += map.get(e);
        }
        out.println(ans);
    }

    static void add(Map<State, Long> map, State a, long v) {
        if (v == 0) return;
        Long f = map.get(a);
        if (f == null) f = 0L;
        f += v;
        map.put(a, f);
    }
}
