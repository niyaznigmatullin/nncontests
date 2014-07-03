package coding;

public class WinterAndMandarins {
    public int getNumber(int[] bags, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i : bags) {
            for (int j : bags) {
                int cn = 0;
                for (int e : bags) {
                    if (e >= i && e <= j) {
                        ++cn;
                    }
                }
                if (cn >= k) {
                    ans = Math.min(ans, j - i);
                }
            }
        }
        return ans;
    }
}
