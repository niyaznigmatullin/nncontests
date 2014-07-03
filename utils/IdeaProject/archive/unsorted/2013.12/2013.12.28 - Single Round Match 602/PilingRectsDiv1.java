package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class PilingRectsDiv1 {
    public long getmax(int n, int[] XS, int[] YS, int XA, int XB, int XC, int YA, int YB, int YC) {
        int[] x = new int[2 * n];
        int[] y = new int[2 * n];
        for (int i = 0; i < XS.length; i++) {
            x[i] = XS[i];
            y[i] = YS[i];
        }
        for (int i = XS.length; i < 2 * n; i++) {
            x[i] = (int) (((long) XA * x[i - 1] + XB) % XC + 1);
            y[i] = (int) (((long) YA * y[i - 1] + YB) % YC + 1);
        }
        Rectangle[] a = new Rectangle[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            a[i] = new Rectangle(x[i], y[i]);
        }
        Arrays.sort(a, new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                if (o1.x != o2.x) return Integer.compare(o1.x, o2.x);
                return Integer.compare(o1.y, o2.y);
            }
        });
        if (n == 1) {
            return (long) a[0].x * a[0].y + (long) a[1].x * a[1].y;
        }
        int[] minY = new int[2 * n];
        minY[0] = a[0].y;
        for (int i = 1; i < 2 * n; i++) {
            minY[i] = Math.min(minY[i - 1], a[i].y);
        }
        ys = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) ys[i] = a[i].y;
        ys = ArrayUtils.sortAndUnique(ys);
        f = new Fenwick(ys.length);
        long ans = 0;
        for (int i = 2 * n - 1; i > 0; i--) {
            int first = i;
            if (first == n) {
                int id = getKth(1);
                ans = Math.max(ans, (long) a[0].x * minY[i - 1] + (long) a[i].x * Math.min(ys[id], a[i].y));
            } else if (first < n) {
                {
                    int toGet = n - first;
                    int id = getKth(toGet + 1);
                    int fId = getKth(1);
                    ans = Math.max(ans, (long) a[0].x * Math.min(minY[i - 1], ys[fId]) + (long) a[i].x * Math.min(a[i].y, ys[id]));
                }
                {
                    int toGet = n - 1;
                    int id = getKth(toGet + 1);
                    int fId = getKth(1);
                    ans = Math.max(ans, (long) a[0].x * Math.min(minY[i - 1], ys[id]) + (long) a[i].x * Math.min(a[i].y, ys[fId]));
                }

            }
            f.add(Arrays.binarySearch(ys, a[i].y), 1);
        }
        return ans;
    }

    static int getKth(int k) {
        if (f.getSum(0, ys.length) < k) {
            return Integer.MIN_VALUE;
        }
        int l = -1;
        int r = ys.length;
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (f.getSum(0, mid) < k) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    static int[] ys;
    static Fenwick f;

    static class Rectangle {
        int x;
        int y;

        Rectangle(int x, int y) {
            if (x > y) {
                int t = x;
                x = y;
                y = t;
            }
            this.x = x;
            this.y = y;
        }
    }
}
