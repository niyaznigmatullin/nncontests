package coding;

import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class LaserBoxes2 {
    static final Random rand = new Random(12323232L);

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DInteger[][][] p = new Point2DInteger[n][][];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger[in.nextInt()][];
            for (int j = 0; j < p[i].length; j++) {
                p[i][j] = new Point2DInteger[in.nextInt()];
                for (int k = 0; k < p[i][j].length; k++) {
                    p[i][j][k] = new Point2DInteger(in.nextInt(), in.nextInt());
                }
            }
        }
        int[] w = new int[n];
        int[] shiftX = new int[n];
        int[] shiftY = new int[n];
        final int[] h = new int[n];
        int sumW = 0;
        for (int i = 0; i < n; i++) {
            int x1 = Integer.MAX_VALUE;
            int y1 = Integer.MAX_VALUE;
            int x2 = Integer.MIN_VALUE;
            int y2 = Integer.MIN_VALUE;
            for (Point2DInteger[] f : p[i]) {
                for (Point2DInteger e : f) {
                    x1 = Math.min(x1, e.x);
                    y1 = Math.min(y1, e.y);
                    x2 = Math.max(x2, e.x);
                    y2 = Math.max(y2, e.y);
                }
            }
            shiftX[i] = x1;
            shiftY[i] = y1;
            w[i] = x2 - x1 + 1;
            h[i] = y2 - y1 + 1;
            for (Point2DInteger[] f : p[i]) {
                for (Point2DInteger e : f) {
                    e.x -= x1;
                    e.y -= y1;
                }
            }
            sumW += w[i] + 1;
        }
        Integer[] id1 = new Integer[n];
        Integer[] id2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            id1[i] = i;
            id2[i] = i;
        }
        Arrays.sort(id1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return h[o1] - h[o2];
            }
        });
        Arrays.sort(id2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return h[o2] - h[o1];
            }
        });
        long best = Long.MAX_VALUE;
        int[] bestOffsetW = new int[n];
        int[] bestOffsetH = new int[n];
        loop:
        for (int sw = 1; sw <= sumW && sw < 3200; sw++) {
            for (Integer[] id : new Integer[][]{id1, id2}) {
                for (int i : id) if (w[i] + 1 > sw) continue loop;
                int[] curOffsetW = new int[n];
                int[] curOffsetH = new int[n];
                int ws = 0;
                int hs = 0;
                int bestH = 0;
                int maxW = 0;
                for (int i : id) {
                    if (ws + w[i] + 1 >= sw) {
                        hs += bestH + 1;
                        ws = 0;
                        bestH = 0;
                    }
                    curOffsetH[i] = hs;
                    curOffsetW[i] = ws;
                    ws += w[i] + 1;
                    maxW = Math.max(maxW, ws);
                    bestH = Math.max(bestH, h[i]);
                }
                hs += bestH + 1;
                if (maxW < 3200 && hs < 3200 && (long) maxW * hs < best) {
                    best = (long) maxW * hs;
                    bestOffsetH = curOffsetH;
                    bestOffsetW = curOffsetW;
                }
            }
            int[] id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            for (int it = 0; it < 100; it++) {
                for (int i = 1; i < n; i++) {
                    int j = rand.nextInt(i + 1);
                    int t = id[i];
                    id[i] = id[j];
                    id[j] = t;
                }
                for (int i : id) if (w[i] + 1 > sw) continue loop;
                int[] curOffsetW = new int[n];
                int[] curOffsetH = new int[n];
                int ws = 0;
                int hs = 0;
                int bestH = 0;
                int maxW = 0;
                for (int i : id) {
                    if (ws + w[i] + 1 >= sw) {
                        hs += bestH + 1;
                        ws = 0;
                        bestH = 0;
                    }
                    curOffsetH[i] = hs;
                    curOffsetW[i] = ws;
                    ws += w[i] + 1;
                    maxW = Math.max(maxW, ws);
                    bestH = Math.max(bestH, h[i]);
                }
                hs += bestH + 1;
                if (maxW < 3200 && hs < 3200 && (long) maxW * hs < best) {
                    best = (long) maxW * hs;
                    bestOffsetH = curOffsetH;
                    bestOffsetW = curOffsetW;
                }
            }
        }
        int all = 0;
        for (Point2DInteger[][] f : p) for (Point2DInteger[] e : f) all += e.length;
        out.println(all + " " + Integer.MAX_VALUE / 3);
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = 0;
        int y2 = 0;
        for (int i = 0; i < n; i++) {
            for (Point2DInteger[] f : p[i]) {
                for (int j = 0; j < f.length; j++) {
                    int k = (j + 1) % f.length;
                    int cx = bestOffsetW[i] + f[j].x;
                    int cy = bestOffsetH[i] + f[j].y;
                    x1 = Math.min(x1, cx);
                    y1 = Math.min(y1, cy);
                    x2 = Math.max(x2, cx);
                    y2 = Math.max(y2, cy);
                    out.println(cx + " " + cy + " " + (bestOffsetW[i] + f[k].x) + " " + (bestOffsetH[i] + f[k].y));
                }
            }
        }
        {
            try {
                PrintWriter fout = new PrintWriter("e.offset");
                for (int i = 0; i < n; i++) {
                    fout.println("# offset " + i + " " + (-shiftX[i] + bestOffsetW[i]) + " " + (-shiftY[i] + bestOffsetH[i]));
                }
                fout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        {
            try {
                PrintWriter fout = new PrintWriter("e.score");
                fout.println((x2 - x1 + 1) * (y2 - y1 + 1));
                fout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
