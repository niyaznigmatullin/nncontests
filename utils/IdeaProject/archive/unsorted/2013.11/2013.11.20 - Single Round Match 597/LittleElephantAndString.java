package coding;

import java.util.Arrays;

public class LittleElephantAndString {
    public int getNumber(String B, String A) {
        {
            char[] c = A.toCharArray();
            Arrays.sort(c);
            char[] d = B.toCharArray();
            Arrays.sort(d);
            if (!Arrays.equals(c, d)) {
                return -1;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= A.length(); i++) {
            String z = A.substring(A.length() - i);
            int cur = 0;
            for (int c : B.toCharArray()) {
                if (cur < z.length() && z.charAt(cur) == c) {
                    ++cur;
                }
            }
            if (cur == z.length()) {
                ans = A.length() - i;
            }
        }
        return ans;

    }
}
