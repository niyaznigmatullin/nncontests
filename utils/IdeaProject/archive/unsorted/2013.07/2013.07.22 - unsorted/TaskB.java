package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskB {

    final int[] DX = {1, 0, -1, 0};
    final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        for (int n = 1; n <= 3; n++) {
            for (int m = 1; m <= 3; m++) {
                Set<State> set = new HashSet<>();
                Queue<State> q = new ArrayDeque<>();
                byte[][] a = new byte[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        a[i][j] = (byte)(i * m + j);
                    }
                }
                State init = new State(a);
                q.add(init);
                set.add(init);
                while (!q.isEmpty()) {
                    State state = q.poll();
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            if (state.a[i][j] < 0) continue;
                            for (int dir = 0; dir < 4; dir++) {
                                int x = i + DX[dir];
                                int y = j + DY[dir];
                                if (x < 0 || y < 0 || x >= n || y >= m) continue;
                                byte[][] b = state.a.clone();
                                for (int e = 0; e < b.length; e++) b[e] = b[e].clone();
                                b[x][y] = b[i][j];
                                b[i][j] = -1;
                                State ns = new State(b);
                                if (!set.contains(ns)) {
                                    set.add(ns);
                                    q.add(ns);
                                }
                            }
                        }
                    }
                }
                System.out.println(n + " " + m + " " + set.size());
            }
        }
    }

    static class State {
        byte[][] a;

        State(byte[][] a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State st = (State) o;
            return Arrays.deepEquals(a, st.a);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(a);
        }
    }
}
