package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TaskB {

    static int[] POINTS = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    static class Pilot {
        int points;
        String name;
        int[] wins;

        Pilot(String name) {
            this.name = name;
            wins = new int[100];
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        Map<String, Pilot> all = new HashMap<String, Pilot>();
        for (int race = 0; race < t; race++) {
            int n = in.nextInt();
            String[] name = new String[n];
            for (int i = 0; i < n; i++) {
                name[i] = in.next();
            }
            for (int i = 0; i < n; i++) {
                int add = i < POINTS.length ? POINTS[i] : 0;
                Pilot e = all.get(name[i]);
                if (e == null) {
                    e = new Pilot(name[i]);
                }
                e.points += add;
                e.wins[i]++;
                all.put(name[i], e);
            }
        }
        Pilot best1 = null;
        Comparator<Pilot> c1 = new Comparator<Pilot>() {
            public int compare(Pilot o1, Pilot o2) {
                if (o1.points != o2.points) {
                    return o2.points - o1.points;
                }
                for (int i = 0; i < o1.wins.length; i++) {
                    if (o1.wins[i] != o2.wins[i]) {
                        return o2.wins[i] - o1.wins[i];
                    }
                }
                return 0;
            }
        };
        Comparator<Pilot> c2 = new Comparator<Pilot>() {
            public int compare(Pilot o1, Pilot o2) {
                if (o1.wins[0] != o2.wins[0]) {
                    return o2.wins[0] - o1.wins[0];
                }
                if (o1.points != o2.points) {
                    return o2.points - o1.points;
                }
                for (int i = 1; i < o1.wins.length; i++) {
                    if (o1.wins[i] != o2.wins[i]) {
                        return o2.wins[i] - o1.wins[i];
                    }
                }
                return 0;
            }
        };
        Pilot best2 = null;
        for (Pilot e : all.values()) {
            if (best1 == null || c1.compare(best1, e) > 0) {
                best1 = e;
            }
            if (best2 == null || c2.compare(best2, e) > 0) {
                best2 = e;
            }
        }
        out.println(best1.name);
        out.println(best2.name);
	}
}
