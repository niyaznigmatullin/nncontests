package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Task1147 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        final int[] x1 = new int[n];
        final int[] y1 = new int[n];
        final int[] x2 = new int[n];
        final int[] y2 = new int[n];
        int[] color = new int[n];
        int maxColor = 0;
        for (int i = 0; i < n; i++) {
            x1[i] = in.nextInt();
            y1[i] = in.nextInt();
            x2[i] = in.nextInt();
            y2[i] = in.nextInt();
            color[i] = in.nextInt();
            maxColor = Math.max(maxColor, color[i]);
        }
        ++maxColor;
        int[] xs = new int[2 * n];
        for (int i = 0; i < n; i++) {
            xs[i] = x1[i];
            xs[i + n] = x2[i];
        }
        Integer[] start = new Integer[n];
        Integer[] end = new Integer[n];
        for (int i = 0; i < n; i++) {
            start[i] = end[i] = i;
        }
        Arrays.sort(start, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(y1[o1], y1[o2]);
            }
        });
        Arrays.sort(end, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(y2[o1], y2[o2]);
            }
        });
        Arrays.sort(xs);
        int last = xs[0];
        MaxSegmentTree tree = new MaxSegmentTree(n);
        int[] colors = new int[maxColor];
        for (int i = 1; i < xs.length; i++) {
            if (xs[i] == xs[i - 1]) {
                continue;
            }
            int cur = xs[i];
            int dx = cur - last;
            int lastY = -1;
            for (int j = 0, k = 0; j < n || k < n; ) {
                while (j < n && (x2[start[j]] <= last || x1[start[j]] >= cur)) {
                    ++j;
                }
                while (k < n && (x2[end[k]] <= last || x1[end[k]] >= cur)) {
                    ++k;
                }
                if (j >= n && k >= n) break;
                int got = tree.getMax(0, n);
                int curY;
                if (k >= n || j < n && y1[start[j]] <= y2[end[k]]) {
                    tree.set(start[j], start[j]);
                    curY = y1[start[j]];
                    j++;
                } else {
                    tree.set(end[k], Integer.MIN_VALUE);
                    curY = y2[end[k]];
                    k++;
                }
                if (got >= 0) {
                    colors[color[got]] += dx * (curY - lastY);
                }
                lastY = curY;
            }
            last = cur;
        }
        int sum = w * h;
        for (int i : colors) {
            sum -= i;
        }
        colors[1] += sum;
        for (int i = 0; i < maxColor; i++) {
            if (colors[i] == 0) {
                continue;
            }
            out.println(i + " " + colors[i]);
        }
    }

    static class Event {
        int rect;
        int x;
    }
}
