package lib.test.on2013_09.on2013_09_14_Codeforces_Round__200__Div__1_.E___Pumping_Stations;



import ru.ifmo.niyaz.DataStructures.DisjointSetUnionFast;
import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        int[] cap = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            cap[i] = in.nextInt();
        }
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = 0;
        }
//        {
//            for (int v = 0; v < n; v++) {
//                for (int u = v + 1; u < n; u++) {
//                    DinicGraph g = new DinicGraph(n);
//                    for (int j = 0; j < m; j++) {
//                        g.addEdge(from[j], to[j], cap[j]);
//                        g.addEdge(to[j], from[j], cap[j]);
//                    }
//                    System.out.println(v + " " + u + " " + g.getMaxFlow(v, u));
//                }
//            }
//        }
        int colorsCount = 1;
        int[] ansFrom = new int[n];
        int[] ansTo = new int[n];
        int[] ansCap = new int[n];
        int edges = 0;
        while (true) {
            int[] nColors = new int[colorsCount];
            for (int i : color) {
                nColors[i]++;
            }
            int id = -1;
            for (int i = 0; i < colorsCount; i++) {
                if (nColors[i] > 1) {
                    id = i;
                    break;
                }
            }
            if (id < 0) {
                break;
            }
            int first = -1;
            int second = -1;
            for (int i = 0; i < n; i++) {
                if (color[i] == id) {
                    if (first < 0) {
                        first = i;
                    } else if (second < 0) {
                        second = i;
                    }
                }
            }
            int vCount = colorsCount + nColors[id];
            DinicGraph g = new DinicGraph(vCount);
            DisjointSetUnionFast dsu = new DisjointSetUnionFast(colorsCount);
            for (int i = 0; i < edges; i++) {
                if (ansFrom[i] == id || ansTo[i] == id) {
                    continue;
                }
                dsu.union(ansFrom[i], ansTo[i]);
            }
            int[] number = new int[n];
            int curNumber = colorsCount;
            for (int i = 0; i < n; i++) {
                if (color[i] == id) {
                    number[i] = curNumber++;
                } else {
                    number[i] = dsu.get(color[i]);
                }
            }
            for (int i = 0; i < m; i++) {
                g.addUndirectedEdge(number[from[i]], number[to[i]], cap[i]);
            }
            long flow = g.getMaxFlow(number[first], number[second]);
            boolean[] cut = g.getCut(number[first], number[second]);
            for (int i = 0; i < n; i++) {
                if (color[i] == id) {
                    if (!cut[number[i]]) {
                        color[i] = colorsCount;
                    }
                }
            }
            for (int i = 0; i < edges; i++) {
                if (ansFrom[i] == id) {
                    if (!cut[dsu.get(ansTo[i])]) {
                        ansFrom[i] = colorsCount;
                    }
                }
                if (ansTo[i] == id) {
                    if (!cut[dsu.get(ansFrom[i])]) {
                        ansTo[i] = colorsCount;
                    }
                }
            }
            ansFrom[edges] = id;
            ansTo[edges] = colorsCount;
            ansCap[edges] = (int) flow;
            ++edges;
            ++colorsCount;
        }
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[color[i]] = i;
        }
        for (int i = 0; i < edges; i++) {
            ansFrom[i] = rev[ansFrom[i]];
            ansTo[i] = rev[ansTo[i]];
        }
        int ans = 0;
        for (int i = 0; i < edges; i++) {
            ans += ansCap[i];
        }
        out.println(ans);
        List<Integer> eds = new ArrayList<>();
        for (int i = 0; i < edges; i++) {
            eds.add(i);
        }
        List<Integer> answer = new ArrayList<>();
        boolean[] vs = new boolean[n];
        Arrays.fill(vs, true);
        getAns(eds, answer, ansFrom, ansTo, ansCap, n, vs);
//        System.out.println(Arrays.toString(ansFrom));
//        System.out.println(Arrays.toString(ansTo));
//        System.out.println(Arrays.toString(ansCap));
        for (int i : answer) {
            out.print(i + 1 + " ");
        }
//        {
//            System.out.println(answer);
//            int cans = 0;
//            for (int i = 1; i < answer.size(); i++) {
//                DinicGraph g = new DinicGraph(n);
//                for (int j = 0; j < m; j++) {
//                    g.addEdge(from[j], to[j], cap[j]);
//                    g.addEdge(to[j], from[j], cap[j]);
//                }
//                long cur2 = g.getMaxFlow(answer.get(i - 1), answer.get(i));
//                cans += cur2;
//                System.out.println(answer.get(i - 1) + " " + answer.get(i) + " " + cur2);
//            }
//            System.out.println(cans);
//        }
    }

    static void getAns(List<Integer> edges, List<Integer> answer, int[] ansFrom, int[] ansTo, int[] ansCap, int n, boolean[] vs) {
        if (edges.isEmpty()) {
            for (int i = 0; i < n; i++) {
                if (vs[i]) {
                    answer.add(i);
                }
            }
            return;
        }
        int minimal = -1;
        for (int i : edges) {
            if (minimal < 0 || ansCap[minimal] > ansCap[i]) {
                minimal = i;
            }
        }
//        System.out.println(edges + " " + Arrays.toString(vs) + " " + minimal);
        DisjointSetUnionFast dsu = new DisjointSetUnionFast(n);
        for (int i : edges) {
            if (i == minimal) continue;
            dsu.union(ansFrom[i], ansTo[i]);
        }
        int[] cc = new int[n];
        Arrays.fill(cc, -1);
        int was = 0;
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        boolean[] fs = new boolean[n];
        boolean[] sc = new boolean[n];
        fs[ansFrom[minimal]] = true;
        sc[ansTo[minimal]] = true;
        for (int i = 0; i < n; i++) {
            if (!vs[i]) {
                continue;
            }
            int comp = dsu.get(i);
            if (cc[comp] < 0) {
                cc[comp] = was++;
            }
            if (cc[comp] == 0) {
                fs[i] = true;
            } else {
                sc[i] = true;
            }
        }
        if (was > 2) throw new AssertionError();
        for (int i : edges) {
            if (i == minimal) continue;
            int comp = dsu.get(ansFrom[i]);
            if (cc[comp] == 0) {
                first.add(i);
            } else if (cc[comp] == 1) {
                second.add(i);
            }
        }
        getAns(first, answer, ansFrom, ansTo, ansCap, n, fs);
        getAns(second, answer, ansFrom, ansTo, ansCap, n, sc);
    }
}
