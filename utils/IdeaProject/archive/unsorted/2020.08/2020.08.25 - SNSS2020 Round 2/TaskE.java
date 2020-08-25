package coding;

import ru.ifmo.niyaz.DataStructures.LayeredRangeTree2D;
import ru.ifmo.niyaz.DataStructures.SuffixArray;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String str = in.next();
        int q = in.nextInt();
        int[] s = new int[str.length() + 1];
        for (int i = 0; i < str.length(); i++) {
            s[i] = str.charAt(i);
        }
        int[] sa = SuffixArray.buildSuffixArray(s);
        LayeredRangeTree2D tree = new LayeredRangeTree2D(sa);
        for (int cq = 0; cq < q; cq++) {
            int from = in.nextInt();
            int to = in.nextInt() + 1;
            String wtr = in.next();
            int[] w = new int[wtr.length()];
            for (int i = 0; i < wtr.length(); i++) w[i] = wtr.charAt(i);
            int left = -1;
            int right = sa.length;
            while (left < right - 1) {
                int mid = (left + right) >> 1;
                int res = compare(s, w, sa[mid]);
                if (res <= 0) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            int first = right;
            left = -1;
            right = sa.length;
            while (left < right - 1) {
                int mid = (left + right) >> 1;
                int res = compare(s, w, sa[mid]);
                if (res < 0) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            int last = left;
            if (from >= to - wtr.length() + 1) {
                out.println(0);
            } else {
                out.println(tree.count(first, last + 1, from, to - wtr.length() + 1));
            }
        }
    }

    private int compare(int[] s, int[] w, int start) {
        int res = 0;
        for (int j = 0; j < w.length; j++) {
            int c = w[j] - s[start + j];
            if (c != 0) {
                res = c;
                break;
            }
        }
        return res;
    }
}
