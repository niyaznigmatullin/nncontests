package mypackage;

import niyazio.FastScanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {
	public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (!count.containsKey(x)) {
                 count.put(x, 0);
            }
            count.put(x, count.get(x) + 1);
        }
        PriorityQueue<Element> pq = new PriorityQueue<Element>();
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            pq.add(new Element(e.getKey(), e.getValue()));
        }
        List<String> output = new ArrayList<String>();
        while (pq.size() >= 3) {
            Element e1 = pq.poll();
            Element e2 = pq.poll();
            Element e3 = pq.poll();
            int[] a = new int[]{e1.val, e2.val, e3.val};
            Arrays.sort(a);
            output.add(a[2] + " " + a[1] + " " + a[0]);
            e1.count--;
            e2.count--;
            e3.count--;
            if (e1.count != 0) {
                pq.add(e1);
            }
            if (e2.count != 0) {
                pq.add(e2);
            }
            if (e3.count != 0) {
                pq.add(e3);
            }
        }
        out.println(output.size());
        for (String e : output) {
            out.println(e);
        }
	}

    static class Element implements Comparable<Element> {
        int val;
        int count;

        Element(int val, int count) {
            this.val = val;
            this.count = count;
        }

        public int compareTo(Element o) {
            return o.count - count;
        }
    }
}

