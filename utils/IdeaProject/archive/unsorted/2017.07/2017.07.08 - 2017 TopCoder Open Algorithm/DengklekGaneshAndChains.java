package coding;

import java.util.Arrays;

public class DengklekGaneshAndChains {
    public String getBestChains(String[] chains, int[] lengths) {
        int n = chains.length;
        for (int i = 0; i < n; i++) {
            String s = chains[i];
            String res = s;
            for (int j = 1; j < s.length(); j++) {
                String cur = s.substring(j) + s.substring(0, j);
                if (res.compareTo(cur) < 0) {
                    res = cur;
                }
            }
            chains[i] = res;
        }
        boolean[] was = new boolean[n];
        StringBuilder ans = new StringBuilder();
        for (int len : lengths) {
            String bestPrefix = null;
            String bestString = null;
            int pos = -1;
            for (int i = 0; i < n; i++) {
                if (was[i]) continue;
                String cur = chains[i].substring(0, len);
                if (bestPrefix == null || bestPrefix.compareTo(cur) < 0) {
                    bestPrefix = cur;
                    bestString = chains[i];
                    pos = i;
                } else if (bestPrefix.compareTo(cur) == 0) {
                    if (bestString == null || bestString.compareTo(chains[i]) > 0) {
                        bestString = chains[i];
                        pos = i;
                    }
                }
            }
            was[pos] = true;
            ans.append(bestPrefix);
        }
        return ans.toString();
    }
}
