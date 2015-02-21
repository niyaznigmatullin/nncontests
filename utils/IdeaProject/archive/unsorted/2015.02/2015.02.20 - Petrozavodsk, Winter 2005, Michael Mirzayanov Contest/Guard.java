package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Guard {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int[][] bx = new int[n][2];
        for (int i = 0; i < n; i++) {
            bx[i][0] = in.nextInt();
            bx[i][1] = in.nextInt();
        }
        int[][] by = bx.clone();
        Arrays.sort(bx, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        Arrays.sort(by, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int startX = 0;
        int endX = n - 1;
        int startY = 0;
        int endY = n - 1;
        int minX = Integer.MIN_VALUE;
        int maxX = Integer.MAX_VALUE;
        int minY = Integer.MIN_VALUE;
        int maxY = Integer.MAX_VALUE;
        int ans = 0;
        int[] P = new int[]{x0, y0};
        while (true) {
            while (startX < n && outside(bx[startX], minX, minY, maxX, maxY)) ++startX;
            while (endX >= 0 && outside(bx[endX], minX, minY, maxX, maxY)) --endX;
            while (startY < n && outside(by[startY], minX, minY, maxX, maxY)) ++startY;
            while (endY >= 0 && outside(by[endY], minX, minY, maxX, maxY)) --endY;
            if (startX >= n || endX < 0 || startY >= n || endY < 0) break;
            minX = bx[startX][0];
            maxX = bx[endX][0];
            minY = by[startY][1];
            maxY = by[endY][1];
            if (minX >= maxX || minY >= maxY || outside(P, minX, minY, maxX, maxY)) break;
            ++ans;
        }
        out.println(ans);
    }

    static boolean outside(int[] p, int minX, int minY, int maxX, int maxY) {
        return p[0] <= minX || p[0] >= maxX || p[1] <= minY || p[1] >= maxY;
    }
}
