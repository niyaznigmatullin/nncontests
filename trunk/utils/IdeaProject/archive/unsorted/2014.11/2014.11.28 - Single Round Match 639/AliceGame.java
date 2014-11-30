package coding;

import java.util.Random;

public class AliceGame {
    public long findMinimumValue(long x, long y) {
        Random rand = new Random();
        while (true) {
            int s = rand.nextInt(100);
            x = Math.abs(rand.nextLong()) % ((long) s * s + 1);
            long nx = x;
            y = (long) s * s - x;
            long n = x + y;
            long z = (long) Math.sqrt(n);
            while (z * z < n) ++z;
            while (z * z > n) --z;
            if (z * z != n) return -1;
            int ans = 0;
            for (int i = (int) z; i > 0; i--) {
                if (x >= 2 * i - 1) {
                    x -= 2 * i - 1;
                    ++ans;
                }
            }
            System.out.println(nx + " " + y);
            if (x != 0) throw new AssertionError();
        }
//        return ans;
    }
}
