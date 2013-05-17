package lib.test.on2013_02.on2013_02_17_Facebook_HackerCup_Round_3.NameTheBaby;



import ru.ifmo.niyaz.DataStructures.AhoCorasick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class NameTheBaby {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("test " + testNumber);
        int length = in.nextInt();
        long k = in.nextLong();
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        out.println("Case #" + testNumber + ": " + solve(length, k, n, s));
    }

    static String solve(int length, long k, int n, String[] s) {
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = new int[s[i].length()];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = s[i].charAt(j) - 'a';
            }
        }
        AhoCorasick ac = new AhoCorasick(a, alphabet);
        long[][] dp = new long[ac.countNodes()][length + 1];
        for (int i = 0; i < dp.length; i++) {
            if (ac.hasTerminal(i)) {
                dp[i][0] = 1;
            }
        }
        long[] pows = new long[length + 1];
        pows[0] = 1;
        for (int i = 1; i <= length; i++) {
            pows[i] = mul(pows[i - 1], alphabet);
        }
        for (int i = 1; i <= length; i++) {
            for (int v = 0; v < dp.length; v++) {
                if (ac.hasTerminal(v)) {
                    dp[v][i] = pows[i];
                } else {
                    for (int d = 0; d < alphabet; d++) {
                        dp[v][i] = add(dp[v][i], dp[ac.getLink(v, d)][i - 1]);
                    }
                }
            }
        }
        if (dp[0][length] < k) {
            return "unnamed baby :(";
        }
        k--;
        char[] ans = new char[length];
        int v = 0;
        boolean haveTerminal = false;
        for (int i = 0; i < length; i++) {
            boolean found = false;
            for (int letter = 0; letter < alphabet; letter++) {
                int u = ac.getLink(v, letter);
                if (haveTerminal) {
                    if (pows[length - i - 1] <= k) {
                        k -= pows[length - i - 1];
                    } else {
                        found = true;
                        ans[i] = (char) (letter + 'a');
                        v = u;
                        break;
                    }
                } else if (dp[u][length - i - 1] <= k) {
                    k -= dp[u][length - i - 1];
                } else {
                    found = true;
                    ans[i] = (char) (letter + 'a');
                    v = u;
                    break;
                }
            }
            if (ac.hasTerminal(v)) {
                haveTerminal = true;
            }
            if (!found) {
                throw new AssertionError();
            }
        }
        return new String(ans);
    }

    static final long INF = (1L << 60);
    static final int alphabet = 26;

    static long add(long a, long b) {
        a += b;
        if (a >= INF) {
            return INF;
        }
        return a;
    }

    static long mul(long a, long b) {
        if (INF / b < a) {
            return INF;
        }
        a *= b;
        if (a >= INF) {
            return INF;
        }
        return a;
    }
}
