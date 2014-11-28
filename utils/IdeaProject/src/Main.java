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
		Substrings solver = new Substrings();
		solver.solve(1, in, out);
		out.close();
	}
}

class Substrings {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int[] a = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i);
        }
        int n = a.length;
        int[] sa = SuffixArray.buildSuffixArray(a);
        int[] lcp = SuffixArray.getLCP(sa, a);
        MinSegmentTree tree = new MinSegmentTree(n);
        for (int i = 0; i + 1 < n; i++) tree.set(i, lcp[i]);
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) rev[sa[i]] = i;
        int q = in.nextInt();
        long[] answer = new long[n];
        for (int i = 0; i < n; i++) {
            int lcpPrev = i > 0 ? lcp[i - 1] : 0;
            int len = n - sa[i] - 1;
            if (i > 0) answer[i] += answer[i - 1];
//            answer[i] += len - Math.min(len, lcpPrev);
            answer[i] += len;
        }
        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            int len = r - l;
            int w = rev[l];
            {
                int left = 0;
                int right = w + 1;
                while (left < right - 1) {
                    int mid = (left + right) >> 1;
                    if (tree.getMin(w - mid, w) >= len - 1) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                w -= left;
            }
            int w2 = -1;
            {
                int left = w;
                int right = n;
                while (left < right - 1) {
                    int mid = (left + right) >> 1;
                    if (tree.getMin(w, mid) >= len - 1) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                w2 = left;
            }
            long ans = w > 0 ? answer[w - 1] : 0;
            ans += (long) (len - 1) * (w2 - w + 1);
            out.println(ans);
        }
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

    public String next() {
        StringBuilder sb = new StringBuilder();
        int c = read();
        while (isWhiteSpace(c)) {
            c = read();
        }
        if (c < 0) {
            return null;
        }
        while (c >= 0 && !isWhiteSpace(c)) {
            sb.appendCodePoint(c);
            c = read();
        }
        return sb.toString();
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

class SuffixArray {

    public static int[] buildSuffixArray(int[] s) {
        int n = s.length;
        int alphabet = 0;
        for (int i : s) {
            alphabet = Math.max(alphabet, i);
        }
        ++alphabet;
        int[] h = new int[Math.max(n, alphabet)];
        int[] c = new int[n];
        int[] d = new int[n];
        int[] count = new int[alphabet];
        for (int i = 0; i < n; i++) {
            c[i] = s[i];
            count[c[i]]++;
        }
        for (int i = 1; i < alphabet; i++) {
            h[i] = h[i - 1] + count[i - 1];
        }
        int[] a = new int[n];
        int[] b = new int[n];
        {
            int[] g = h.clone();
            for (int i = 0; i < n; i++) {
                a[g[c[i]]++] = i;
            }
        }
        for (int w = 1; w < n; w <<= 1) {
            for (int i = 0; i < n; i++) {
                int j = good(a[i] - w + n, n);
                b[h[c[j]]++] = j;
            }
            int nc = 0;
            h[nc++] = 0;
            d[b[0]] = 0;
            for (int i = 1; i < n; i++) {
                if (c[b[i]] != c[b[i - 1]] || c[good(b[i] + w, n)] != c[good(b[i - 1] + w, n)]) {
                    h[nc++] = i;
                }
                d[b[i]] = nc - 1;
            }
            int[] t = a; a = b; b = t;
            t = c; c = d; d = t;
        }
        return a;
    }

    static int good(int x, int n) {
        if (x >= n) {
            x -= n;
        }
        return x;
    }


    public static int[] getLCP(int[] sa, int[] a) {
        int k = 0;
        int n = a.length;
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[sa[i]] = i;
        }
        int[] lcp = new int[n];
        for (int i = 0; i < n; i++) {
            k = Math.max(k - 1, 0);
            int j = rev[i] + 1;
            if (j >= n) continue;
            j = sa[j];
            while (i + k < n && j + k < n && a[i + k] == a[j + k]) ++k;
            lcp[rev[i]] = k;
        }
        return lcp;
    }

}

class MinSegmentTree {
    int[] min;
    int[] minId;
    int n;

    public MinSegmentTree(int n) {
        this.n = Integer.highestOneBit(n) << 1;
        min = new int[this.n * 2];
        minId = new int[this.n * 2];
        for (int i = 0; i < n; i++) {
            minId[i + n] = i;
        }
        for (int i = 0; i < n; i++) {
            set(i, Integer.MAX_VALUE);
        }
    }

    public void set(int x, int y) {
        x += n;
        min[x] = y;
        while (x > 1) {
            x >>= 1;
            int left = min[x << 1];
            int right = min[(x << 1) | 1];
            if (left <= right) {
                min[x] = left;
                minId[x] = minId[x << 1];
            } else {
                min[x] = right;
                minId[x] = minId[(x << 1) | 1];
            }
        }
    }

    public int getMin(int left, int right) {
        --right;
        left += n;
        right += n;
        int ret = Integer.MAX_VALUE;
        while (left <= right) {
            if ((left & 1) == 1) {
                ret = Math.min(ret, min[left]);
                left++;
            }
            if ((right & 1) == 0) {
                ret = Math.min(ret, min[right]);
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
        return ret;
    }

}

