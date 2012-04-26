package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskD {

    static final int ALPHABET = 26;

    static int code(char a) {
        return a - 'a';
    }

    static class Word {
        char[] s;
        int[][] next;
        int[] first;

        Word(String s_) {
            this.s = s_.toCharArray();
            first = new int[ALPHABET];
            Arrays.fill(first, -1);
            for (int i = 0; i < s.length; i++) {
                int c = code(s[i]);
                if (first[c] >= 0) {
                    continue;
                }
                first[c] = i;
            }
            next = new int[ALPHABET][s.length + 1];
            for (int[] d : next) {
                Arrays.fill(d, -1);
            }
            for (int i = s.length - 1; i >= 0; i--) {
                for (int j = 0; j < ALPHABET; j++) {
                    next[j][i] = next[j][i + 1];
                }
                next[code(s[i])][i] = i;
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long time = System.currentTimeMillis();
        int n = in.nextInt();
//        int n = 1;
        Word[] a = new Word[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Word(in.next());
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < 1000000; j++) {
//                sb.append('b');
//            }
//            a[i] = new Word(sb.toString());
        }
        System.err.println(System.currentTimeMillis() - time);
        int m = in.nextInt();
//        int m = 2000;
        Word[] b = new Word[m];
        for (int i = 0; i < m; i++) {
            b[i] = a[in.nextInt() - 1];
//            b[i] = a[0];
        }
        char[] s = in.next().toCharArray();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 2000; i++) {
//            sb.append('b');
//        }
//        char[] s = sb.toString().toCharArray();
        System.err.println(System.currentTimeMillis() - time);
        int[][] dp1 = new int[s.length + 1][s.length + 1];
        int[][] dp2 = new int[s.length + 1][s.length + 1];
        for (int[] d : dp1) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        int[][] whereNext = new int[ALPHABET][m + 1];
        for (int[] d : whereNext) {
            Arrays.fill(d, -1);
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < ALPHABET; j++) {
                whereNext[j][i] = whereNext[j][i + 1];
            }
            for (int j = 0; j < ALPHABET; j++) {
                if (b[i].first[j] >= 0) {
                    whereNext[j][i] = i;
                }
            }
        }
        System.err.println(System.currentTimeMillis() - time);
        dp1[0][0] = 0;
        dp2[0][0] = 0;
        for (int i = 0; i < s.length; i++) {
            int ch = code(s[i]);
            for (int j = 0; j <= i; j++) {
                if (dp1[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dp1[i + 1][j] > dp1[i][j] || dp1[i + 1][j] == dp1[i][j] && dp2[i + 1][j] > dp2[i][j]) {
                    dp1[i + 1][j] = dp1[i][j];
                    dp2[i + 1][j] = dp2[i][j];
                }
                int next = b[dp1[i][j]].next[ch][dp2[i][j]];
                int allNext = dp1[i][j];
                if (next < 0) {
                    allNext = whereNext[ch][dp1[i][j] + 1];
                    if (allNext < 0) {
                        continue;
                    }
                    next = b[allNext].first[ch];
                }
                if (dp1[i + 1][j + 1] > allNext || dp1[i + 1][j + 1] == allNext && dp2[i + 1][j + 1] > next) {
                    dp1[i + 1][j + 1] = allNext;
                    dp2[i + 1][j + 1] = next + 1;
                }
            }
        }
        System.err.println(System.currentTimeMillis() - time);
        int answer = 0;
        for (int i = 0; i <= s.length; i++) {
            if (dp1[s.length][i] == Integer.MAX_VALUE) {
                continue;
            }
            answer = i;
        }
        out.println(answer);
        System.err.println(System.currentTimeMillis() - time);
    }
}
