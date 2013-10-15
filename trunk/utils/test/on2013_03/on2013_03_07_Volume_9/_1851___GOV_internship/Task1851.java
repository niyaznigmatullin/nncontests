package lib.test.on2013_03.on2013_03_07_Volume_9._1851___GOV_internship;



import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Task1851 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        String t = in.next();
        int[] id1 = new int[s.length()];
        int[] id2 = new int[t.length()];
        Arrays.fill(id1, -1);
        Arrays.fill(id2, -1);
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '?') {
                id1[i] = cnt++;
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '?') id2[i] = cnt++;
        }
        DinicGraph g = new DinicGraph(2 + cnt);
        int source = cnt;
        int target = cnt + 1;
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int c1 = 0;
            int c0 = 0;
            for (int j = 0; j < t.length(); j++) {
                if (i + t.length() - j > s.length() || i - j < 0) {
                    continue;
                }
                if (t.charAt(j) == '0') c0++;
                else if (t.charAt(j) == '1') c1++;
            }
            if (id1[i] >= 0) {
                g.addEdge(source, id1[i], c0);
                g.addEdge(id1[i], target, c1);
            } else {
                if (s.charAt(i) == '0') ans += c1;
                else ans += c0;
            }
        }
        for (int i = 0; i < t.length(); i++) {
            int c0 = 0;
            int c1 = 0;
            for (int start = 0; start <= s.length() - t.length(); start++) {
                int j = i + start;
                if (id1[j] < 0) {
                    if (s.charAt(j) == '0') ++c0;
                    else ++c1;
                    continue;
                }
                if (id2[i] >= 0) {
                    g.addEdge(id2[i], id1[j], 1);
                    g.addEdge(id1[j], id2[i], 1);
                }
            }
            if (id2[i] >= 0) {
                g.addEdge(source, id2[i], c0);
                g.addEdge(id2[i], target, c1);
            }
        }
        ans += g.getMaxFlow(source, target);
        out.println(ans);
        boolean[] z = g.getCut(source, target);
        StringBuilder ans1 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (id1[i] >= 0) ans1.append(z[id1[i]] ? '0' : '1');
            else
                ans1.append(s.charAt(i));
        }
        StringBuilder ans2 = new StringBuilder();
        for (int i = 0; i < t.length(); i++) {
            if (id2[i] >= 0) ans2.append(z[id2[i]] ? '0' : '1');
            else
                ans2.append(t.charAt(i));
        }
        out.println(ans1);
        out.println(ans2);
    }
}
