package coding;

import java.util.Arrays;

public class WinterAndReindeers {

    static char[] get(String[] a) {
        StringBuilder sb = new StringBuilder();
        for (String e : a) {
            sb.append(e);
        }
        return sb.toString().toCharArray();
    }

    public int getNumber(String[] allA, String[] allB, String[] allC) {
        char[] a = get(allA);
        char[] b = get(allB);
        char[] c = get(allC);
        int[] f1 = get2(a, c);
        int[] f2 = get2(b, c);
        int[][] dp1 = new int[a.length][b.length];
        int[][] dp2 = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    dp1[i][j] = i > 0 && j > 0 ? dp1[i - 1][j - 1] : 0;
                    dp1[i][j]++;
                } else {
                    if (i > 0)
                        dp1[i][j] = Math.max(dp1[i][j], dp1[i - 1][j]);
                    if (j > 0)
                        dp1[i][j] = Math.max(dp1[i][j], dp1[i][j - 1]);
                }
            }
        }
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = b.length - 1; j >= 0; j--) {
                if (a[i] == b[j]) {
                    dp2[i][j] = i + 1 < a.length && j + 1 < b.length ? dp2[i + 1][j + 1] : 0;
                    dp2[i][j]++;
                } else {
                    if (i + 1 < a.length)
                        dp2[i][j] = Math.max(dp2[i][j], dp2[i + 1][j]);
                    if (j + 1 < b.length)
                        dp2[i][j] = Math.max(dp2[i][j], dp2[i][j + 1]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int first = i > 0 && j > 0 ? dp1[i - 1][j - 1] : 0;
                int i2 = f1[i];
                int j2 = f2[j];
                if (i2 < 0 || j2 < 0) continue;
                int second = 0;
                if (i2 < a.length && j2 < b.length) second = dp2[i2][j2];
//                System.out.println(first + " " + second + " " + i + " " + j + " " + i2 + " " + j2);
                ans = Math.max(ans, first + second + c.length);
            }
        }
        return ans;
    }

    static int[] get2(char[] a, char[] b) {
        int[] ret = new int[a.length];
        all:
        for (int i = 0; i < a.length; i++) {
            int cur = i;
            for (char c : b) {
                while (cur < a.length && a[cur] != c) {
                    ++cur;
                }
                if (cur >= a.length) {
                    ret[i] = -1;
                    continue all;
                }
                ++cur;
            }
            ret[i] = cur;
        }
        return ret;
    }
}
