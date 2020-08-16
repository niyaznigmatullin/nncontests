package coding;

import java.util.*;

public class PurpleSubsequences {

    static class State {
        int[] a;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Arrays.equals(a, state.a);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }

        public State(int[] a) {
            this.a = a;
        }
    }

    static Map<State, Integer> allStates;
    static List<State> listStates;

    static void genAll(int x, int len, int last, int[] cur) {
        if (x == len) {
            State state = new State(cur.clone());
            allStates.put(state, allStates.size());
            listStates.add(state);
            return;
        }
        for (int i = last; i <= INF; i++) {
            cur[x] = i;
            genAll(x + 1, len, i, cur);
        }
    }

    static final int INF = 21;

    public long count(int[] A, int L) {
        allStates = new HashMap<>();
        listStates = new ArrayList<>();
        genAll(0, L, 1, new int[L]);
        long[][] count = new long[INF][allStates.size()];
        long[] allCount = new long[allStates.size()];
        int[] start = new int[L];
        Arrays.fill(start, INF);
        int startState = allStates.get(new State(start));
        count[INF - 1][startState]++;
        allCount[startState]++;
        for (int e : A) {
            long[] newDCount = new long[allStates.size()];
            for (int i = 0; i < allCount.length; i++) {
                long cur = allCount[i];
                if (cur == 0) continue;
                int nState = addState(i, e);
                newDCount[nState] += cur;
            }
            for (int i = 0; i < allCount.length; i++) {
                allCount[i] -= count[e - 1][i];
                allCount[i] += newDCount[i];
            }
            count[e - 1] = newDCount;
        }
        long ans = 0;
        for (Map.Entry<State, Integer> e : allStates.entrySet()) {
            if (e.getKey().a[L - 1] != INF) {
                ans += allCount[e.getValue()];
            }
        }
        return ans;
    }

    static int addState(int old, int add) {
        int[] f = listStates.get(old).a.clone();
        int pos = 0;
        while (pos < f.length && f[pos] < add) pos++;
        if (pos < f.length) f[pos] = add;
        return allStates.get(new State(f));
    }
}
