package coding;

import java.util.Arrays;

public class MedianFaking {
    public int[] minimize(int F, int M, int[] data, int goal) {
        int all = F * M;
        int where = (all - 1) / 2;
        int[][] have = new int[M + 1][M + 1];
        for (int i = 0; i < data.length; i += M) {
            int less = 0;
            int greater = 0;
            for (int j = 0; j < M; j++) {
                if (data[i + j] < goal) less++;
                if (data[i + j] > goal) greater++;
            }
            have[less][greater]++;
        }
        Arrays.sort(data);
        if (data[where] == goal) {
            return new int[]{0, 0};
        }
        int count = 0;
        if (data[where] < goal) {
            data[where] = goal;
            count++;
            for (int i = where + 1; i < all; i++) {
                if (data[i] < goal) {
                    data[i] = goal;
                    count++;
                }
            }
            int ans = 0;
            int cur = count;
            for (int less = M; cur > 0 && less >= 1; less--) {
                for (int gr = 0; cur > 0 && gr <= M; gr++) {
                    while (have[less][gr] > 0) {
                        --have[less][gr];
                        ans++;
                        cur -= less;
                        if (cur <= 0) {
                            break;
                        }
                    }
                }
            }
            return new int[]{ans, count};
        } else {
            data[where] = goal;
            count++;
            for (int i = where - 1; i >= 0; i--) {
                if (data[i] > goal) {
                    data[i] = goal;
                    count++;
                }
            }
            int ans = 0;
            int cur = count;
            for (int gr = M; cur > 0 && gr >= 1; gr--) {
                for (int less = 0; cur > 0 && less <= M; less++) {
                    while (have[less][gr] > 0) {
                        --have[less][gr];
                        ans++;
                        cur -= gr;
                        if (cur <= 0) break;
                    }
                }
            }
            return new int[]{ans, count};
        }
    }
}
