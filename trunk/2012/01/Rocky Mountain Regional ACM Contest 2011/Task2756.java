package mypackage;

import com.sun.xml.internal.rngom.digested.DXMLPrinter;
import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.jvm.hotspot.debugger.dbx.x86.DbxX86Thread;

import java.util.Arrays;

public class Task2756 {

    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int MOD = in.nextInt();
        if (MOD == 0) {
            throw new UnknownError();
        }
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int[] d = new int[4 * n * m];
        int[] ways = new int[4 * n * m];
        Arrays.fill(d, Integer.MAX_VALUE);
        int[] q = new int[4 * n * m];
        int firstX = in.nextInt();
        int firstY = in.nextInt();
        int finalX = in.nextInt();
        int finalY = in.nextInt();
        int firstDir = "NESW".indexOf(in.next());
        int initState = firstDir * n * m + firstX * m + firstY;
        int head = 0;
        int tail = 1;
        q[head] = initState;
        d[initState] = 0;
        ways[initState] = 1 % MOD;
        while (head < tail) {
            int curState = q[head++];
            int dir = curState / n / m;
            int x = curState / m % n;
            int y = curState % m;
            do {
                int newX = x + DX[dir];
                int newY = y + DY[dir];
                if (newX < 0 || newY < 0 || newX >= n || newY >= m || c[newX][newY] == '*') {
                    continue;
                }
                int newDir = dir;
                int newState = newDir * n * m + newX * m + newY;
                if (d[newState] > d[curState] + 1) {
                    d[newState] = d[curState] + 1;
                    ways[newState] = ways[curState];
                    q[tail++] = newState;
                } else if (d[newState] == d[curState] + 1) {
                    ways[newState] += ways[curState];
                    if (ways[newState] >= MOD) {
                        ways[newState] -= MOD;
                    }
                }
            } while (false);
            {
                int newX = x;
                int newY = y;
                int newDir = (dir + 3) & 3;
                int newState = newDir * n * m + newX * m + newY;
                if (d[newState] > d[curState] + 1) {
                    d[newState] = d[curState] + 1;
                    ways[newState] = ways[curState];
                    q[tail++] = newState;
                } else if (d[newState] == d[curState] + 1) {
                    ways[newState] += ways[curState];
                    if (ways[newState] >= MOD) {
                        ways[newState] -= MOD;
                    }
                }
            }
            {
                int newX = x;
                int newY = y;
                int newDir = (dir + 1) & 3;
                int newState = newDir * n * m + newX * m + newY;
                if (d[newState] > d[curState] + 1) {
                    d[newState] = d[curState] + 1;
                    ways[newState] = ways[curState];
                    q[tail++] = newState;
                } else if (d[newState] == d[curState] + 1) {
                    ways[newState] += ways[curState];
                    if (ways[newState] >= MOD) {
                        ways[newState] -= MOD;
                    }
                }
            }
        }
        out.print("Case " + testNumber + ": " + MOD + " ");
        int minD = Integer.MAX_VALUE;
        for (int dir = 0; dir < 4; dir++) {
            int state = dir * n * m + finalX * m + finalY;
            minD = Math.min(minD, d[state]);
        }
        if (minD == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        int ans = 0;
        for (int dir = 0; dir < 4; dir++) {
            int state = dir * n * m + finalX * m + finalY;
            if (minD == d[state]) {
                ans += ways[state];
                if (ans >= MOD) {
                    ans -= MOD;
                }
            }
        }
        out.println(ans);
        System.gc();
    }
}
