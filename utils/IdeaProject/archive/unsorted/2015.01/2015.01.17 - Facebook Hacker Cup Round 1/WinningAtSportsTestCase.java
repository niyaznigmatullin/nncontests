package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WinningAtSportsTestCase implements TestProvider {

    static class Solver {

        static int go(int x, int y, int a, int b) {
            if (x > a || y > b) return 0;
            if (x + y > 0 && x <= y) return 0;
            if (x == a && y == b) {
                return 1;
            }
            return go(x + 1, y, a, b) + go(x, y + 1, a, b);
        }

        static int solve1(int a, int b) {
            return go(0, 0, a, b);
        }

        static int go2(int x, int y, int a, int b) {
            if (x > y && y != b) return 0;
            if (x > a || y > b) return 0;
            if (x == a && y == b) return 1;
            return go2(x + 1, y, a, b) + go2(x, y + 1, a, b);
        }

        static int solve2(int a, int b) {
            return go2(0, 0, a, b);
        }
    }

    static Test getTest1() {
        StringBuilder sb = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        int count = 0;
        final int MAXN = 10;
        for (int i = 1; i < MAXN; i++) {
            for (int j = 0; j < i; j++) {
                ++count;
            }
        }
        sb.append(count).append('\n');
        count = 0;
        for (int i = 1; i < MAXN; i++) {
            for (int j = 0; j < i; j++) {
                ++count;
                answer.append("Case #").append(count).append(": ").append(Solver.solve1(i, j)).append(' ').append(Solver.solve2(i, j)).append('\n');
                sb.append(i).append('-').append(j).append('\n');
            }
        }
        return new Test(sb.toString(), answer.toString());
    }

    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        ret.add(getTest1());
        return ret;
    }
}
