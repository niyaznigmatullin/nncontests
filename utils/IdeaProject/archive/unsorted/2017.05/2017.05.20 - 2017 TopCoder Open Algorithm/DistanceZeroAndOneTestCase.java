package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class DistanceZeroAndOneTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        Random rng = new Random(123213L);
        List<NewTopCoderTest> ret = new ArrayList<>();
        for (int it = 0; it < 200; it++) {
            int n = rng.nextInt(15) + 2;
            boolean[][] edges = new boolean[n][n];
            double p = rng.nextDouble();
            for (int i = 0; i < n ;i++) {
                for (int j = i + 1; j < n; j++) {
                    edges[j][i] = edges[i][j] = rng.nextDouble() < p;
                }
            }
            int[] s = bfs(edges, 0);
            int[] t = bfs(edges, 1);
            boolean bad = false;
            for (int i = 0; i < n; i++) {
                if (s[i] == Integer.MAX_VALUE || t[i] == Integer.MAX_VALUE) {
                    bad = true;
                    break;
                }
            }
            if (bad) {
                --it;
                continue;
            }
            ret.add(new NewTopCoderTest(new Object[]{s, t}));
        }
        return ret;
    }

    static int[] bfs(boolean[][] edges, int start) {
        int n = edges.length;
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        q[tail++] = start;
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < n; i++) {
                if (edges[v][i] && d[i] == Integer.MAX_VALUE) {
                    d[i] = d[v] + 1;
                    q[tail++] = i;
                }
            }
        }
        return d;
    }
}
