package lib.test.on2013_04.on2013_04_27_Challenge24.LaserBoxes;



import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.awt.*;
import java.awt.geom.Area;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class LaserBoxes {

    static final Random rand = new Random(12323232L);
    static final int CO = 2000;

    static final int dw = 40;
    static Area[] alreadyHave;
    static Integer[] order;
    static int[] width;
    static int[] height;
    static int[][] xs;
    static int[][] ys;
    static int best;
    static int[] bestOffsetW, bestOffsetH, curOffsetH, curOffsetW;

    static Polygon getPolygon(Point2DInteger[] p, int dx, int dy) {
        int[] cxs = new int[p.length];
        int[] cys = new int[p.length];
        for (int i = 0; i < cxs.length; i++) {
            cxs[i] = p[i].x + dx;
            cys[i] = p[i].y + dy;
        }
        return new Polygon(cxs, cys, cxs.length);
    }

    static Polygon getPolygon(int id, int dx, int dy) {
        int[] cxs = xs[id].clone();
        int[] cys = ys[id].clone();
        for (int i = 0; i < cxs.length; i++) {
            cxs[i] += dx;
            cys[i] += dy;
        }
        return new Polygon(cxs, cys, cxs.length);
    }

    static void go(int x, int currentWidth, int currentHeight) {
        if ((long) currentHeight * currentWidth >= best) return;
        if (x == order.length) {
            best = currentHeight * currentWidth;
            bestOffsetH = curOffsetH;
            bestOffsetW = curOffsetW;
            return;
        }
        int i = order[x];
        int lastW = Integer.MAX_VALUE;
        for (int h = 0; h <= currentHeight; h += dw) {
            for (int w = 0; w <= lastW; w += dw) {
                boolean bad = false;
                for (int old = 0; old < x; old++) {
                    Area curArea = new Area(getPolygon(i, w, h));
                    curArea.intersect(alreadyHave[old]);
                    if (!curArea.isEmpty()) {
                        bad = true;
                        break;
                    }
                }
                alreadyHave[x] = new Area(getPolygon(i, w, h));
                if (bad) {
                    continue;
                }
                curOffsetH[i] = h;
                curOffsetW[i] = w;
                lastW = Math.min(lastW, w);
                go(x + 1, Math.max(currentWidth, w + width[i]), Math.max(currentHeight, h + height[i]));
                break;
            }
        }
    }

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
        width = new int[n];
        int[] shiftX = new int[n];
        int[] shiftY = new int[n];
        height = new int[n];
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
            if (i == 78) System.err.println(x1 + " " + y1);
            width[i] = x2 - x1 + 1;
            height[i] = y2 - y1 + 1;
            for (Point2DInteger[] f : p[i]) {
                for (Point2DInteger e : f) {
                    e.x -= x1;
                    e.y -= y1;
                }
            }
            sumW += width[i] + 1;
        }
        xs = new int[n][];
        ys = new int[n][];
        int[] notHole = new int[n];
        boolean[][] used = new boolean[n][];
        for (int i = 0; i < n; i++) {
            used[i] = new boolean[p[i].length];
            long bestArea = 0;
            int id = -1;
            for (int j = 0; j < p[i].length; j++) {
                long curArea = 0;
                for (int k = 0; k < p[i][j].length; k++) {
                    int l = (k + 1) % p[i][j].length;
                    curArea += p[i][j][k].vmul(p[i][j][l]);
                }
                if (curArea < 0) {
                    for (int k = 1, l = p[i][j].length - 1; k < l; k++, l--) {
                        Point2DInteger t = p[i][j][k];
                        p[i][j][k] = p[i][j][l];
                        p[i][j][l] = t;
                    }
                    curArea = -curArea;
                }
                if (curArea > bestArea) {
                    bestArea = curArea;
                    id = j;
                }
            }
            notHole[i] = id;
            int[] cxs = new int[p[i][id].length];
            int[] cys = new int[p[i][id].length];
            for (int j = 0; j < p[i][id].length; j++) {
                cxs[j] = p[i][id][j].x;
                cys[j] = p[i][id][j].y;
            }
            xs[i] = cxs;
            ys[i] = cys;
        }
        int[] inHole = new int[n];
        int[] shiftHoleW = new int[n];
        int[] shiftHoleH = new int[n];
        Arrays.fill(inHole, -1);
        all:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = 0; k < p[j].length; k++) {
                    if (used[j][k] || k == notHole[j]) continue;
                    int x1 = Integer.MAX_VALUE;
                    int y1 = Integer.MAX_VALUE;
                    int x2 = Integer.MIN_VALUE;
                    int y2 = Integer.MIN_VALUE;
                    for (Point2DInteger e : p[j][k]) {
                        x1 = Math.min(x1, e.x);
                        y1 = Math.min(y1, e.y);
                        x2 = Math.max(x2, e.x);
                        y2 = Math.max(y2, e.y);
                    }
                    for (int x = x1; x + width[i] <= x2; x++) {
                        for (int y = y1; y + height[i] <= y2; y++) {
                            Area area = new Area(getPolygon(p[j][k], 0, 0));
                            Area area2 = new Area(getPolygon(i, x, y));
                            area.intersect(area2);
                            if (area.equals(area2)) {
                                inHole[i] = j;
                                used[j][k] = true;
                                shiftHoleW[i] = x;
                                shiftHoleH[i] = y;
                                continue all;
                            }
                        }
                    }
                }
            }
        }
        Integer[] id1 = new Integer[n];
        Integer[] id2 = new Integer[n];
        order = new Integer[n];
        for (int i = 0; i < n; i++) {
            id1[i] = i;
            id2[i] = i;
            order[i] = i;
        }
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(height[o1] * width[o1], height[o2] * width[o2]);
            }
        });
        Arrays.sort(id1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return height[o1] - height[o2];
            }
        });
        Arrays.sort(id2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return height[o2] - height[o1];
            }
        });
        best = Integer.MAX_VALUE;
        bestOffsetW = new int[n];
        bestOffsetH = new int[n];
        loop:
        for (int sw = 1; sw <= sumW && sw < CO; sw++) {
            for (Integer[] id : new Integer[][]{id1, id2}) {
                for (int i : id) if (width[i] + 1 > sw) continue loop;
                int[] curOffsetW = new int[n];
                int[] curOffsetH = new int[n];
                int ws = 0;
                int hs = 0;
                int bestH = 0;
                int maxW = 0;
                for (int i : id) {
                    if (inHole[i] >= 0) continue;
                    if (ws + width[i] + 1 >= sw) {
                        hs += bestH + 1;
                        ws = 0;
                        bestH = 0;
                    }
                    curOffsetH[i] = hs;
                    curOffsetW[i] = ws;
                    ws += width[i] + 1;
                    maxW = Math.max(maxW, ws);
                    bestH = Math.max(bestH, height[i]);
                }
                hs += bestH + 1;
                if (maxW < CO && hs < CO && (long) maxW * hs < best) {
                    best = maxW * hs;
                    bestOffsetH = curOffsetH;
                    bestOffsetW = curOffsetW;
                }
            }
            int[] id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            for (int it = 0; it < 200; it++) {
                for (int i = 1; i < n; i++) {
                    int j = rand.nextInt(i + 1);
                    int t = id[i];
                    id[i] = id[j];
                    id[j] = t;
                }
                for (int i : id) if (width[i] + 1 > sw) continue loop;
                int[] curOffsetW = new int[n];
                int[] curOffsetH = new int[n];
                int ws = 0;
                int hs = 0;
                int bestH = 0;
                int maxW = 0;
                for (int i : id) {
                    if (inHole[i] >= 0) continue;
                    if (ws + width[i] + 1 >= sw) {
                        hs += bestH + 1;
                        ws = 0;
                        bestH = 0;
                    }
                    curOffsetH[i] = hs;
                    curOffsetW[i] = ws;
                    ws += width[i] + 1;
                    maxW = Math.max(maxW, ws);
                    bestH = Math.max(bestH, height[i]);
                }
                hs += bestH + 1;
                if (maxW < CO && hs < CO && (long) maxW * hs < best) {
                    best = maxW * hs;
                    bestOffsetH = curOffsetH;
                    bestOffsetW = curOffsetW;
                }
            }
        }
        final int step = 100;
        loop:
        for (int sw = 1; sw <= sumW && sw < CO; sw += step) {
            System.err.println(sw);
            int[] id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                if (width[i] > sw) continue loop;
                if ((long) (sw - step) * height[i] > best) continue loop;
            }
            itloop:
            for (int it = 0; it < 5; it++) {
                for (int j = 1; j < n; j++) {
                    int k = rand.nextInt(j + 1);
                    int t = id[j];
                    id[j] = id[k];
                    id[k] = t;
                }
                int[] curOffsetW = new int[n];
                int[] curOffsetH = new int[n];
                int ws = 0;
                int hs = 0;
                int lastx = 0, lasty = 0;
                final int step2 = 10;
                Area[] areas = new Area[n];
                for (int j = 0; j < n; j++) {
                    int i = id[j];
                    if (inHole[i] >= 0) continue;
                    all:
                    for (int y = lasty; y <= hs + step2; y += step2) {
                        for (int x = y == lasty ? lastx : 0; x + width[i] <= sw && x <= ws + step2; x += step2) {
                            lastx = x;
                            lasty = y;
                            boolean bad = false;
                            for (int k = j - 1; k >= 0; --k) {
                                if (areas[k] == null) continue;
                                Area curArea = new Area(getPolygon(i, x, y));
                                curArea.intersect(areas[k]);
                                if (!curArea.isEmpty()) {
                                    bad = true;
                                    break;
                                }
                            }
                            if (bad) continue;
                            areas[j] = new Area(getPolygon(i, x, y));
                            curOffsetH[i] = y;
                            curOffsetW[i] = x;
                            ws = Math.max(ws, x + width[i]);
                            hs = Math.max(hs, y + height[i]);
                            if ((long) ws * hs >= best) continue itloop;
                            break all;
                        }
                    }
                    if (areas[j] == null) throw new AssertionError();
                }
                if (ws < CO && hs < CO && (long) ws * hs < best) {
                    best = ws * hs;
                    bestOffsetH = curOffsetH;
                    bestOffsetW = curOffsetW;
                }
            }
        }
        if (best == Integer.MAX_VALUE) throw new AssertionError();
        alreadyHave = new Area[n];
        curOffsetH = new int[n];
        curOffsetW = new int[n];
        int all = 0;
        for (int i = 0; i < n; i++) {
            if (inHole[i] >= 0) {
                bestOffsetH[i] = bestOffsetH[inHole[i]] + shiftHoleH[i];
                bestOffsetW[i] = bestOffsetW[inHole[i]] + shiftHoleW[i];
            }
        }
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
