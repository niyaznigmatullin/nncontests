package coding;

import java.util.*;

public class ForkliftTruckOperator {

    static class State {
        int[] a;

        public State(int[] a) {
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

    static Map<State, Integer> d;

    static int go(State state) {
        int[] a = state.a;
        if (a.length == 1 && a[0] <= 1) return 0;
        if (d.containsKey(state)) {
            return d.get(state);
        }
        int ret = Integer.MAX_VALUE / 2;
        {
            int sum = 0;
            for (int i : a) sum += i;
            if (a[0] * 2 >= sum && sum % 2 == 0) {
                int[] b = a.clone();
                b[0] -= sum / 2;
                if (b[0] == 0) b = Arrays.copyOfRange(b, 1, b.length);
                ret = Math.min(ret, go(new State(b)) + 1);
            }
            if (a[a.length - 1] * 2 >= sum && sum % 2 == 0) {
                int[] b = a.clone();
                int last = b.length - 1;
                b[last] -= sum / 2;
                if (b[last] == 0) b = Arrays.copyOf(b, b.length - 1);
                ret = Math.min(ret, go(new State(b)) + 1);
            }
        }
        if (a.length == 5) {
            if (a[1] == a[3]) {
                int[] b = new int[]{a[0], a[1], a[2] + a[4]};
                ret = Math.min(ret, go(new State(b)) + 1);
                b = new int[]{a[0] + a[2], a[1], a[4]};
                ret = Math.min(ret, go(new State(b)) + 1);
            }
        }
        if (a.length == 4) {
            if (a[0] == a[2]) {
                int[] b = new int[]{a[0], a[1] + a[3]};
                ret = Math.min(ret, go(new State(b)) + 1);
                b = new int[]{a[1], a[0], a[3]};
                ret = Math.min(ret, go(new State(b)) + 1);
            }
            if (a[1] == a[3]) {
                int[] b = new int[]{a[0], a[1], a[2]};
                ret = Math.min(ret, go(new State(b)) + 1);
                b = new int[]{a[0] + a[2], a[1]};
                ret = Math.min(ret, go(new State(b)) + 1);
            }
        }
        if (a.length == 3) {
            if (a[1] % 2 == 0) {
                int[] b = new int[]{a[0], a[1] / 2, a[2]};
                ret = Math.min(ret, go(new State(b)) + 1);
            }
            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < 3; j++) {
                    if (a[i] == a[j]) {
                        int[] b = new int[]{a[i], a[i ^ j ^ (1 ^ 2)]};
                        ret = Math.min(ret, go(new State(b)) + 1);
                    }
                }
            }
        }
        if (a.length == 2) {
            if (a[0] % 2 == 0) {
                int[] b = new int[]{a[0] / 2, a[1]};
                ret = Math.min(ret, go(new State(b)) + 1);
            }
            if (a[1] % 2 == 0) {
                int[] b = new int[]{a[0], a[1] / 2};
                ret = Math.min(ret, go(new State(b)) + 1);
            }
            for (int i = 0; i < 2; i++) {
                if (a[i] > a[i ^ 1]) {
                    int[] b = new int[]{a[i] - a[i ^ 1], a[i ^ 1]};
                    ret = Math.min(ret, go(new State(b)) + 1);
                }
            }
        }
        if (a.length == 1) {
            if (a[0] % 2 == 0) {
                int[] b = new int[]{a[0] / 2};
                ret = Math.min(ret, go(new State(b)) + 1);
            } else {
                for (int k = 1; k * 2 < a[0]; k++) {
                    int[] b = new int[]{k, a[0] - k * 2};
                    ret = Math.min(ret, go(new State(b)) + 1);
                }
            }
        }
        d.put(state, ret);
        return ret;
    }

    public int getNumber(String pallets) {
        d = new HashMap<>();
        List<Integer> f = new ArrayList<>();
        for (int i = 0; i < pallets.length(); ) {
            int j = i;
            while (j < pallets.length() && pallets.charAt(i) == pallets.charAt(j)) {
                ++j;
            }
            f.add(j - i);
            i = j;
        }
        int[] a = new int[f.size()];
        for (int i = 0; i < f.size(); i++) a[i] = f.get(i);
        int ret = go(new State(a));
        return ret == Integer.MAX_VALUE / 2 ? -1 : ret;
    }
}
