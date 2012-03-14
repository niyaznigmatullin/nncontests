package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
    static class Element implements Comparable<Element> {
        int cost;
        int type;
        int id;

        Element(int cost, int type, int id) {
            this.cost = cost;
            this.type = type;
            this.id = id;
        }


        public int compareTo(Element o) {
            return cost < o.cost ? 1 : cost > o.cost ? -1 : 0;
        }
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        List<Element> chairs = new ArrayList<Element>();
        List<Element> pencils = new ArrayList<Element>();
        for (int i = 0; i < n; i++) {
            int cost = in.nextInt() * 2;
            int type = in.nextInt();
            Element e = new Element(cost, type, i);
            if (type == 1) {
                chairs.add(e);
            } else {
                pencils.add(e);
            }
        }
        Collections.sort(chairs);
        Collections.sort(pencils);
        List<Element>[] add = new ArrayList[k];
        for (int i = 0; i < k; i++) {
            add[i] = new ArrayList<Element>();
        }
        int current = 0;
        for (Element e : chairs) {
            if (current == k) {
                --current;
            }
            add[current++].add(e);
        }
        if (chairs.size() < k) {
            current = chairs.size();
            for (Element e : pencils) {
                if (current == k) {
                    --current;
                }
                add[current++].add(e);
            }
        } else {
            for (Element e : pencils) {
                add[k - 1].add(e);
            }
        }
        long answer = 0;
        long profit = 0;
        for (List<Element> z : add) {
            if (z.isEmpty()) {
                throw new AssertionError();
            }
            boolean has = false;
            for (Element e : z) {
                if (e.type == 1) {
                    has = true;
                }
                answer += e.cost;
            }
            if (has) {
                long minimal = Long.MAX_VALUE;
                for (Element e : z) {
                    minimal = Math.min(e.cost, minimal);
                }
                profit += minimal >> 1;
            }
        }
        answer -= profit;
        out.println(answer / 2 + "." + ((answer & 1) == 1 ? 5 : 0));
        for (List<Element> z : add) {
            out.print(z.size());
            for (Element e : z) {
                out.print(" " + (e.id + 1));
            }
            out.println();
        }
    }
}
