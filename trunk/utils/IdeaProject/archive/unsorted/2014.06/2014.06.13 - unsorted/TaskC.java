package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int height = in.nextInt();
        int width = in.nextInt();
        int n = in.nextInt() + 1;
        int cost = in.nextInt();
        char[][] c = new char[n][];
        c[0] = new char[height * width];
        for (int i = 1; i < n; i++) {
            char[][] d = in.readCharacterFieldTokens(height, width);
            c[i] = new char[height * width];
            int cn = 0;
            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    c[i][cn++] = d[x][y];
                }
            }
        }
        int all = height * width;
        int[] d = new int[n];
        int[] from = new int[n];
        boolean[] was = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        int[] ansX = new int[n - 1];
        int[] ansY = new int[n - 1];
        int count = 0;
        int allCost = 0;
        while (true) {
            int best = -1;
            for (int i = 0; i < n; i++) {
                if (was[i] || d[i] == Integer.MAX_VALUE) continue;
                if (best < 0 || d[best] > d[i]) {
                    best = i;
                }
            }
            if (best < 0) break;
            char[] c1 = c[best];
            was[best] = true;
            allCost += d[best];
            if (best > 0) {
                ansX[count] = best;
                ansY[count] = from[best];
                ++count;
            }
            for (int i = 0; i < n; i++) {
                if (was[i]) continue;
                char[] c2 = c[i];
                int curCost = 0;
                int bestCost = all;
                for (int j = 0; curCost < bestCost && j < all; j++) {
                    if (c1[j] != c2[j]) {
                        curCost += cost;
                    }
                }
                bestCost = Math.min(bestCost, curCost);
                if (d[i] > bestCost) {
                    d[i] = bestCost;
                    from[i] = best;
                }
            }
        }
        out.println(allCost);
        for (int i = 0; i + 1 < n; i++) {
            out.println(ansX[i] + " " + ansY[i]);
        }
    }
}
