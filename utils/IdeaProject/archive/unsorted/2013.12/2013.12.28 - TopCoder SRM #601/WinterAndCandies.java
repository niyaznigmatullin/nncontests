package coding;

public class WinterAndCandies {
    public int getNumber(int[] type) {
        int ans = 0;
        for (int i = 1; i <= type.length; i++) {
            int ways = 1;
            for (int j = 1; j <= i; j++) {
                int cn = 0;
                for (int k : type) {
                    if (k == j) ++cn;
                }
                ways *= cn;
            }
            ans += ways;
        }
        return ans;
    }
}
