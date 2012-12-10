package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = in.nextLine();
            if (s == null) {
                break;
            }
            sb.append(s);
        }
        StringTokenizer st = new StringTokenizer(sb.toString(), "<> \r\t");
        List<Integer> ans = new ArrayList<Integer>();
        List<Integer> cur = new ArrayList<Integer>();
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s.endsWith("tr")) {
                continue;
            }
            if (s.equals("td")) {
                cur.set(cur.size() - 1, cur.get(cur.size() - 1) + 1);
            }
            if (s.endsWith("td")) {
                continue;
            }
            if (s.equals("table")) {
                cur.add(0);
            } else {
                ans.add(cur.remove(cur.size() - 1));
            }
        }
        Collections.sort(ans);
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ans));
	}
}
