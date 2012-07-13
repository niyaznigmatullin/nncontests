package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.BitSet;

public class ProblemE {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        BitSet[] bs = new BitSet[n * m];
        final int[] DX = { 1, 0, -1, 0, 0 };
        final int[] DY = { 0, 1, 0, -1, 0 };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int c = i * m + j;
                bs[c] = new BitSet(n * m);
                for (int dir = 0; dir < 5; dir++) {
                    int x = i + DX[dir];
                    int y = j + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m) {
                        continue;
                    }
                    bs[c].set(x * m + y);
                }
            }
        }
        int row = 0;
        for (int i = 0; row < n * m && i < n * m; i++) {
            if (!bs[row].get(i)) {
                for (int j = row + 1; j < n * m; j++) {
                    if (bs[j].get(i)) {
                        BitSet t = bs[row];
                        bs[row] = bs[j];
                        bs[j] = t;
                        break;
                    }
                }
                if (!bs[row].get(i)) {
                    continue;
                }
            }
            for (int j = row + 1; j < n * m; j++) {
                if (bs[j].get(i)) {
                    bs[j].xor(bs[row]);
                }
            }
            ++row;
        }
        int rank = row;
        --row;
        for (int i = bs[row].nextSetBit(0); row >= 0 && i >= 0; i--) {
            if (!bs[row].get(i)) {
                continue;
            }
            for (int j = row - 1; j >= 0; j--) {
                if (bs[j].get(i)) {
                    bs[j].xor(bs[row]);
                }
            }
            --row;
        }
        if (rank == n * m) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j > 0) {
                        out.print(' ');
                    }
                    out.print(0);
                }
                out.println();
            }
            return;
        }
        int[][] answer = new int[n][m];
        answer[n - 1][m - 1] = 1;
        for (int i = 0; i < n * m; i++) {
            if (bs[i].get(n * m - 1)) {
                answer[i / m][i % m] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j > 0) {
                    out.print(' ');
                }
                out.print(answer[i][j]);
            }
            out.println();
        }
	}
}
