package lib.test.on2013_08.on2013_08_31_.Drawing;



import com.sun.org.apache.bcel.internal.generic.DSUB;
import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Drawing {

    static class Segment {
        Point2DInteger p, q;

        Segment(Point2DInteger p, Point2DInteger q) {
            if (p.x > q.x || p.x == q.x && p.y > q.y) {
                Point2DInteger t = p;
                p = q;
                q = t;
            }
            this.p = p;
            this.q = q;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        List<Segment> list = new ArrayList<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            Point2DInteger p = new Point2DInteger(in.nextInt(), in.nextInt());
            Point2DInteger q = new Point2DInteger(in.nextInt(), in.nextInt());
            list.add(new Segment(p, q));
        }
        all:
        while (true) {
            for (int i = 0; i < list.size(); i++) {
                Segment s1 = list.get(i);
                for (int j = i + 1; j < list.size(); j++) {
                    Segment s2 = list.get(j);
                    if (s1.q.equals(s2.p) || s2.q.equals(s1.p)) {
                        if (GeometryAlgorithms.vmulFromPoint(s1.p, s2.q, s2.p) == 0 &&
                                GeometryAlgorithms.vmulFromPoint(s1.q, s2.q, s2.p) == 0) {
                            list.remove(j);
                            list.remove(i);
                            if (s1.q.equals(s2.p)) {
                                list.add(new Segment(s1.p, s2.q));
                            } else {
                                list.add(new Segment(s2.p, s1.q));
                            }
                            continue all;
                        }
                    }
                }
            }
            break;
        }
        Map<Point2DInteger, Integer> deg = new HashMap<>();
        for (Segment e : list) {
            {
                Point2DInteger a = e.p;
                Integer f = deg.get(a);
                if (f == null) f = 0;
                deg.put(a, f + 1);
            }
            {
                Point2DInteger a = e.q;
                Integer f = deg.get(a);
                if (f == null) f = 0;
                deg.put(a, f + 1);
            }
        }
        int countOdd = 0;
        for (int e : deg.values()) {
            if ((e & 1) == 1) {
                ++countOdd;
            }
        }
        if (countOdd > 2) {
            out.println(0);
            return;
        }
        Map<Point2DInteger, Integer> z = new HashMap<>();
        for (Point2DInteger e : deg.keySet()) {
            z.put(e, z.size());
        }
        for (Segment e : list) {
            int v = z.get(e.p);
            int u = z.get(e.q);
            if (v != u) {
                for (Point2DInteger f : z.keySet()) {
                    int fv = z.get(f);
                    if (fv == v) {
                        z.put(f, u);
                    }
                }
            }
        }
        all:
        while (true) {
            for (int i = 0; i < list.size(); i++) {
                Segment s1 = list.get(i);
                if (s1.p.x != s1.q.x) continue;
                for (int j = 0; j < list.size(); j++) {
                    Segment s2 = list.get(j);
                    if (s2.p.y != s2.q.y) continue;
                    int v = z.get(s1.p);
                    int u = z.get(s2.p);
                    if (v == u) continue;
                    if (s2.p.y >= s1.p.y && s2.p.y <= s1.q.y && s1.p.x >= s2.p.x && s1.p.x <= s2.q.x) {
                        Point2DInteger pp = new Point2DInteger(s1.p.x, s2.p.y);
                        if (!z.containsKey(pp)) {
                            continue;
                        }
                        if (i > j) {
                            list.remove(i);
                            list.remove(j);
                        } else {
                            list.remove(j);
                            list.remove(i);
                        }
                        if (!s1.p.equals(pp))
                            list.add(new Segment(s1.p, pp));
                        if (!pp.equals(s1.q))
                            list.add(new Segment(pp, s1.q));
                        if (!pp.equals(s2.p))
                            list.add(new Segment(s2.p, pp));
                        if (!pp.equals(s2.q))
                            list.add(new Segment(pp, s2.q));
                        for (Point2DInteger e : z.keySet()) {
                            int ev = z.get(e);
                            if (ev == v) {
                                z.put(e, u);
                            }
                        }
                        continue all;
                    }
                }
            }
            break;
        }
        all:
        while (true) {
            for (int i = 0; i < list.size(); i++) {
                Segment s1 = list.get(i);
                if (s1.p.x != s1.q.x) continue;
                for (int j = 0; j < list.size(); j++) {
                    Segment s2 = list.get(j);
                    if (s2.p.y != s2.q.y) continue;
                    int v = z.get(s1.p);
                    int u = z.get(s2.p);
                    if (v == u) continue;
                    if (s2.p.y >= s1.p.y && s2.p.y <= s1.q.y && s1.p.x >= s2.p.x && s1.p.x <= s2.q.x) {
                        Point2DInteger pp = new Point2DInteger(s1.p.x, s2.p.y);
                        z.put(pp, u);
                        if (i > j) {
                            list.remove(i);
                            list.remove(j);
                        } else {
                            list.remove(j);
                            list.remove(i);
                        }
                        if (!s1.p.equals(pp))
                            list.add(new Segment(s1.p, pp));
                        if (!pp.equals(s1.q))
                            list.add(new Segment(pp, s1.q));
                        if (!pp.equals(s2.p))
                            list.add(new Segment(s2.p, pp));
                        if (!pp.equals(s2.q))
                            list.add(new Segment(pp, s2.q));
                        for (Point2DInteger e : z.keySet()) {
                            int ev = z.get(e);
                            if (ev == v) {
                                z.put(e, u);
                            }
                        }
                        continue all;
                    }
                }
            }
            break;
        }
        int component = -1;
        for (int v : z.values()) {
            if (component < 0) {
                component = v;
            } else if (component != v) {
                out.println(0);
                return;
            }
        }
        List<Point2DInteger> stack = new ArrayList<>();
        for (Point2DInteger e : deg.keySet()) {
            if ((deg.get(e) & 1) == 1) {
                stack.add(e);
                break;
            }
        }
        if (stack.isEmpty()) {
            stack.add(z.keySet().iterator().next());
        }
        List<Point2DInteger> answer = new ArrayList<>();
        out.println(list.size());
        all: while (!stack.isEmpty()) {
            Point2DInteger onTop = stack.get(stack.size() - 1);
            for (int i = 0; i < list.size(); i++) {
                Segment e = list.get(i);
                Point2DInteger e1 = e.p;
                Point2DInteger e2 = e.q;
                if (!e1.equals(onTop)) {
                    Point2DInteger t = e1;
                    e1 = e2;
                    e2 = t;
                }
                if (!e1.equals(onTop)) {
                    continue;
                }
                list.remove(i);
                stack.add(e2);
                continue all;
            }
            answer.add(stack.remove(stack.size() - 1));
        }
        if (!list.isEmpty()) throw new AssertionError();
        for (int i = 0; i + 1 < answer.size(); i++) {
            out.println(answer.get(i).x + " " + answer.get(i).y + " " + answer.get(i + 1).x + " " + answer.get(i + 1).y);
        }
    }
}
