package coding;

import java.util.Arrays;

public class YetAnotherTwoTeamsProblem {
    public long count(int[] skill) {
        Arrays.sort(skill);
        int sum = 0;
        for (int i : skill) {
            sum += i;
        }
        long[] dp = new long[sum + 1];
        dp[0] = 1;
        int curSum = 0;
        long ans = 0;
        for (int it = skill.length - 1; it >= 0; it--) {
            int x = skill[it];
            for (int i = curSum; i >= 0; i--) {
                dp[i + x] += dp[i];
                if (i + i + x + x > sum && i + i < sum) {
                    ans += dp[i];
                }
            }
            curSum += x;
        }
        return ans;
    }
}
