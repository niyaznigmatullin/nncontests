package lib.test.on2013_09.on2013_09_02_.SubMatrix;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SubMatrix {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[][] c = in.readCharacterFieldTokens(1000, 1000);
//        Random rand = new Random();
//        int x = rand.nextInt(5000) + 990000;
//        int y = rand.nextInt(5000) + 990000;
//        char[][] c = new char[1000][1000];
//        for (int i = 0; i < 1000; i++) {
//            for (int j = 0; j < 1000; j++) {
//                c[i][j] = (char) ('0' + getIt(i + x, j + y));
//            }
//        }
        final int IT = 20;
        final int N = 4000000;
        int[] posx = new int[N];
        int[] posy = new int[N];
        int[] next = new int[N];
        int free = 0;
        int[][] head = new int[IT + 1][1 << IT];
        for (int[] d : head) {
            Arrays.fill(d, -1);
        }
        for (int i = -IT + 1; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                int number = 0;
                for (int k = 0; k < IT && i + k < 1000; k++) {
                    number <<= 1;
                    if (i + k >= 0)
                        number |= c[i + k][j] - '0';
                }
                posx[free] = i;
                posy[free] = j;
                int z = i < 0 ? IT + i : Math.min(IT, 1000 - i);
                next[free] = head[z][number];
                head[z][number] = free;
                free++;
            }
        }
        System.out.println(free);
//        for (int z = 0; z < IT; z++) {
            for (int i = 0; i < 1000000; i += 1000) {
                for (int j = 0; j < 1000000; j += 1000) {
                    int number = 0;
                    for (int k = 0; k < IT; k++) {
                        number <<= 1;
                        number |= getIt(i + k, j);
                    }
                    for (int e = head[IT][number]; e >= 0; e = next[e]) {
                        if (check(i - posx[e], j - posy[e], c)) {
                            out.println(i - posx[e] + 1 + " " + (j - posy[e] + 1));
                            return;
                        }
                    }
                    for (int pref = IT / 2 - 1; pref < IT; pref++) {
                        int num = number >> IT - pref;
                        for (int e = head[pref][num]; e >= 0; e = next[e]) {
                            if (check(i - posx[e], j - posy[e], c)) {
                                out.println(i - posx[e] + 1 + " " + (j - posy[e] + 1));
                                return;
                            }
                        }
                    }
                    for (int suf = IT / 2 - 1; suf < IT; suf++) {
                        int num = number & ((1 << suf) - 1);
                        for (int e = head[suf][num]; e >= 0; e = next[e]) {
                            int cx = i;
                            int cy = j - posy[e];
                            if (check(cx, cy, c)) {
                                out.println(cx + 1 + " " + (cy + 1));
                                return;
                            }
                        }
                    }
                }
            }
//        }
        throw new AssertionError();
    }

    static int count;

    static boolean check(int x, int y, char[][] c) {
        if (x < 0 || y < 0 || x + 1000 > 1000000 || y + 1000 > 1000000) return false;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (getIt(i + x, j + y) != c[i][j] - '0') return false;
            }
        }
        return true;
    }

    static int getIt(int x, int y) {
        int i = (x + 1);
        int j = (y + 1);
        long where = i * 1000000L + j;
        int hash = 0;
        for (int k = 0; k < 5; ++k) {
            hash += (int) ((where >>> (8 * k)) & 255);
            hash += (hash << 10);
            hash ^= (hash >>> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >>> 11);
        hash += (hash << 15);
        return (hash >>> 27) & 1;
    }
}
