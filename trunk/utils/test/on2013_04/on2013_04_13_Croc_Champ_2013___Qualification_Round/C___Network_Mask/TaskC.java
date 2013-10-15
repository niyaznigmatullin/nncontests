package lib.test.on2013_04.on2013_04_13_Croc_Champ_2013___Qualification_Round.C___Network_Mask;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            a[i] = ipToLong(s);
        }
        ArrayUtils.sort(a);
        long z = 1L << 31;
        for (int moves = 0; moves < 31; moves++) {
            int cur = 1;
            long last = z & a[0];
            for (int i = 1; i < n; i++) {
                long v = z & a[i];
                if (v != last) {
                    ++cur;
                    last = v;
                }
            }
            if (cur == k) {
                out.println(longToIP(z));
                return;
            }
            z |= z >> 1;
        }
        out.println(-1);
    }

    static long ipToLong(String token) {
        StringTokenizer st = new StringTokenizer(token, ".");
        long ip = 0;
        for (int j = 0; j < 4; j++) {
            ip = (ip << 8) | Integer.parseInt(st.nextToken());
        }
        return ip;
    }

    static String longToIP(long ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            if (i < 3) sb.append('.');
            sb.append((ip >> 8 * i) & 0xFF);
        }
        return sb.toString();
    }
}
