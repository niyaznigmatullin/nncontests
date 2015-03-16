package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Point[] all = new Point[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                all[i * m + j] = new Point(i, j);
            }
        }
        Arrays.sort(all, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.x + o1.y, o2.x + o2.y);
            }
        });
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += all[i].x + all[i].y + 1;
        }

        out.println(ans);
        for (int i = k - 1; i >= 0; i--) {
            int x = 0;
            int y = 0;
            out.print("(1,1)");
            while (x < all[i].x) {
                x++;
                out.print(" (" + (x + 1) + "," + (y + 1) + ")");
            }
            while (y < all[i].y) {
                y++;
                out.print(" (" + (x + 1) + "," + (y + 1) + ")");
            }
            out.println();
        }
    }
}
