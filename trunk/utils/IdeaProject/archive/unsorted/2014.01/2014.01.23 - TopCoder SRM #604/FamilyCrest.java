package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;

import java.util.*;

public class FamilyCrest {
    static class Event {
        Point2DInteger v;
        boolean start;
        Event pair;

        Event(Point2DInteger v, boolean start) {
            this.v = v;
            this.start = start;
        }
    }

    public String canBeInfinite(int[] x1, int[] y1, int[] x2, int[] y2) {
        int n = x1.length;
        if (n <= 2) return "Infinite";
        List<Event> events = new ArrayList<>();
        int all = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point2DInteger p1 = new Point2DInteger(x1[i], y1[i]);
                Point2DInteger p2 = new Point2DInteger(x2[i], y2[i]);
                Point2DInteger p3 = new Point2DInteger(x1[j], y1[j]);
                Point2DInteger p4 = new Point2DInteger(x2[j], y2[j]);
                if (p1.equals(p4)) {
                    Point2DInteger t = p3;
                    p3 = p4;
                    p4 = t;
                } else if (p2.equals(p4)) {
                    Point2DInteger t = p3;
                    p3 = p4;
                    p4 = t;
                    t = p1;
                    p1 = p2;
                    p2 = t;
                } else if (p2.equals(p3)) {
                    Point2DInteger t = p1;
                    p1 = p2;
                    p2 = t;
                }
                if (p1.equals(p3)) {
                    {
                        Point2DInteger v1 = p2.subtract(p1);
                        Point2DInteger v2 = p4.subtract(p3);
                        if (v1.vmul(v2) < 0) {
                            Point2DInteger t = v1;
                            v1 = v2;
                            v2 = t;
                        }
                        Event e1 = new Event(v1, true);
                        Event e2 = new Event(v2, false);
                        e1.pair = e2;
                        e2.pair = e1;
                        events.add(e1);
                        events.add(e2);
                    }
                    {
                        Point2DInteger v1 = p1.subtract(p2);
                        Point2DInteger v2 = p3.subtract(p4);
                        if (v1.vmul(v2) < 0) {
                            Point2DInteger t = v1;
                            v1 = v2;
                            v2 = t;
                        }
                        Event e1 = new Event(v1, true);
                        Event e2 = new Event(v2, false);
                        e1.pair = e2;
                        e2.pair = e1;
                        events.add(e1);
                        events.add(e2);
                    }
                    ++all;
                } else {
                    if (p2.subtract(p1).vmul(p4.subtract(p3)) != 0 && GeometryAlgorithms.segmentsIntersect(p1, p2, p3, p4)) {
                        return "Finite";
                    }
                }
            }
        }
        Comparator<Event> comp = new Comparator<Event>() {

            int get(int x, int y) {
                if (x > 0) {
                    return y > 0 ? 2 : y < 0 ? 8 : 1;
                } else if (x < 0) {
                    return y > 0 ? 4 : y < 0 ? 6 : 5;
                } else {
                    return y > 0 ? 3 : y < 0 ? 7 : 0;
                }
            }

            int compareVectors(Point2DInteger p1, Point2DInteger p2) {
                int c = get(p1.x, p1.y) - get(p2.x, p2.y);
                if (c != 0) return c;
                return (int) p2.vmul(p1);
            }

            @Override
            public int compare(Event o1, Event o2) {
                int c = compareVectors(o1.v, o2.v);
                if (c != 0) return c;
                if (o1.start == o2.start) return 0;
                if (!o1.start) return -1;
                return 1;
            }
        };
        Collections.sort(events, comp);
        int count = 0;
        for (Event e : events) {
            if (e.start && comp.compare(e, e.pair) > 0) {
                ++count;
            }
        }
        for (Event e : events) {
            if (count == all) {
                return "Infinite";
            }
            if (e.start) {
                ++count;
            } else {
                --count;
            }
        }
        return "Finite";
    }
}
