package lib.test.on2013_01.on2013_01_01_January_Challenge_2013.Dividing_Products;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class DividingProducts {

    static int[] MAXPOWER = {54, 36, 18, 18};
    static int[][] NUMBERS = {{}, {}, {0}, {1}, {0, 0}, {2}, {0, 1}, {3}, {0, 0, 0}, {1, 1}};

//    static int[] current;
//
//    static int go(int v, int n, int left) {
//        if (v == n) {
//            return 1;
//        }
//        int ret = 0;
//        for (int count = 0; count <= left; count++) {
//            boolean ok = true;
//            for (int j : NUMBERS[v]) {
//                current[j] += count;
//                if (current[j] > MAXPOWER[j]) {
//                    ok = false;
//                }
//            }
//            if (ok) {
//                ret += go(v + 1, n, left - count);
//            }
//            for (int j : NUMBERS[v]) {
//                current[j] -= count;
//            }
//        }
//        if (ret > 10000)
//            System.out.println(ret);
//        return ret;
//    }
//
//    static {
//        long num = 1;
//        int count = 0;
//        for (int i = 0; i <= MAXPOWER[0]; i++) {
//            long num2 = 1;
//            for (int j = 0; j <= MAXPOWER[1]; j++) {
//                long num3 = num2;
//                for (int k = 0; k <= MAXPOWER[2]; k++) {
//                    long num4 = num3;
//                    for (int l = 0; l <= MAXPOWER[3]; l++) {
//                        ++count;
//                        num4 *= 7;
//                        if (num4 > 1e18) {
//                            break;
//                        }
//                    }
//                    num3 *= 5;
//                    if (num3 > 1e18) {
//                        break;
//                    }
//                }
//                num2 *= 3;
//                if (num2 > 1e18) {
//                    break;
//                }
//            }
//            num *= 2;
//            if (num > 1e18) {
//                break;
//            }
//        }
//        int count2 = 0;
//        for (int i = 0; i <= MAXPOWER[0]; i++) {
//            for (int j = 0; j + i <= 18 && j <= MAXPOWER[1]; j++) {
//                for (int k = 0; k + j + i <= 18 && k <= MAXPOWER[2]; k++) {
//                    for (int l = 0; l + k + j + i <= 18 && l <= MAXPOWER[3]; l++) {
//                        ++count2;
//                    }
//                }
//            }
//        }
//        System.err.println(count + " " + count2);
//        current = new int[4];
////        System.err.println(go(1, 10, 18));
//    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        int[] length = new int[t];
        long[] v = new long[t];
        for (int i = 0; i < t; i++) {
            length[i] = in.nextInt();
            v[i] = in.nextLong();
        }
        int[] answer = solution1(length, v);
        for (int i : answer) {
            int i1 = i >>> 16;
            int i2 = i & 0xFFFF;
            out.println(((long) i1 << 16) | i2);
        }
    }

    static int[] solution1(int[] length, long[] v) {
        int[][][][] dp = new int[1][1][1][1];
        dp[0][0][0][0] = 1;
        int maximalLen = 0;
        for (int i : length) {
            maximalLen = Math.max(maximalLen, i);
        }
        int[] d1 = new int[maximalLen + 1];
        int[] z1 = new int[maximalLen + 1];
        for (int i = 0; i <= maximalLen; i++) {
            int plus1 = (i + 1) / 2;
            int minus1 = i / 2;
            int plus2 = (maximalLen + 1) / 2 - plus1;
            d1[i] = Math.min(plus2, minus1);
            z1[i] = plus1;
        }
        int[] last = new int[maximalLen + 1];
        Arrays.fill(last, -1);
        int m = length.length;
        int[] nextQ = new int[m];
        for (int i = 0; i < m; i++) {
            nextQ[i] = last[length[i]];
            last[length[i]] = i;
        }
        int[] primes = new int[]{2, 3, 5, 7};
        int[] answer = new int[m];
        int[] pow = new int[4];
        int[] pow10 = new int[maximalLen + 1];
        pow10[0] = 1;
        for (int i = 1; i <= maximalLen; i++) {
            pow10[i] = pow10[i - 1] * 10;
        }
        int[] pow9 = new int[maximalLen + 1];
        pow9[0] = 1;
        for (int i = 1; i <= maximalLen; i++) {
            pow9[i] = pow9[i - 1] * 9;
        }
        for (int currentLength = 1; currentLength <= maximalLen; currentLength++) {
            int curD = d1[currentLength];
            int curZ = z1[currentLength];
            int prevD = d1[currentLength - 1];
            int prevZ = z1[currentLength - 1];
            int[][][][] next = new int[3 * (curD + curZ) + 1][2 * (curD + curZ) + 1]
                    [(curD + curZ) + 1][(curD + curZ) + 1];
            int delta = (currentLength & 1) == 1 ? 1 : -1;
            int c3 = curZ + curD;
            int c1 = 3 * c3;
            int c2 = 2 * c3;
            for (int two = 0; two < dp.length; two++) {
                for (int three = 0; three < dp[two].length; three++) {
                    int[][] dp2 = dp[two][three];
                    for (int five = 0; five < dp2.length; five++) {
                        for (int seven = 0; seven < dp2[five].length; seven++) {
                            int val = dp2[five][seven];
                            if (val == 0) {
                                continue;
                            }
                            for (int d = 1; d < 10; d++) {
                                int nTwo = two - prevD * 3;
                                int nThree = three - prevD * 2;
                                int nFive = five - prevD;
                                int nSeven = seven - prevD;
                                for (int i : NUMBERS[d]) {
                                    if (i == 0) nTwo += delta;
                                    else if (i == 1) nThree += delta;
                                    else if (i == 2) nFive += delta;
                                    else
                                        nSeven += delta;
                                }
                                nTwo += curD * 3;
                                nThree += curD * 2;
                                nFive += curD;
                                nSeven += curD;
                                if (nTwo >= 0 && nThree >= 0 && nFive >= 0 && nSeven >= 0 &&
                                        nTwo <= c1 && nThree <= c2 &&
                                        nFive <= c3 && nSeven <= c3) {
                                    next[nTwo][nThree][nFive][nSeven] += val;
                                }
                            }
                        }
                    }
                }
            }
            dp = next;
            for (int query = last[currentLength]; query >= 0; query = nextQ[query]) {
                long q = v[query];
                int len = length[query];
                if (q == 0) {
                    int plus = (len + 1) / 2;
                    int minus = len / 2;
                    answer[query] = 9 * pow9[minus] * (pow10[plus - 1] - pow9[plus - 1]);
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int p = primes[i];
                    int count = 0;
                    while (q % p == 0) {
                        ++count;
                        q /= p;
                    }
                    pow[i] = count;
                }
                if (q == 1 && pow[0] <= z1[len] * 3 && pow[1] <= z1[len] * 2 && pow[2] <= z1[len]
                        && pow[3] <= z1[len]) {
                    answer[query] = dp[pow[0] + 3 * d1[len]][pow[1]+ 2 * d1[len]][pow[2] + d1[len]][pow[3] + d1[len]];
                } else {
                    answer[query] = 0;
                }
            }
        }
        return answer;
    }
}
