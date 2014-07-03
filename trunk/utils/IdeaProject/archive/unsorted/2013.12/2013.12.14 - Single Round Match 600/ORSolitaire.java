package coding;

public class ORSolitaire {
    public int getMinimum(int[] numbers, int goal) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 30; i++) {
            if (((goal >> i) & 1) == 1) {
                int cur = 0;
                for (int x : numbers) {
                    if ((x & goal) != x) {
                        continue;
                    }
                    if (((x >> i) & 1) == 1) {
                        ++cur;
                    }
                }
                ans = Math.min(ans, cur);
            }
        }
        return ans;
    }
}
