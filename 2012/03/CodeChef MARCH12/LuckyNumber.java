package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LuckyNumber {


    static int[] luckyNumbers;
    static int[][] dp;
    static final int MAXLEN = 1001;
    static final int MOD = 1000000007;

    static {
        List<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(7);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 2; j++) {
                int add = list.get(i) * 10 + list.get(j);
                if (add < MAXLEN) {
                    list.add(add);
                }
            }
        }
        luckyNumbers = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            luckyNumbers[i] = list.get(i);
        }
        dp = new int[MAXLEN][MAXLEN];
        dp[0][0] = 1;
        for (int i = 1; i < MAXLEN; i++) {
            for (int j = 0; j <= i; j++) {
                int lucky = 2;
                int nonLucky = 8;
                if (j > 0) {
                    dp[i][j] = (int) ((dp[i][j] + (long) dp[i - 1][j - 1] * lucky) % MOD);
                }
                dp[i][j] = (int) ((dp[i][j] + (long) dp[i - 1][j] * nonLucky) % MOD);
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String a = in.next();
        String b = in.next();
        {
            char[] c = b.toCharArray();
            int i = c.length - 1;
            while (i >= 0 && c[i] == '9') {
                c[i] = '0';
                i--;
            }
            if (i < 0) {
                c = new char[c.length + 1];
                Arrays.fill(c, '0');
                c[0] = '1';
            } else {
                c[i]++;
            }
            b = new String(c);
        }
        int answer = solve(b) - solve(a);
        if (answer < 0) {
            answer += MOD;
        }
        out.println(answer);
    }

    static int solve(String s) {
        int answer = 0;
        for (int countLucky : luckyNumbers) {
            int leftLucky = countLucky;
            for (int i = 0; leftLucky >= 0 && i < s.length(); i++) {
                int d = s.charAt(i) - '0';
                for (int curD = 0; curD < d; curD++) {
                    answer = answer + getCount(s.length() - i - 1, leftLucky - (curD == 4 || curD == 7 ? 1 : 0));
                    if (answer >= MOD) {
                        answer -= MOD;
                    }
                }
                if (d == 4 || d == 7) {
                    leftLucky--;
                }
            }
        }
        return answer;
    }

    static int getCount(int length, int lucky) {
        if (lucky < 0 || lucky > length) {
            return 0;
        }
        return dp[length][lucky];
    }
}
