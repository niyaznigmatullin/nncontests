package DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 22.10.12
 * Time: 0:45
 * To change this template use File | Settings | File Templates.
 */
public class SuffixArray {

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

}
