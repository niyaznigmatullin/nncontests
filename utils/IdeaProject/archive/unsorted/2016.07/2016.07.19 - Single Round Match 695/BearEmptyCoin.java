package coding;

import java.math.BigInteger;

public class BearEmptyCoin {
    public long winProbability(int K, int S) {
        if (S % K == 0) {
            return (1L << K);
        }
        long[][] C = new long[K + 1][K + 1];
        for (int i = 0; i <= K; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        long ans = 0;
        long best = 0;
        final int MAX = 200000;
        for (int i = S / K - 1000; i <= S / K + 1000; i++) {
            long z = S - i * K;
            long curAns = 0;
            for (int j = 1; j <= K; j++) {
                if (j == K) {
                    if (z == 0) {
                        ++curAns;
                    }
                    continue;
                }
                long mx = 0;
                for (int e = 1; e + j <= K; e++) {
                    if (z % e == 0) {
                        mx = Math.max(mx, C[K - j - 1][e - 1]);
                    }
                }
                curAns += mx;
            }
            if (ans < curAns) {
                ans = curAns;
                best = i;
            }
        }
        for (int i = MAX + 1; i <= 10 * MAX; i++) {
            long z = S - i * K;
            long curAns = 0;
            for (int j = 1; j <= K; j++) {
                if (j == K) {
                    if (z == 0) {
                        ++curAns;
                    }
                    continue;
                }
                long mx = 0;
                for (int e = 1; e + j <= K; e++) {
                    if (z % e == 0) {
                        mx = Math.max(mx, C[K - j - 1][e - 1]);
                    }
                }
                curAns += mx;
            }
            if (ans < curAns) {
                System.out.println(curAns + " " + ans + " " + i);
                ans = curAns;
                best = i;
                throw new AssertionError();
            }
        }
        System.out.println(best);
        return ans * 2;
//        return C[K][K / 2];
    }
}
