package lib.test.on2013_07.on2013_07_19_Single_Round_Match_585.TrafficCongestion;



public class TrafficCongestion {
    public int theMinCars(int n) {
        long ans = 1;
        final int MOD = 1000000007;
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 1) {
                ans = (ans + MOD - 1) * 2 + 1;
            } else {
                ans = ans * 2 + 1;
            }
            ans %= MOD;
        }
        return (int) ans;
    }
}
