package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskD {

    static class State {
        int letter;
        long sum;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (letter != state.letter) return false;
            if (sum != state.sum) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = letter;
            result = 31 * result + (int) (sum ^ (sum >>> 32));
            return result;
        }

        public State(int letter, long sum) {

            this.letter = letter;
            this.sum = sum;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Map<State, Integer> map = new HashMap<>();
        int[] x = new int[26];
        for (int i = 0; i < 26; i++) {
            x[i] = in.nextInt();
        }
        long sum = 0;
        String s = in.next();
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            State cur = new State(c, sum);
            Integer got = map.get(cur);
            if (got == null) got = 0;
            ans += got;
            sum += x[c];
            cur = new State(c, sum);
            got = map.get(cur);
            if (got == null) got = 0;
            map.put(cur, got + 1);
        }
        out.println(ans);
    }
}
