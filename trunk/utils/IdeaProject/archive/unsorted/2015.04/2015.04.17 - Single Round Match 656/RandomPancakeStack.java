package coding;

public class RandomPancakeStack {
    public double expectedDeliciousness(int[] d) {
        int n = d.length;
        double[][] dp = new double[n + 1][n + 1];
        for (int got = n; got >= 0; got--) {
            for (int last = 0; last + got <= n; last++) {
                if (got == n) {
                    dp[got][last] = 0.;
                    continue;
                }
                int left = n - got;
                double sum = 0;
                for (int next = 0; next < last; next++) {
                    double prob = 1. / left;
                    sum += prob * (d[next] + dp[got + 1][next]);
                }
                dp[got][last] = sum;
            }
        }
        return dp[0][n];
    }
}
