package coding;

public class LeftToRightGame {
    public String whoWins(int length, int divisor) {
        boolean[][] dp = new boolean[length + 1][divisor];
        if (divisor == 1) {
            return "Bob";
        }
        if (length % 2 == 1) return "Alice";
        dp[0][0] = true;
        for (int i = 1; i <= length; i++) {
            for (int mod = 0; mod < divisor; mod++) {
                for (int digit = 0; digit < 10; digit++) {
                    if (i == length && digit == 0) continue;
                    dp[i][mod] |= !dp[i - 1][(mod * 10 + digit) % divisor];
                }
            }
        }
        return dp[length][0] ? "Alice" : "Bob";
    }
}
