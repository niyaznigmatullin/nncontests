package coding;

public class ParenthesisRemoval {
    public int countWays(String s) {
        int cl = 0;
        int ans = 1;
        final int MOD = 1000000007;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                ++cl;
            } else {
                ans = (int) ((long) ans * cl % MOD);
                cl--;
            }
        }
        return ans;
    }
}
