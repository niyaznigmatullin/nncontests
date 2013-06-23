package coding;

public class TheLargestString {
    public String find(String s, String t) {
        String ans = null;
        for (int len = 1; len <= s.length(); len++) {
            String first = getBest(s, len);
            String[][] dp = new String[s.length() + 1][len + 1];
            dp[0][0] = "";
            for (int i = 1; i <= s.length(); i++) {
                dp[i][0] = "";
                for (int j = 1; j <= i && j <= len; j++) {
                    char sc = s.charAt(i - 1);
                    char tc = t.charAt(i - 1);
                    dp[i][j] = dp[i - 1][j];
                    if (sc != first.charAt(j - 1)) {
                        continue;
                    }
                    String got = dp[i - 1][j - 1];
                    if (got == null) {
                        continue;
                    }
                    got += tc;
                    dp[i][j] = max(dp[i][j], got);
                }
            }
            ans = max(ans, first + dp[s.length()][len]);
        }
        return ans;
    }

    static String getBest(String s, int len) {
        String[][] dp = new String[s.length() + 1][len + 1];
        dp[0][0] = "";
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = "";
            for (int j = 1; j <= i && j <= len; j++) {
                dp[i][j] = dp[i - 1][j];
                String got = dp[i - 1][j - 1];
                if (got == null) {
                    continue;
                }
                got += s.charAt(i - 1);
                dp[i][j] = max(dp[i][j], got);
            }
        }
        return dp[s.length()][len];
    }

    static String max(String s, String t) {
        if (s == null) return t;
        if (t == null) return s;
        return s.compareTo(t) < 0 ? t : s;
    }
}