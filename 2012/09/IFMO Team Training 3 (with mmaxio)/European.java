package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class European {

    ArrayList<Integer> cur;
    ArrayList<Integer> ans;

    void go(TreeSet<Integer> set) {
        //System.err.println(set);
        if (set.isEmpty()) {
            if (ans == null || ans.size() > cur.size()) {
                ans = new ArrayList<Integer>(cur);
            }
            return;
        }
        if (cur.size() == 5) {
            return;
        }
        for (int x : set) {
            for (int i = 0; i < cur.size(); i++) {
                int p = cur.get(i) - x;
                if (p >= 0) {
                    TreeSet<Integer> to = new TreeSet<Integer>(set);
                    for (int j = 0; j < cur.size(); j++) {
                        to.remove(Math.abs(p - cur.get(j)));
                    }
                    cur.add(p);
                    go(to);
                    cur.remove(cur.size() - 1);
                }
                p = cur.get(i) + x;
                TreeSet<Integer> to = new TreeSet<Integer>(set);
                for (int j = 0; j < cur.size(); j++) {
                    to.remove(Math.abs(p - cur.get(j)));
                }
                cur.add(p);
                go(to);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < n; i++)
            set.add(in.nextInt());
        cur = new ArrayList<Integer>();
        cur.add(0);
        ans = null;
        go(set);
        out.println("Scenario #" + testNumber);
        out.print(ans.size() + ": ");
        Collections.sort(ans);
        for (int x : ans)
            out.print(x + " ");
        out.println();
        out.println();
    }
}
