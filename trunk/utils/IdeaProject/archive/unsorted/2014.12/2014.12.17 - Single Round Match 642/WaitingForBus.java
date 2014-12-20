package coding;

public class WaitingForBus {
    public double whenWillBusArrive(int[] time, int[] prob, int s) {
        double[] dp = new double[s];
        if (s == 0) return 0;
        dp[0] = 1.;
        double ans = 0;
        for (int i = 0; i < s; i++) {
            double val = dp[i];
            if (val == 0) continue;
            for (int j = 0; j < time.length; j++) {
                double p = val * prob[j] * .01;
                int ns = i + time[j];
                if (ns >= s) ans += (ns - s) * p; else dp[ns] += p;
            }
        }
        return ans;
    }
}
