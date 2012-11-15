package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskE {

    static String[] ALPHABET = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        BigInteger[] dp = new BigInteger[s.length() + 1];
        dp[s.length()] = BigInteger.ONE;
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = BigInteger.ZERO;
            for (int j = 0; j < ALPHABET.length; j++) {
                if (s.substring(i).startsWith(ALPHABET[j])) {
                    dp[i] = dp[i].add(dp[i + ALPHABET[j].length()]);
                }
            }
        }
        BigInteger k = new BigInteger(in.next()).subtract(BigInteger.ONE);
        if (dp[0].compareTo(k) <= 0) {
            out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            for (int j = 0; j < ALPHABET.length; j++) {
                if (s.substring(i).startsWith(ALPHABET[j])) {
                    BigInteger val = dp[i + ALPHABET[j].length()];
                    if (val.compareTo(k) <= 0) {
                        k = k.subtract(val);
                    } else {
                        sb.append((char) (j + 'A'));
                        i += ALPHABET[j].length();
                        break;
                    }
                }
            }
        }
        out.println(sb);
    }
}
