package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class TaskC {
    static final int MOD = 1000000007;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int n = in.nextInt();
        char[] from = new char[n];
        String[] to = new String[n];
        for (int i = 0; i < n; i++) {
            String line = in.next();
            from[i] = line.charAt(0);
            to[i] = line.substring(3);
        }
        int[] map = new int[10];
        long[] length = new long[10];
        for (int i = 0; i < 10; i++) {
            map[i] = i;
            length[i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            int nMap = 0;
            int nLength = 0;
            String t = to[i];
            for (int j = 0; j < t.length(); j++) {
                int d = t.charAt(j) - '0';
                nMap = add(mul(nMap, MathUtils.modPow(10, length[d], MOD)), map[d]);
                nLength += length[d];
                if (nLength >= MOD - 1) {
                    nLength -= MOD - 1;
                }
            }
            map[from[i] - '0'] = nMap;
            length[from[i] - '0'] = nLength;
        }
        {
            int nMap = 0;
            String t = s;
            for (int j = 0; j < t.length(); j++) {
                int d = t.charAt(j) - '0';
                nMap = add(mul(nMap, MathUtils.modPow(10, length[d], MOD)), map[d]);
            }
            out.println(nMap);
        }

    }
}
