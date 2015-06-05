package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Bilingual {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        String line = in.readLine();
        while (line.isEmpty()) line = in.readLine();
        int n = Integer.parseInt(line);
        Map<String, Integer> q = new HashMap<>();
        int[][] f = new int[n][];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            List<Integer> z = new ArrayList<>();
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                Integer got = q.get(word);
                if (got == null) got = q.size();
                q.put(word, got);
                z.add(got);
            }
            f[i] = ArrayUtils.toPrimitiveArrayInteger(z);
        }
        int words = q.size();
        DinicGraph g = new DinicGraph(words * 2 + 2);
        int src = words * 2;
        int tar = src + 1;
        for (int i = 0; i < words; i++) g.addEdge(i, i + words, 1);
        final int INF = 300;
        for (int i : f[0]) {
            g.addEdge(src, i, INF);
        }
        for (int i : f[1]) {
            g.addEdge(i + words, tar, INF);
        }
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < f[i].length; j++) {
                for (int k = j + 1; k < f[i].length; k++) {
                    g.addEdge(f[i][j] + words, f[i][k], INF);
                    g.addEdge(f[i][k] + words, f[i][j], INF);
                }
            }
        }
        out.println("Case #" + testNumber + ": " + g.getMaxFlow(src, tar));
    }
}
