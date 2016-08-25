package coding;

import java.util.Arrays;

public class ParenthesesAndPermutation {
    public String getSequence(int[] p) {
        int n = p.length >> 1;
        int[] other = new int[2 * n];
        Arrays.fill(other, -1);
        int left = n;
        int curBalance = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 2 * n; i++) {
            if (left == 0) {
                ans.append(')');
                curBalance--;
                if (curBalance < 0) {
//                    throw new AssertionError();
                    return "Impossible";
                }
                continue;
            }
            other[p[i]] = 1;
            int minimal = 0;
            for (int j = 0, b = 0; j < 2 * n; j++) {
                b += other[j];
                minimal = Math.min(minimal, b);
            }
            if (minimal + (left - 1) * 2 < 0) {
                other[p[i]] = -1;
                ans.append(')');
                curBalance--;
                if (curBalance < 0) {
//                    throw new AssertionError();
                    return "Impossible";
                }
            } else {
                ans.append('(');
                --left;
                ++curBalance;
            }
        }
//        char[] z = new char[2 * n];
//        for (int i = 0; i < 2 * n; i++) {
//            z[p[i]] = ans.charAt(i);
//        }
//        for (int i = 0, b = 0; i < 2 * n; i++) {
//            if (z[i] == '(') b++; else b--;
//            if (b < 0) throw new AssertionError();
//            if (i + 1 == 2 * n && b != 0) throw new AssertionError();
//        }
        return ans.toString();
    }
}
