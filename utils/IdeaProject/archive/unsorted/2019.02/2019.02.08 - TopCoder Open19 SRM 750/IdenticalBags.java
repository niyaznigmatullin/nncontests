package coding;

public class IdenticalBags {
    public long makeBags(long[] candy, long bagSize) {
        long mx = 0;
        for (long e : candy) mx = Math.max(mx, e);
        long l = 0;
        long r = mx + 1;
        while (l < r - 1) {
            long mid = (l + r) >> 1;
            long size = 0;
            for (long e : candy) {
                size += e / mid;
                if (size >= bagSize) break;
            }
            if (size >= bagSize) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
