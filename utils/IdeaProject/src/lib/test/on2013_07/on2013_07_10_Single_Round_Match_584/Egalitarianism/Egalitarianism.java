package lib.test.on2013_07.on2013_07_10_Single_Round_Match_584.Egalitarianism;



import java.util.Arrays;

public class Egalitarianism {
    public int maxDifference(String[] isFriend, int d) {
        int n = isFriend.length;
        int[][] a = new int[n][n];
        for (int[] e : a) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) a[i][i] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isFriend[i].charAt(j) != 'Y' || i == j) continue;
                a[i][j] = 1;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] != Integer.MAX_VALUE && a[k][j] != Integer.MAX_VALUE) {
                        a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == Integer.MAX_VALUE) {
                    return -1;
                }
                ans = Math.max(ans, a[i][j]);
            }
        }
        return ans * d;
    }
}
