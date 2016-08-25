package coding;

public class DistinguishableSetDiv1 {
    public int count(String[] answer) {
        int m = answer.length;
        int n = answer[0].length();
        int[] bad = new int[1 << n];
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int mask = 0;
                for (int f = 0; f < answer[i].length(); f++) {
                    if (answer[i].charAt(f) == answer[j].charAt(f)) {
                        mask |= 1 << f;
                    }
                }
                bad[mask] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int mask = (1 << n) - 1; mask >= 0; mask--) {
                if (((mask >> i) & 1) == 1) {
                    bad[mask & ~(1 << i)] += bad[mask];
                }
            }
        }
        int ans = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            ans += bad[mask] > 0 ? 1 : 0;
        }
        return (1 << n) - ans;
    }
}
