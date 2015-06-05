package coding;

public class FoxAndFlowerShopDivTwo {
    public int theMaxFlowers(String[] flowers, int r, int c) {
        int n = flowers.length;
        int m = flowers[0].length();
        int ans = 0;
        for (int x1 = 0; x1 < n; x1++) {
            for (int y1 = 0; y1 < m; y1++) {
                for (int x2 = x1 + 1; x2 <= n; x2++) {
                    for (int y2 = y1 + 1; y2 <= m; y2++) {
                        if (r >= x1 && r < x2 && c >= y1 && c < y2) {
                            continue;
                        }
                        int cur = 0;
                        for (int x = x1; x < x2; x++) {
                            for (int y = y1; y < y2; y++) {
                                if (flowers[x].charAt(y) == 'F') ++cur;
                            }
                        }
                        ans = Math.max(ans, cur);
                    }
                }
            }
        }
        return ans;
    }
}
