package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> words = new HashMap<>();
        List<int[]> edges = new ArrayList<>();
        List<String> allWords = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String first = in.next();
            String second = in.next();
            for (int x = 1; x <= first.length(); x++) {
                for (int y = 1; y <= second.length(); y++) {
                    String w = first.substring(0, x) + second.substring(0, y);
                    Integer got = words.get(w);
                    if (got == null) {
                        got = words.size();
                        words.put(w, got);
                        allWords.add(w);
                    }
                    edges.add(new int[]{i, got});
                }
            }
        }
        KuhnMatchingGraph g = new KuhnMatchingGraph(n, words.size());
        for (int[] e : edges) {
            g.addEdge(e[0], e[1]);
        }
        int maxMatching = g.getMaximalMatching();
        if (maxMatching < n) {
            out.println("No solution");
            return;
        }
        int[] p = g.getPaired1();
        for (int i = 0; i < n; i++) {
            out.println(allWords.get(p[i]));
        }
    }
}
