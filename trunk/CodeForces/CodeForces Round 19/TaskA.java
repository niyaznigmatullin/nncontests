package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> a = new HashMap<String, Integer>();
        String[] name = new String[n];
        for (int i = 0; i < n; i++) {
            String team = in.next();
            a.put(team, i);
            name[i] = team;
        }
        final int[] pnt = new int[n];
        final int[] scored = new int[n];
        final int[] dif = new int[n];
        for (int i = 0; i < n * (n - 1) / 2; i++) {
            StringTokenizer st = new StringTokenizer(in.next(), "-");
            int t1 = a.get(st.nextToken());
            int t2 = a.get(st.nextToken());
            st = new StringTokenizer(in.next(), ":");
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            if (p1 > p2) {
                pnt[t1] += 3;
            } else if (p1 < p2) {
                pnt[t2] += 3;
            } else {
                ++pnt[t1];
                ++pnt[t2];
            }
            scored[t1] += p1;
            scored[t2] += p2;
            dif[t1] += p1 - p2;
            dif[t2] += p2 - p1;
        }
        Integer[] z = new Integer[n];
        for (int i = 0; i < n; i++) {
            z[i] = i;
        }
        Arrays.sort(z, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (pnt[o1] != pnt[o2]) {
                    return pnt[o2] - pnt[o1];
                }
                if (dif[o1] != dif[o2]) {
                    return dif[o2] - dif[o1];
                }
                return scored[o2] - scored[o1];
            }
        });
        String[] ans = new String[n / 2];
        for (int i = 0; i < n / 2; i++) {
            ans[i] = (name[z[i]]);
        }
        Arrays.sort(ans);
        for (String e : ans) {
            out.println(e);
        }
    }
}
