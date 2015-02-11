package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class CorporateGiftingTestCase implements TestProvider {

    static final Random RNG = new Random(2133L);

    static int[] genGraph() {
        int n = RNG.nextInt(15) + 1;
        return genGraph(n);
    }

    private static int[] genGraph(int n) {
        int[] p = new int[n];
        int[] permutation = new int[n];
        for (int i = 1; i < n; i++) {
            int j = RNG.nextInt(i);
            permutation[i] = permutation[j];
            permutation[j] = i;
        }
        p[permutation[0]] = -1;
        for (int i = 1; i < n; i++) {
            p[permutation[i]] = permutation[RNG.nextInt(i)];
        }
        return p;
    }

    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        if (true) return ret;
        for (int it = 0; it < 100; it++) {
            int tCount = 30;
            StringBuilder input = new StringBuilder();
            StringBuilder answer = new StringBuilder();
            input.append(tCount).append('\n');
            for (int i = 0; i < tCount; i++) {
                int[] graph = genGraph();
                input.append(graph.length).append('\n');
                for (int j = 0; j < graph.length; j++) {
                    input.append(graph[j] + 1).append(' ');
                }
                input.append('\n');
                answer.append("Case #").append(i + 1).append(": ").append(Solver.solve(graph)).append('\n');
            }
            ret.add(new Test(input.toString(), answer.toString()));
        }
        for (int it = 0; it < 3; it++) {
            StringBuilder input = new StringBuilder();
            input.append(1).append('\n');
            int[] graph = genGraph(200000);
            input.append(graph.length).append('\n');
            for (int j = 0; j < graph.length; j++) {
                input.append(graph[j] + 1).append(' ');
            }
            input.append('\n');
            ret.add(new Test(input.toString()));
        }
        return ret;
    }

    static class Solver {

        static List<Integer>[] edges;

        static void go(int v, int n, int sum) {
            if (sum >= ans) return;
            if (v == n) {
                ans = sum;
                return;
            }
            for (int c = 0; c < n; c++) {
                color[v] = c;
                boolean bad = false;
                for (int i : edges[v]) {
                    if (color[i] >= 0 && color[i] == color[v]) {
                        bad = true;
                        break;
                    }
                }
                if (bad) continue;
                go(v + 1, n, sum + c);
            }
        }

        static int[] color;
        static int ans;

        static int solve(int[] p) {
            int n = p.length;
            edges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < p.length; i++) {
                if (p[i] >= 0) {
                    edges[p[i]].add(i);
                    edges[i].add(p[i]);
                }
            }
            color = new int[n];
            Arrays.fill(color, -1);
            ans = Integer.MAX_VALUE;
            go(0, p.length, 0);
            return p.length + ans;
        }
    }
}
