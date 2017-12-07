package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class ChromosomalCrossoverTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> ret = new ArrayList<>();
        Random rng = new Random(50216);
        for (int it = 0; it < 300; it++) {
            int n = rng.nextInt(10) + 5;
            String s = gen(n, rng);
            String t = gen(n, rng);
            int ans = 0;
            for (int mask = 0; mask < 1 << n; mask++) {
                String s1 = "";
                String s2 = "";
                for (int i = 0; i< n; i++) {
                    if (((mask >> i) & 1) == 0) {
                        s1 += s.charAt(i);
                        s2 += t.charAt(i);
                    } else {
                        s1 += t.charAt(i);
                        s2 += s.charAt(i);
                    }
                }
                ans = Math.max(getLCS(s1, s2), ans);
            }
            ret.add(new NewTopCoderTest(new Object[]{s, t}, ans));
        }
        return ret;
    }

    static String gen(int n, Random rng) {
        char[] c = new char[n];
        for (int i = 0; i < n; i++) {
            c[i] = "ATGC".charAt(rng.nextInt(4));
        }
        return new String(c);
    }

    static int getLCS(String a, String b) {
        int[][] dp = new int[a.length()][b.length()];
        int ans = 0;
        for (int i = 0; i < a.length(); i++){
            for (int j = 0; j< b.length(); j++) {
                if (a.charAt(i) != b.charAt(j)) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (i > 0 && j > 0 ? dp[i - 1][j - 1] : 0) + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
