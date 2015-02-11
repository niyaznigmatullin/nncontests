package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static int code(char c) {
        if (Character.isDigit(c)) return c - '0';
        if (Character.isLowerCase(c)) return c - 'a' + 10;
        return c - 'A' + 36;
    }

    static char decode(int x) {
        if (x < 10) return (char) (x + '0');
        x -= 10;
        if (x < 26) return (char) (x + 'a');
        x -= 26;
        return (char) (x + 'A');
    }

    static int[] head;
    static int[][] edges;
    static final int LETTERS = 26 + 26 + 10;
    static final int all = LETTERS * LETTERS;

    static char[] ans;
    static int pos;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new int[all][all];
        for (int i = 0; i < n; i++) {
            String  s= in.next();
            edges[code(s.charAt(0)) * LETTERS + code(s.charAt(1))][code(s.charAt(1)) * LETTERS + code(s.charAt(2))]++;
        }
        int start = -1;
        int finish = -1;
        int any = 0;
        for (int i = 0; i < all; i++) {
            int degIn = 0;
            int degOut = 0;
            for (int j = 0; j < all; j++) {
                degIn += edges[j][i];
                degOut += edges[i][j];
            }
            if (Math.abs(degIn - degOut) > 1) {
                out.println("NO");
                return;
            }
            if (degIn + degOut > 0) any = i;
            if (degIn > degOut) {
                if (finish >= 0) {
                    out.println("NO");
                    return;
                }
                finish = i;
            }
            if (degIn < degOut) {
                if (start >= 0) {
                    out.println("NO");
                    return;
                }
                start = i;
            }
        }
        if (start < 0) start = any;
        pos = n;
        ans = new char[n + 2];
        head = new int[all];
        dfs(start);
        if (pos >= 0) {
            out.println("NO");
            return;
        }
        out.println("YES");
        out.println(ans);
    }

    static void dfs(int v) {
        while (head[v] < all) {
            if (edges[v][head[v]] == 0) {
                ++head[v];
                continue;
            }
            --edges[v][head[v]];
            dfs(head[v]);
        }
        ans[pos] = decode(v / LETTERS);
        ans[pos + 1] = decode(v % LETTERS);
        --pos;
    }
}
