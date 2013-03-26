import java.util.*;

public class Mrep {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int[][] dp = new int[s.length()][s.length()];
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i > 0 && j > 0) dp[i][j] = dp[i - 1][j - 1] + 1; else dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }
                if (i != j && dp[i][j] >= 20 && (i + 1 >= s.length() || j + 1 >= s.length() || s.charAt(i + 1) != s.charAt(j + 1))) {
                    ans.add(s.substring(i - dp[i][j] + 1, i + 1));
                }
            }
        }
        for (String e : ans) {
            System.out.println(e);   
        }
    } 
}