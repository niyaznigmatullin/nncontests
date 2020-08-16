package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GaryAndSubsequence {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        String[] s = new String[n];
        int[][][] next = new int[n][][];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
            next[i] = new int[s[i].length()][];
            int[] f = new int[26];
            Arrays.fill(f, s[i].length());
            for (int j = s[i].length() - 1; j >= 0; j--) {
                f[s[i].charAt(j) - 'a'] = j;
                next[i][j] = f.clone();
            }
        }
        Map<Long, Boolean> cache = new HashMap<>();
        for (int cq = 0; cq < q; cq++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (s[x].length() > s[y].length() || (s[x].length() == s[y].length() && x > y)) {
                int t = x;
                x = y;
                y = t;
            }
            long key = (long) x * n + y;
            if (cache.containsKey(key)) {
                out.println(cache.get(key) ? "Yes" : "No");
                continue;
            }
            int curY = 0;
            int curX = 0;
            while (curX < s[x].length() && curY < s[y].length()) {
                int letter = s[x].charAt(curX) - 'a';
                curY = next[y][curY][letter];
                if (curY == s[y].length()) break;
                curY++;
                curX++;
            }
            boolean curAnswer = curX == s[x].length();
            cache.put(key, curAnswer);
            out.println(curAnswer ? "Yes" : "No");
        }
    }
}
