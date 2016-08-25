package coding;

public class TrySail {
    public int get(int[] strength) {
        final int M = 256;
        boolean[][] can = new boolean[M][M];
        can[0][0] = true;
        for (int i : strength) {
            boolean[][] ncan = new boolean[M][M];
            for (int e = 0; e < M; e++) {
                for (int f = 0; f < M; f++) {
                    if (!can[e][f]) continue;
                    ncan[e ^ i][f] = true;
                    ncan[e][f ^ i] = true;
                    ncan[e][f] = true;
                }
            }
            can = ncan;
        }
        int x = 0;
        for (int i : strength) x ^= i;
        int ans = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (!can[i][j]) continue;
                int first = i;
                int second = j;
                int third = x ^ first ^ second;
                ans = Math.max(ans, first + second + third);
            }
        }
        return ans;
    }
}
