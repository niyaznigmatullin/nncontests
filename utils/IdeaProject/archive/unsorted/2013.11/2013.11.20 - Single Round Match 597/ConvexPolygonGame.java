package coding;

import java.util.Arrays;

public class ConvexPolygonGame {

    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static long div1(long a, long b) {
        long c = a / b;
        while (c * b < a) ++c;
        while (c * b > a) --c;
        return c;
    }

    static long div2(long a, long b) {
        long c = a / b;
        while (c * b > a) --c;
        while (c * b < a) ++c;
        return c;
    }

    static int getY1(int x1, int y1, int x2, int y2, int y0) {
        return (int) (x1 + div1((long) (y0 - y1) * (x2 - x1), y2 - y1));
    }

    static int getY2(int x1, int y1, int x2, int y2, int y0) {
        return (int) (x1 + div2((long) (y0 - y1) * (x2 - x1), y2 - y1));
    }

    public String winner(int[] x, int[] y) {
        int n = x.length;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int i : y) {
            minY = Math.min(i, minY);
            maxY = Math.max(maxY, i);
        }
        int[] hx = new int[3];
        int[] hy = new int[3];
        int have = 0;
        for (int curY = minY; curY <= maxY; curY++) {
            int right = Integer.MAX_VALUE, left = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int j = (i + 1);
                if (j == n) j = 0;
                if (y[i] == y[j]) continue;
                if (y[i] <= curY && y[j] >= curY) {
                    right = getY1(x[i], y[i], x[j], y[j], curY);
                }
                if (y[i] >= curY && y[j] <= curY) {
                    left = getY2(x[i], y[i], x[j], y[j], curY);
                }
            }
            if (left == Integer.MIN_VALUE || right == Integer.MAX_VALUE) continue;
            for (int curX = left; curX <= right; curX++) {
                if (have < 2) {
                    hx[have] = curX;
                    hy[have] = curY;
                    ++have;
                } else {
                    long vmul = (long) (hx[0] - curX) * (hy[1] - curY) - (long) (hy[0] - curY) * (hx[1] - curX);
                    System.err.println(Arrays.toString(hx));
                    System.err.println(Arrays.toString(hy));
                    System.err.println(have + " " + vmul + " " + curX + " " + curY);
                    if (vmul != 0) return "Masha";
                }
            }
        }
        return "Petya";
    }
}
