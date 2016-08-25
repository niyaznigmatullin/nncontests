package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;

import java.util.Random;

public class ZeroPointSixThreeSix {
    public int[] replan(int[] x, int[] y, int[] match) {
        int n = x.length;
        Point2DInteger[] p= new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(x[i], y[i]);
        }
        Random rand = new Random(System.currentTimeMillis());
        int[] initMatch = match.clone();
        double initAns = 0;
        for (int i = 0; i < n; i++) {
            if (i < match[i]) {
                initAns += p[i].distance(p[match[i]]);
            }
        }
        while (true) {
            while (true) {
                boolean changed = false;
                for (int i = 0; i < n; i++) {
                    if (i < match[i]) continue;
                    for (int j = i + 1; j < n; j++) {
                        if (j < match[j] || match[i] == j) continue;
                        int a = i;
                        int b = match[i];
                        int c = j;
                        int d = match[j];
                        if (GeometryAlgorithms.segmentsIntersect(p[i], p[match[i]], p[j], p[match[j]])) {
                            if (rand.nextBoolean()) {
                                match[a] = c;
                                match[c] = a;
                                match[b] = d;
                                match[d] = b;
                            } else {
                                match[a] = d;
                                match[d] = a;
                                match[b] = c;
                                match[c] = b;
                            }
                            changed = true;
                        }
                    }
                }
                if (!changed) break;
            }
            double curAns = 0;
            for (int i = 0; i < n; i++) {
                if (i < match[i]) {
                    curAns += p[i].distance(p[match[i]]);
                }
            }
            if (curAns < initAns * 0.637) {
                match = initMatch.clone();
            } else {
                break;
            }
        }
        return match;
    }
}
