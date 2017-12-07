package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RememberWords {
    static class Segment {
        int left;
        int right;

        public Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    static List<Segment> getSegments(int d, int w) {
        long value1 = w - (long) d * (d - 1) / 2;
        Segment min = new Segment(value1 <= 0 ? 0 : (int) ((value1 + d - 1) / d), w / d);
        int left = 0;
        int right = w + 1;
        while (left < right - 1) {
            int mid = (left + right) >>> 1;
            long current = (long) mid * d;
            long f = Math.min(mid, d);
            current -= f * (f - 1) / 2;
            if (current > w) {
                right = mid;
            } else {
                left = mid;
            }
        }
        Segment max = new Segment((d + w - 1) / d, left);
        return new ArrayList<>(Arrays.asList(min, max));
    }

    public String isPossible(int d1, int d2, int w1, int w2) {
        List<Segment> s1 = getSegments(d1, w1);
        List<Segment> s2 = getSegments(d2, w2);
        for (Segment e : s1) {
            for (Segment f : s2) {
                if (Math.max(e.left, f.left - 1) <= Math.min(e.right, f.right + 1)) {
                    return "Possible";
                }
            }
        }
        return "Impossible";
    }
}
