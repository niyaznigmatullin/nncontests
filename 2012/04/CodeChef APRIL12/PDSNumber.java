package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;
import org.omg.DynamicAny.NameDynAnyPair;

import java.util.Arrays;

public class PDSNumber {

    static final int MAXD = 10;
    static final int[] maxP = {6, 4, 2, 2};
    static int[][][][][][][] dp = new int[MAXD + 1][9 * MAXD + 1][10][maxP[0] + 1][maxP[1] + 1][maxP[2] + 1][maxP[3] + 1];
    static final int[] PRIMES = {2, 3, 5, 7};
    static final int[][] count;
    static final int[] countByDigits = new int[MAXD + 1];
    static final boolean[] isGoodSum;
    static int[][][] containsZero;

    static {
        containsZero = new int[MAXD + 1][9 * MAXD + 1][10];
        int[][] dpSum = new int[MAXD + 1][9 * MAXD + 1];
        dpSum[0][0] = 1;
        for (int i = 1; i <= MAXD; i++) {
            for (int s = 0; s <= 9 * MAXD; s++) {
                for (int cur = 0; cur < 10 && cur <= s; cur++) {
                    dpSum[i][s] += dpSum[i - 1][s - cur];
                }
            }
        }
        for (int i = 1; i <= MAXD; i++) {
            for (int s = 0; s <= 9 * i; s++) {
                containsZero[i][s][0] = dpSum[i - 1][s];
                for (int cur = 1; cur < 10 && cur <= s; cur++) {
                    int z = 0;
                    for (int last = 0; last < 10; last++) {
                        z = add(z, containsZero[i - 1][s - cur][last]);
                    }
                    containsZero[i][s][cur] = z;
                }
            }
        }
        isGoodSum = new boolean[9 * MAXD + 1];
        for (int i = 1; i < isGoodSum.length; i++) {
            int p = i;
            while (p > 1 && p % 2 == 0) {
                p /= 2;
            }
            while (p > 1 && p % 3 == 0) {
                p /= 3;
            }
            while (p > 1 && p % 5 == 0) {
                p /= 5;
            }
            while (p > 1 && p % 7 == 0) {
                p /= 7;
            }
            isGoodSum[i] = p == 1;
        }
//        long time = System.currentTimeMillis();
        count = new int[4][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                int x = i;
                int c = 0;
                while (c < maxP[j] && x % PRIMES[j] == 0) {
                    x /= PRIMES[j];
                    ++c;
                }
                count[j][i] = c;
            }
        }
        dp[0][0][0][0][0][0][0] = 1;
        for (int i = 0; i <= MAXD; i++) {
            int[][][][][][] next = i + 1 <= MAXD ? dp[i + 1] : null;
            for (int s = 0; s <= 9 * i; s++) {
                int[][][][][] all = dp[i][s];
                for (int two = 0; two <= maxP[0]; two++) {
                    for (int three = 0; three <= maxP[1]; three++) {
                        for (int five = 0; five <= maxP[2]; five++) {
                            for (int seven = 0; seven <= maxP[3]; seven++) {
                                int current = 0;
                                for (int curD = 0; curD < 10; curD++) {
                                    current = add(all[curD][two][three][five][seven], current);
                                }
                                if (current == 0) {
                                    continue;
                                }
                                for (int curD = 0; curD < 10; curD++) {
                                    if (i + 1 > MAXD || s + curD > 9 * MAXD) {
                                        continue;
                                    }
                                    int param1 = Math.min(maxP[0], two + count[0][curD]);
                                    int param2 = Math.min(maxP[1], three + count[1][curD]);
                                    int param3 = Math.min(maxP[2], five + count[2][curD]);
                                    int param4 = Math.min(maxP[3], seven + count[3][curD]);
                                    int[] z = next[s + curD][curD][param1][param2][param3];
                                    z[param4] = add(current, z[param4]);
                                }
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= MAXD; i++) {
            for (int s = 1; s <= 9 * i; s++) {
                for (int lastD = 1; lastD < 10; lastD++) {
                    if (!isGoodSum[s]) {
                        countByDigits[i] = add(countByDigits[i], containsZero[i][s][lastD]);
                        continue;
                    }
                    int t1 = 1;
                    for (int two = 0; two <= maxP[0]; two++, t1 <<= 1) {
                        int t2 = 1;
                        for (int three = 0; three <= maxP[1]; three++, t2 *= 3) {
                            int t3 = 1;
                            for (int five = 0; five <= maxP[2]; five++, t3 *= 5) {
                                int t4 = 1;
                                for (int seven = 0; seven <= maxP[3]; seven++, t4 *= 7) {
                                    int z = t1 * t2 * t3 * t4;

                                    int val = dp[i][s][lastD][two][three][five][seven];
                                    if (val == 0) {
                                        continue;
                                    }
                                    if (z % s == 0) {
                                        ans = add(ans, val);
                                        countByDigits[i] = add(countByDigits[i], val);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i < MAXD; i++) {
            for (int s = 0; s <= 9 * i; s++) {
                for (int curD = 0; curD < 10; curD++) {
                    int[][][][] all = dp[i][s][curD];
                    for (int two = maxP[0]; two > -1; two--) {
                        for (int three = maxP[1]; three > -1; three--) {
                            for (int five = maxP[2]; five > -1; five--) {
                                for (int seven = maxP[3]; seven > -1; seven--) {
                                    long got = all[two][three][five][seven];
                                    for (int mask = 1; mask < 16; mask++) {
                                        int mul = (Integer.bitCount(mask) & 1) == 0 ? -1 : 1;
                                        int newTwo = (mask & 1) + two;
                                        int newThree = ((mask >> 1) & 1) + three;
                                        int newFive = ((mask >> 2) & 1) + five;
                                        int newSeven = ((mask >> 3) & 1) + seven;
                                        if (newTwo <= maxP[0] && newThree <= maxP[1] && newFive <= maxP[2] && newSeven <= maxP[3]) {
                                            got += all[newTwo][newThree][newFive][newSeven] * mul;
                                        }
                                    }
                                    all[two][three][five][seven] = got > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) got;
                                }
                            }
                        }
                    }
                }
            }
        }

//        System.err.println(ans);
//        System.err.println(System.currentTimeMillis() - time);
    }

    static int add(int a, int b) {
        a += b;
        if (a < 0) {
            return Integer.MAX_VALUE;
        }
        return a;
    }

    static int getP(long x) {
        return x == 0 ? 1 : (int) (x % 10) * getP(x / 10);
    }

    static int getS(long x) {
        return x == 0 ? 0 : (int) (x % 10) + getS(x / 10);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        int c = 300002;
//        System.err.println(solve(c - 1));
//        for (long i = 1; ; i++) {
//            if ((i & ((1048576 << 4) - 1)) == 0) {
//                System.err.println(i);
//            }
//            if (getP(i) % getS(i) == 0) {
//                --c;
//                if (c == 0) {
//                    System.err.println("ans[1000000000] = " + i);
//                    break;
//                }
//            }
//        }
        solve(19);
        while (true) {
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            out.println(solve(n - 1));
        }
//        out.println("WA");
    }

    static long solve(int n) {
        return solveStupid(n);
    }

    static long solveStupid(int n) {
//        System.err.println("Test = " + n);
        int currentAdd = 0;
        int d = getCountOfDigits(n);
        for (int i = 1; i < d; i++) {
            n -= countByDigits[i];
        }
//        System.err.println("d = " + d);
        int[] answer = new int[d];
        Arrays.fill(answer, -1);
        int dif1 = 0;
        int dif2 = 0;
        int dif3 = 0;
        int dif4 = 0;
        boolean weContainsZero = false;
        for (int i = 0; i < d; i++) {
            if (weContainsZero) {
                for (int j = d - 1; j >= i; j--) {
                    answer[j] = n % 10;
                    n /= 10;
                }
                break;
            }
            for (int firstDigit = i == 0 ? 1 : 0; firstDigit < 10; firstDigit++) {
                int current = 0;
                for (int sum = 0; sum + currentAdd <= 9 * d && sum <= 9 * (d - i); sum++) {
                    if (sum + currentAdd == 0) {
                        continue;
                    }
                    if (!isGoodSum[sum + currentAdd]) {
                        current = add(current, containsZero[d - i][sum][firstDigit]);
                        continue;
                    }
                    int t1 = get(sum + currentAdd, 2);
                    int t2 = get(sum + currentAdd, 3);
                    int t3 = get(sum + currentAdd, 5);
                    int t4 = get(sum + currentAdd, 7);
                    t1 = Math.max(0, t1 - dif1);
                    t2 = Math.max(0, t2 - dif2);
                    t3 = Math.max(0, t3 - dif3);
                    t4 = Math.max(0, t4 - dif4);
                    t1 = Math.min(t1, maxP[0]);
                    t2 = Math.min(t2, maxP[1]);
                    t3 = Math.min(t3, maxP[2]);
                    t4 = Math.min(t4, maxP[3]);
                    if (d - i == MAXD) {
                        for (int two = t1; two <= maxP[0]; two++) {
                            for (int three = t2; three <= maxP[1]; three++) {
                                for (int five = t3; five <= maxP[2]; five++) {
                                    for (int seven = t4; seven <= maxP[3]; seven++) {
                                        current = add(current, dp[d - i][sum][firstDigit][two][three][five][seven]);
                                    }
                                }
                            }
                        }
                    } else {
                        current = add(current, dp[d - i][sum][firstDigit][t1][t2][t3][t4]);
                    }
                }
//                System.err.println(firstDigit + " " + current + " " + n + " " + (d - i));
                if (current <= n) {
                    n -= current;
                } else {
                    answer[i] = firstDigit;
                    break;
                }
            }
//            System.err.println("ans[" + i + "] = " + answer[i]);
            if (answer[i] < 0) {
                throw new AssertionError();
            }
            currentAdd += answer[i];
            dif1 += get(answer[i], 2);
            dif2 += get(answer[i], 3);
            dif3 += get(answer[i], 5);
            dif4 += get(answer[i], 7);
            if (answer[i] == 0) {
                weContainsZero = true;
            }
        }
        long ret = 0;
        for (int i = 0; i < d; i++) {
            ret = ret * 10 + answer[i];
        }
        return ret;
    }

    private static int getCount1(int currentAdd, int d, int i, int dif1, int dif2, int dif3, int dif4, int mid) {
        int current = 0;
        for (int sum = 1; sum + currentAdd <= 9 * MAXD; sum++) {
            int t1 = get(sum + currentAdd, 2);
            int t2 = get(sum + currentAdd, 3);
            int t3 = get(sum + currentAdd, 5);
            int t4 = get(sum + currentAdd, 7);
            t1 = Math.max(0, t1 - dif1);
            t2 = Math.max(0, t2 - dif2);
            t3 = Math.max(0, t3 - dif3);
            t4 = Math.max(0, t4 - dif4);
            for (int two = t1; two <= maxP[0]; two++) {
                for (int three = t2; three <= maxP[1]; three++) {
                    for (int five = t3; five <= maxP[2]; five++) {
                        for (int seven = t4; seven <= maxP[3]; seven++) {
                            current = add(current, dp[d - i][sum][mid][two][three][five][seven]);
                        }
                    }
                }
            }
        }
        return current;
    }

    static int get(int sum, int i) {
        int ret = 0;
        while (ret < maxP[0] && sum % i == 0) {
            sum /= i;
            ++ret;
        }
        return ret;
    }

    static int getCountOfDigits(int n) {
        for (int i = 1; ; i++) {
            if (n >= countByDigits[i]) {
                n -= countByDigits[i];
            } else {
                return i;
            }
        }
    }

    static int getD(int n) {
        return n == 0 ? 0 : getD(n / 10) + 1;
    }

}
