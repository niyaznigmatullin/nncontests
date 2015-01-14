import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Niyaz Nigmatullin
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		FastScanner in = new FastScanner(inputStream);
		FastPrinter out = new FastPrinter(outputStream);
		TaskC solver = new TaskC();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskC {

    static final int K = 9;
    static int[] Z = new int[1 << K];

    static {
        Z[1] = 0;
        for (int i = 2; i < Z.length; i++) {
            Z[i] = Z[i >> 1] + 1;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int qCount = in.nextInt();
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int[] ks = new int[qCount];
        for (int i = 0; i < qCount; i++) {
            ks[i] = in.nextInt();
        }
        int[][][][] fMin = new int[K][K][n][m];
        int[][][][] fMax = new int[K][K][n][m];
        fMin[0][0] = fMax[0][0] = a;
        for (int i = 1; i < K; i++) {
            int shift = 1 << i - 1;
            int[][] max = fMax[0][i - 1];
            int[][] min = fMin[0][i - 1];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    int mx = max[x][y];
                    int mn = min[x][y];
                    if (y >= shift) {
                        mx = Math.max(mx, max[x][y - shift]);
                        mn = Math.min(mn, min[x][y - shift]);
                    }
                    fMax[0][i][x][y] = mx;
                    fMin[0][i][x][y] = mn;
                }
            }
        }
        for (int j = 0; j < K; j++) {
            for (int i = 1; i < K; i++) {
                int shift = 1 << i - 1;
                int[][] max = fMax[i - 1][j];
                int[][] min = fMin[i - 1][j];
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < m; y++) {
                        int mx = max[x][y];
                        int mn = min[x][y];
                        if (x >= shift) {
                            mx = Math.max(mx, max[x - shift][y]);
                            mn = Math.min(mn, min[x - shift][y]);
                        }
                        fMax[i][j][x][y] = mx;
                        fMin[i][j][x][y] = mn;
                    }
                }
            }
        }
        long[] ans = new long[qCount];
        for (int x1 = 0; x1 < n; x1++) {
            int[] min = new int[m];
            int[] max = new int[m];
            Arrays.fill(min, Integer.MAX_VALUE);
            Arrays.fill(max, Integer.MIN_VALUE);
            for (int x2 = x1; x2 < n; x2++) {
                int[] ax2 = a[x2];
                for (int i = 0; i < m; i++) {
                    min[i] = Math.min(min[i], ax2[i]);
                    max[i] = Math.max(max[i], ax2[i]);
                }
                for (int f = 0; f < qCount; f++) {
                    int k = ks[f];
                    for (int i = 0, j = -1; i < m; i++) {
                        while (j + 1 < m && (j + 1 < i || getDif(fMax, fMin, x1, i, x2, j + 1) <= k)) {
                            ++j;
                        }
                        ans[f] += j - i + 1;
                    }
                }
            }
        }
        for (long z : ans) {
            out.println(z);
        }
    }

    static int getDif(int[][][][] f, int[][][][] fMin, int x1, int y1, int x2, int y2) {
        int v1 = Z[x2 - x1 + 1];
        int v2 = Z[y2 - y1 + 1];
        int shift1 = 1 << v1;
        int shift2 = 1 << v2;
        int[][] g = f[v1][v2];
        int[][] g2 = fMin[v1][v2];
        return Math.max(Math.max(g[x2][y2], g[x1 + shift1 - 1][y2]),
                Math.max(g[x2][y1 + shift2 - 1], g[x1 + shift1 - 1][y1 + shift2 - 1])) -
                Math.min(Math.min(g2[x2][y2], g2[x1 + shift1 - 1][y2]),
                        Math.min(g2[x2][y1 + shift2 - 1], g2[x1 + shift1 - 1][y1 + shift2 - 1]));
    }
}

class FastScanner extends BufferedReader {

    public FastScanner(InputStream is) {
        super(new InputStreamReader(is));
    }

    public int read() {
        try {
            int ret = super.read();
//            if (isEOF && ret < 0) {
//                throw new InputMismatchException();
//            }
//            isEOF = ret == -1;
            return ret;
        } catch (IOException e) {
            throw new InputMismatchException();
        }
    }

    static boolean isWhiteSpace(int c) {
        return c >= 0 && c <= 32;
    }

    public int nextInt() {
        int c = read();
        while (isWhiteSpace(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int ret = 0;
        while (c >= 0 && !isWhiteSpace(c)) {
            if (c < '0' || c > '9') {
                throw new NumberFormatException("digit expected " + (char) c
                        + " found");
            }
            ret = ret * 10 + c - '0';
            c = read();
        }
        return ret * sgn;
    }

    public String readLine() {
        try {
            return super.readLine();
        } catch (IOException e) {
            return null;
        }
    }

}

class FastPrinter extends PrintWriter {

    public FastPrinter(OutputStream out) {
        super(out);
    }

    public FastPrinter(Writer out) {
        super(out);
    }


}

