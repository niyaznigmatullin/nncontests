package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;

public class ShoutterDiv1 {
    public int count(String[] s1000, String[] s100, String[] s10, String[] s1, String[] t1000, String[] t100, String[] t10, String[] t1) {
        String zz = ss(s1000);
        int[] s = new int[zz.length()];
        int[] t = new int[zz.length()];
        for (int i = 0, k = 0; i < s1000.length; i++) {
            for (int j = 0; j < s1000[i].length(); j++, k++) {
                s[k] = s1000[i].charAt(j) * 1000 + s100[i].charAt(j) * 100 + s10[i].charAt(j) * 10 + s1[i].charAt(j) - 1111 * '0';
                t[k] = t1000[i].charAt(j) * 1000 + t100[i].charAt(j) * 100 + t10[i].charAt(j) * 10 + t1[i].charAt(j) - 1111 * '0';
            }
        }
        return count(s, t);
    }

    int count(int[] s, int[] t) {
        int ans = 0;
        int[] all = new int[s.length + t.length];
        for (int i = 0; i < all.length; i++) {
            all[i] = i < s.length ? s[i] : t[i - s.length];
        }
        int[] points = ArrayUtils.sortAndUnique(all);
        int m = points.length;
        int[] minLeft = new int[m];
        for (int i = 0; i < s.length; i++) {
            s[i] = Arrays.binarySearch(points, s[i]);
            t[i] = Arrays.binarySearch(points, t[i]);
        }
        Arrays.fill(minLeft, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < s.length; j++) {
                if (t[j] >= i) {
                    minLeft[i] = Math.min(minLeft[i], s[j]);
                }
            }
        }
        int[] maxRight = new int[m];
        Arrays.fill(maxRight, Integer.MIN_VALUE);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < s.length; j++) {
                if (s[j] <= i) {
                    maxRight[i] = Math.max(maxRight[i], t[j]);
                }
            }
        }
        int first = Integer.MAX_VALUE;
        int last = Integer.MIN_VALUE;
        for (int i = 0; i < s.length; i++) {
            first = Math.min(first, t[i]);
            last = Math.max(last, s[i]);
        }
        short[][] dp = new short[m][];
        for (int i = 0; i < m; i++) {
            dp[i] = new short[i + 1];
        }
        for (int len = m - 1; len >= 0; len--) {
            for (int i = 0; i + len < m; i++) {
                int j = i + len;
                if (i <= first && j >= last) {
                    dp[j][i] = 0;
                    continue;
                }
                int cur = Short.MAX_VALUE;
                if (minLeft[i] < i && dp[j][minLeft[i]] != Short.MAX_VALUE) {
                    cur = Math.min(cur, dp[j][minLeft[i]] + 1);
                }
                if (maxRight[j] > j && dp[maxRight[j]][i] != Short.MAX_VALUE) {
                    cur = Math.min(cur, dp[maxRight[j]][i] + 1);
                }
                dp[j][i] = (short) cur;
            }
        }
        for (int i = 0; i < s.length; i++) {
            int cur = dp[t[i]][s[i]];
            for (int j = 0; j < s.length; j++) {
                if (i == j || t[j] < s[i] || t[i] < s[j]) {
                    continue;
                }
                int val = dp[Math.max(t[i], t[j])][Math.min(s[i], s[j])];
                if (val == Short.MAX_VALUE) {
                    continue;
                }
                cur = Math.min(cur, 1 + val);
            }
            if (cur == Short.MAX_VALUE) return -1;
            ans += cur;
        }
        return ans;
    }

    String ss(String[] s) {
        StringBuilder sb = new StringBuilder();
        for (String e : s) {
            sb.append(e);
        }
        return sb.toString();
    }

}
