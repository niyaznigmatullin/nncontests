package coding;

import java.util.Arrays;

public class ManySquares {
    public int howManySquares(int[] sticks) {
        Arrays.sort(sticks);
        int ans = 0;
        int n = sticks.length;
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && sticks[i] == sticks[j]) {
                ++j;
            }
            ans += (j - i) / 4;
            i = j;
        }
        return ans;
    }
}
