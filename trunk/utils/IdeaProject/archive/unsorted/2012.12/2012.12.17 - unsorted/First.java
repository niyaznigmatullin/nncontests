package coding;

import ru.ifmo.niyaz.DataStructures.TrieList;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

import java.util.Arrays;
import java.util.BitSet;

public class First {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = new int[s[i].length() + 1];
            for (int j = 0; j < s[i].length(); j++) {
                a[i][j] = s[i].charAt(j) - 'a' + 1;
            }
        }
        TrieList st = new TrieList(a);
        BitSet[] bs = new BitSet[st.free];
        int[] q = new int[st.free];
        q[0] = 0;
        int head = 0;
        int tail = 1;
        final int ALPHABET = 27;
        bs[0] = new BitSet(ALPHABET * ALPHABET);
        while (head < tail) {
            int v = q[head++];
            int e = st.head[v];
            for (int i = e; i >= 0; i = st.next[i]) {
                int to = st.to[i];
                int letter = st.letter[i];
                bs[to] = new BitSet(ALPHABET * ALPHABET);
                bs[to].or(bs[v]);
                for (int j = e; j >= 0; j = st.next[j]) {
                    if (i == j) {
                        continue;
                    }
                    int letter2 = st.letter[j];
                    bs[to].set(letter * ALPHABET + letter2);
                }
                q[tail++] = to;
            }
        }
        boolean[] ans = new boolean[n];
        Arrays.fill(ans, true);
        for (int i = 0; i < n; i++) {
            int[] t = a[i];
            int v = 0;
            for (int j : t) {
                v = st.getLink(v, j);
            }
            boolean[][] c = new boolean[ALPHABET][ALPHABET];
            for (int x = 0; x < ALPHABET; x++) {
                for (int y = 0; y < ALPHABET; y++) {
                    c[x][y] = bs[v].get(x * ALPHABET + y);
                }
            }
            for (int z = 0; z < ALPHABET; z++) {
                c[0][z] = true;
            }
            floyd(c);
            for (int x = 0; x < ALPHABET; x++) {
                for (int y = 0; y < ALPHABET; y++) {
                    if (x == y) {
                        continue;
                    }
                    if (c[x][y] && c[y][x]) {
                        ans[i] = false;
                    }
                }
            }
        }
        int cnt = 0;
        for (boolean e : ans) {
            cnt += e ? 1 : 0;
        }
        out.println(cnt);
        for (int i = 0; i < n; i++) {
            if (ans[i]) {
                out.println(s[i]);
            }
        }
    }

    static void floyd(boolean[][] c) {
        for (int k = 0; k < 26; k++) {
            boolean[] ck = c[k];
            for (int i = 0; i < 26; i++) {
                boolean[] ci = c[i];
                for (int j = 0; j < 26; j++) {
                    ci[j] |= ci[k] && ck[j];
                }
            }
        }
    }
}
