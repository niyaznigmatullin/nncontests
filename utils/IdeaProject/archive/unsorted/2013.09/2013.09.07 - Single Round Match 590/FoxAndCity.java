package coding;

import java.util.Arrays;

public class FoxAndCity {

    public int minimalCost(String[] linked, int[] want) {
        this.want = want;
        int n = want.length;
        a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (linked[i].charAt(j) == 'Y') {
                    a[i][j] = 1;
                }
            }
            a[i][i] = 0;
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
        answer = 0;
        for (int i = 0; i < n; i++) {
            int cur = want[i] - a[0][i];
            answer += cur * cur;
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = 1;
            right[i] = a[0][i];
        }
        lefts = new int[n + 1][n];
        rights = new int[n + 1][n];
        lefts[1] = left;
        rights[1] = right;
        go(1, n, 0);
        return answer;
    }

    int answer;
    int[] want;
    int[][] lefts;
    int[][] rights;
    int[][] a;

    void go(int x, int n, int current) {
        int[] left = lefts[x];
        int[] right = rights[x];
        int minCurrent = current;
        for (int i = x; i < n; i++) {
            if (left[i] > right[i]) return;
            if (want[i] < left[i]) minCurrent += (want[i] - left[i]) * (want[i] - left[i]);
            if (want[i] > right[i]) minCurrent += (want[i] - right[i]) * (want[i] - right[i]);
        }
        if (minCurrent >= answer)return;
        if (x == n) {
            answer = current;
            return;
        }
        int[] nleft = lefts[x + 1];
        int[] nright = rights[x + 1];
        for (int i = left[x]; i <= right[x]; i++) {
            for (int j = x + 1; j < n; j++) {
                nleft[j] = left[j];
                nright[j] = right[j];
            }
            for (int j = x + 1; j < n; j++) {
                nright[j] = Math.min(nright[j], i + a[x][j]);
                nleft[j] = Math.max(nleft[j], i - a[x][j]);
            }
            go(x + 1, n, current + (i - want[x]) * (i - want[x]));
        }
    }
}
