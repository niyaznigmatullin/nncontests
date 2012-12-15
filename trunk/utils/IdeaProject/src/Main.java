import java.util.BitSet;
import java.io.InputStreamReader;
import java.io.IOException;
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
		F solver = new F();
		solver.solve(1, in, out);
		out.close();
	}
}

class F {

    static class DSU {
        int[] p;
        BitSet bit;

        DSU(int n) {
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
            bit = new BitSet(n);
        }

        int get(int x) {
            boolean cur = false;
            int last = x;
            for (int i = x; i != p[i]; i = p[i]) {
                cur ^= bit.get(i);
                last = p[i];
            }
            for (int i = x; i != p[i]; ) {
                int go = p[i];
                p[i] = last;
                boolean next = cur ^ bit.get(i);
                bit.set(i, cur);
                cur = next;
                i = go;
            }
            return last;
        }

        boolean union(int x, int y, boolean z) {
            int i = get(x);
            int j = get(y);
//            System.out.println(x + " " + y + " " + z + " " + i + " " + j);
            z = bit.get(x) ^ bit.get(y) ^ z;
            if (i == j) {
                return !z;
            }
            x = i;
            y = j;
            if (x > y) {
                p[x] = y;
                bit.set(x, z);
            } else {
                p[y] = x;
                bit.set(y, z);
            }
            return true;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DSU dsu = new DSU(n * 32);
        int[] shift = new int[n];
        for (int i = 0; i < m; i++) {
            int type = in.nextInt();
            if (type == 1) {
                int x = in.nextInt() - 1;
                int y = in.nextInt();
                shift[x] = (shift[x] - y) & 31;
            } else {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                long z = in.nextLong();
                for (int bit = 0; bit < 32; bit++) {
                    if (!dsu.union(x * 32 + (bit + shift[x] & 31), y * 32 + (bit + shift[y] & 31), ((z >>> 31 - bit) & 1) == 1)) {
                        out.println(-1);
                        return;
                    }
                }
            }
        }
        printIt(out, n, dsu);
    }

    private void printIt(FastPrinter out, int n, DSU dsu) {
        for (int i = 0; i < n; i++) {
            long x = 0;
            for (int j = 0; j < 32; j++) {
                int k = i * 32 + j;
                dsu.get(k);
                int q = dsu.bit.get(k) ? 1 : 0;
                x = (x << 1) + q;
            }
            if (i > 0) {
                out.print(' ');
            }
            out.print(x);
        }
        out.println();
    }
}

class FastScanner extends BufferedReader {

    boolean isEOF;

    public FastScanner(InputStream is) {
        super(new InputStreamReader(is));
    }

    public int read() {
        try {
            int ret = super.read();
            if (isEOF && ret < 0) {
                throw new InputMismatchException();
            }
            isEOF = ret == -1;
            return ret;
        } catch (IOException e) {
            throw new InputMismatchException();
        }
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        int c = read();
        while (isWhiteSpace(c)) {
            c = read();
        }
        while (!isWhiteSpace(c)) {
            sb.appendCodePoint(c);
            c = read();
        }
        return sb.toString();
    }

    static boolean isWhiteSpace(int c) {
        return c >= -1 && c <= 32;
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
        while (!isWhiteSpace(c)) {
            if (c < '0' || c > '9') {
                throw new NumberFormatException("digit expected " + (char) c
                        + " found");
            }
            ret = ret * 10 + c - '0';
            c = read();
        }
        return ret * sgn;
    }

    public long nextLong() {
        return Long.parseLong(next());
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

