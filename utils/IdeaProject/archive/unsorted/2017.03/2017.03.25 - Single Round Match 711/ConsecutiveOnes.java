package coding;

public class ConsecutiveOnes {
    public long get(long n, int k) {
        String s = Long.toBinaryString(n);
        s = "0" + s;
        while (s.length() < k) s = "0" + s;
        String ones = "";
        for (int i = 0; i < k; i++) ones += "1";
        if (s.contains(ones)) {
            return n;
        }
        long ans = Long.MAX_VALUE;
        for (int pos = 0; pos + k <= s.length(); pos++) {
            String t = s.substring(0, pos) + ones;
            while (t.length() < s.length()) t += "0";
            long cur = Long.parseLong(t, 2);
            if (cur >= n) {
                ans = Math.min(ans, cur);
            }
        }
        return ans;
    }
}
