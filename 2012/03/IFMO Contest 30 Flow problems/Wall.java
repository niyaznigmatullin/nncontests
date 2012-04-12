package mypackage;

import graphalgorithms.DinicGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import javax.crypto.Mac;
import java.util.*;

public class Wall {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int w = in.nextInt();
        Set<Integer> all = new HashSet<Integer>();
        all.add(w);
        all.add(0);
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = in.nextInt();
            right[i] = in.nextInt();
            all.add(left[i]);
            all.add(right[i]);
        }
        int[] xs = new int[all.size()];
        int count = 0;
        for (int i : all) {
            xs[count++] = i;
        }
        Arrays.sort(xs);
        for (int i = 0; i < n; i++) {
            left[i] = Arrays.binarySearch(xs, left[i]);
            right[i] = Arrays.binarySearch(xs, right[i]);
        }
        DinicGraph g = new DinicGraph(all.size() * 2 - 2);
        for (int i = 0; i < n; i++) {
            if (left[i] == 0) {
                continue;
            }
            left[i] = left[i] * 2;
        }
        for (int i = 0; i < n; i++) {
            if (right[i] == all.size() - 1) {
                right[i] = all.size() * 2 - 3;
                continue;
            }
            right[i] = right[i] * 2 - 1;
        }
        for (int i = 1; i < all.size() - 1; i++) {
            g.addEdge(i * 2 - 1, i * 2, 0, 1);
        }
        Map<DinicGraph.Edge, Integer> map = new HashMap<DinicGraph.Edge, Integer>();
        for (int i = 0; i < n; i++) {
            map.put(g.addEdge(left[i], right[i], 0, 1), i);
        }
        long answer = g.getMaxFlow(0, all.size() * 2 - 3);
        List<List<DinicGraph.Edge>> ans = g.decompose(0, all.size() * 2 - 3);
        out.println(answer);
        for (List<DinicGraph.Edge> f : ans) {
            for (DinicGraph.Edge e : f) {
                if (!map.containsKey(e)) {
                    continue;
                }
                out.print(map.get(e) + 1 + " ");
            }
            out.println();
        }
	}
}
