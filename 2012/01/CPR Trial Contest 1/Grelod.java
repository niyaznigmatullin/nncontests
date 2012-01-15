package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class Grelod {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> map = new HashMap<String, Integer>();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            map.put(in.next(), i);
            s[i] = in.next();
        }
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = map.get(s[i]);
        }
        Arrays.sort(s);
        boolean[] was = new boolean[n];
        List<String> answer = new ArrayList<String>();
        for (int i = 0; i < s.length; i++) {
            for (int j = i + 1; j < s.length; j++) {
                Arrays.fill(was, false);
                int x = map.get(s[i]);
                boolean ok = false;
                while (!was[x]) {
                    was[x] = true;
                    x = p[x];
                }
                int y = map.get(s[j]);
                if (was[y]) {
                    int t = p[x];
                    p[x] = p[y];
                    p[y] = t;
                    answer.add(s[i] + " <=> " + s[j]);
                }
            }
        }
        out.println(answer.size());
        for (String e : answer) {
            out.println(e);
        }
    }
}
