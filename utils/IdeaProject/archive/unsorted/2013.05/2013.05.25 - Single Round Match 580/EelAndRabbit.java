package coding;

import java.util.ArrayList;
import java.util.List;

public class EelAndRabbit {
    public int getmax(int[] len, int[] t) {
        int n = len.length;
        List<Integer> d = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            d.add(t[i]);
            d.add(t[i] + len[i]);
        }
        int ans = 0;
        for (int i : d) {
            for (int j : d) {
                if (i == j) {
                    continue;
                }
                int cn = 0;
                for (int k = 0; k < n; k++) {
                    if (i >= t[k] && i <= t[k] + len[k] || j >= t[k] && j <= t[k] + len[k]) {
                        ++cn;
                    }
                }
                ans = Math.max(ans, cn);
            }
        }
        return ans;
    }
}
