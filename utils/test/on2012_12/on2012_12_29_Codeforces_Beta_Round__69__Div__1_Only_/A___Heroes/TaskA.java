package lib.test.on2012_12.on2012_12_29_Codeforces_Beta_Round__69__Div__1_Only_.A___Heroes;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        List<String> heroes = new ArrayList<String>(Arrays.asList("Anka", "Chapay", "Cleo", "Troll", "Dracul", "Snowy", "Hexadecimal"));
        int m = in.nextInt();
        int n = heroes.size();
        boolean[][] like = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int x = heroes.indexOf(in.next());
            in.next();
            int y = heroes.indexOf(in.next());
            like[x][y] = true;
        }
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int bestDifference = Integer.MAX_VALUE;
        int bestCount = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j + i <= n; j++) {
                for (int k = 1; k + j + i <= n; k++) {
                    int max = Math.max(a / i, Math.max(b / j, c / k));
                    int min = Math.min(a / i, Math.min(b / j, c / k));
                    if (bestDifference < max - min) {
                        continue;
                    }
                    for (int mask1 = 0; mask1 < 1 << n; mask1++) {
                        if (Integer.bitCount(mask1) != i) {
                            continue;
                        }
                        int like1 = getLike(like, mask1);
                        int leftMask = ((1 << n) - 1) ^ mask1;
                        for (int mask2 = leftMask; mask2 > 0; mask2 = (mask2 - 1 & leftMask)) {
                            if (Integer.bitCount(mask2) != j) {
                                continue;
                            }
                            int mask3 = ((1 << n) - 1) ^ mask1 ^ mask2;
                            if (Integer.bitCount(mask3) != k) {
                                continue;
                            }
                            int allLike = like1 + getLike(like, mask2) + getLike(like, mask3);
                            if (bestDifference > max - min || bestDifference == max - min && bestCount < allLike) {
                                bestCount = allLike;
                                bestDifference = max - min;
                            }
                        }
                    }
                }
            }
        }
        out.println(bestDifference + " " + bestCount);
    }

    static int getLike(boolean[][] a, int mask) {
        int ret = 0;
        for (int i = 0; i < a.length; i++) {
            if (((mask >> i) & 1) == 0) {
                continue;
            }
            for (int j = 0; j < a.length; j++) {
                if (((mask >> j) & 1) == 0) {
                    continue;
                }
                ret += a[i][j] ? 1 : 0;
            }
        }
        return ret;
    }
}
