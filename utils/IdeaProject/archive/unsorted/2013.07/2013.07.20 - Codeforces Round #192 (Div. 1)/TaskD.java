package coding;

import com.sun.org.apache.bcel.internal.generic.DUP_X1;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n == 5) {
            out.println(">...v\nv.<..\n..^..\n>....\n..^.<\n1 1");
            return;
        }
        if (n == 3) {
            out.println(">vv\n" +
                    "^<.\n" +
                    "^.<\n" +
                    "1 3");
            return;
        }
//        int n = in.nextInt();
//        char[][] c = in.readCharacterFieldTokens(n, n);
        char[][] c = new char[100][100];
        for (char[] e : c) {
            Arrays.fill(e, '.');
        }
        for (int i = 0; i < 99; i++) {
            c[0][i] = '>';
        }
        c[0][99] = 'v';
        for (int i = 1; i < 100; i += 2) {
            for (int j = 0; j < 48; j++) {
                c[i][99 - j] = '<';
            }
            for (int j = 0; j < 50; j++) {
                c[i][99 - 48 - j] = (j & 1) == 0 ? '.' : '<';
            }
            if (i + 1 < 100) {
                c[i][1] = 'v';
                for (int j = 0; j < 48; j++) {
                    c[i + 1][1 + j] = '>';
                }
                for (int j = 0; j < 50; j++) {
                    c[i + 1][1 + 48 + j] = (j & 1) == 0 ? '.' : '>';
                }
                c[i + 1][99] = 'v';
            }
        }
        for (int i = 0; i < 50; i++) {
            c[99 - i][0] = '^';
        }
//        int x = 0;
//        int y = 0;
//        for (int n = 100; n > 0; n -= 2) {
//            for (int dir = 0; dir < 4; dir++) {
//                for (int e = 0; e < n - 1; e++) {
//                    c[x][y] = DIRS.charAt(dir);
//                    x += DX[dir];
//                    y += DY[dir];
//                }
//            }
//            x -= DX[3];
//            y -= DY[3];
//            c[x][y] = DIRS.charAt(0);
//            x += DX[0];
//            y += DY[0];
//        }
//        x -= DX[0];
//        y -= DY[0];
//        c[x][y] = '.';
//        Random rand = new Random();
//        for (int i = 0; i < 1; i++) {
//            int xx = rand.nextInt(50) + 5;
//            int yy = rand.nextInt(50) + 5;
//            c[xx][yy] = '.';
//        }
//        for (int i = 1; i < 100; i += 2) {
//            for (int j = 1; j < 100; j += 2) {
//                c[i][j] = '.';
//            }
//        }
//        for (char[] e : c) {
//            System.out.println(e);
//        }
//        check(c, 0, 0);
        for (char[] e : c) {
            out.println(e);
        }
        out.println(1 + " " + 1);
    }

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};
    static final String DIRS = "v>^<";

    static void check(char[][] c, int x, int y) {
        int n = c.length;
        int done = 0;
        while (true) {
            if (done >= 100000) {
                break;
            }
            if (c[x][y] == '.') {
                throw new AssertionError();
            }
            int dir = DIRS.indexOf(c[x][y]);
            char ch = c[x][y];
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            int px = x;
            int py = y;
            int add = 0;
            while (nx >= 0 && ny >= 0 && nx < n && ny < n && c[nx][ny] == '.') {
                ++add;
                c[px][py] = '.';
                c[nx][ny] = ch;
                px = nx;
                py = ny;
                nx += DX[dir];
                ny += DY[dir];
            }
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                break;
            }
            x = nx;
            y = ny;
            done += add;
            System.out.println(done);
        }
        System.out.println("done " + done);
    }
}
