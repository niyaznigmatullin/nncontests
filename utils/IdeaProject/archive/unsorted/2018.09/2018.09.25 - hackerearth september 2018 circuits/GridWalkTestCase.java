package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class GridWalkTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        Random rng = new Random(12323L);
        for (int it = 0; it < 10; it++) {
            int n = 5;
            int m = 6;
            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < m; j++) {
                    if (rng.nextBoolean()) {
                        cur++;
                    }
                    a[i][j] = cur;
                }
            }
            int[][] left = new int[n - 1][m];
            int[][] right = new int[n - 1][m];
            for (int i = 0; i + 1 < n; i++) {
                int cur = 0;
                for (int j = 0; j < m; j++) {
                    cur = randRange(rng, cur, m);
                    left[i][j] = cur;
                }
                cur = 0;
                for (int j = 0; j < m; j++) {
                    if (j + 1 < m) cur = Math.max(cur, left[i][j + 1] - 1);
                    cur = Math.max(cur, left[i][j]);
                    cur = randRange(rng, cur, m);
                    right[i][j] = cur;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(n).append(' ').append(m).append('\n');
            printArray(a, sb, 0);
            printArray(left, sb, 1);
            printArray(right, sb, 1);
            ret.add(new Test(sb.toString(), new StupidSolver(a, left, right).solve() + ""));
        }
        return ret;
    }

    private void printArray(int[][] a, StringBuilder sb, int add) {
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j > 0) sb.append(' ');
                sb.append(a[i][j] + add);
            }
            sb.append('\n');
        }
    }

    static class StupidSolver {
        int[][] a;
        int[][] left;
        int[][] right;
        int n;
        int m;
        Set<Answer> set;

        static class Answer {
            int[] a;

            public Answer(int[] a) {
                this.a = a;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Answer answer = (Answer) o;
                return Arrays.equals(a, answer.a);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(a);
            }
        }

        public StupidSolver(int[][] a, int[][] left, int[][] right) {
            this.a = a;
            this.left = left;
            this.right = right;
            n = a.length;
            m = a[0].length;
            set = new HashSet<>();
        }

        void go(int x, int y, int[] v) {
            v[x] = a[x][y];
            if (x == n - 1) {
                set.add(new Answer(v.clone()));
                return;
            }
            for (int i = left[x][y]; i <= right[x][y]; i++) {
                go(x + 1, i, v);
            }
        }

        int solve() {
            int[] v = new int[n];
            for (int i = 0; i < m; i++) {
                go(0, i, v);
            }
            return set.size();
        }
    }


    static int randRange(Random rng, int left, int right) {
        return rng.nextInt(right - left) + left;
    }
}
