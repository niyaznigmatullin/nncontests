package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Liquid {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() + 1;
        int[] a = new int[n];
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt();
        }
        int maxWater = a[n - 1];
        int[][] d = new int[maxWater + 1][maxWater + 1];
        Queue<State> q = new ArrayDeque<State>();
        Map<State, Integer> map = new HashMap<State, Integer>();
        State initState = new State(0, 0);
        q.add(initState);
        map.put(initState, 0);
        int need = in.nextInt();
        while (!q.isEmpty()) {
            State state = q.poll();
            int v = map.get(state);
            if (state.first == need || state.second == need) {
                out.println(v);
                return;
            }
            for (int i = 0; i < n; i++) {
                State newState = new State(a[i], state.second);
                enQueue(q, map, v, newState);
            }
            for (int i = 0; i < n; i++) {
                State newState = new State(a[i], state.first);
                enQueue(q, map, v, newState);
            }
            for (int i = 0; i < n; i++) {
                int willBe = state.first - a[i] + state.second;
                if (willBe > maxWater || willBe < 0) {
                    continue;
                }
                State newState = new State(a[i], willBe);
                enQueue(q, map, v, newState);
            }
        }
        out.println(0);
    }

    private void enQueue(Queue<State> q, Map<State, Integer> map, int v, State newState) {
        if (map.containsKey(newState)) {
            return;
        }
        map.put(newState, v + 1);
        q.add(newState);
    }

    static class State {
        int first;
        int second;

        State(int first, int second) {
            if (first > second) {
                int t = first;
                first = second;
                second = t;
            }
            this.first = first;
            this.second = second;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;

            State state = (State) o;

            if (first != state.first) return false;
            if (second != state.second) return false;

            return true;
        }

        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
}
