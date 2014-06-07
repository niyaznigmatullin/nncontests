package coding;

public class AlternativePiles {

    static final int MOD = 1000000007;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    public int count(String C, int m) {
        int[][] dp = new int[m + 1][m];
        dp[0][0] = 1;
        for (char c : C.toCharArray()) {
            int[][] next = new int[m + 1][m];
            for (char d : "RGB".toCharArray()) {
                if (c != d && c != 'W') continue;
                for (int dif = 0; dif <= m; dif++) {
                    for (int red = 0; red < m; red++) {
                        int val = dp[dif][red];
                        if (val == 0) continue;
                        if (d == 'R') {
                            if (dif < m) {
                                int nRed = (red + 1) % m;
                                next[dif + 1][nRed] = add(next[dif + 1][nRed], val);
                            }
                        }
                        if (d == 'G') {
                            if (dif > 0) {
                                next[dif - 1][red] = add(next[dif - 1][red], val);
                            }
                        }
                        if (d == 'B') {
                            next[dif][red] = add(next[dif][red], val);
                        }
                    }
                }
            }
            dp = next;
        }
        return dp[0][0];
    }
}
