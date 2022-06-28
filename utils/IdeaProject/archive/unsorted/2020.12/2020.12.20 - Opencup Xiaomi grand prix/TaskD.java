package coding;

import ru.ifmo.niyaz.DataStructures.SparseTableMin;
import ru.ifmo.niyaz.DataStructures.SuffixArray;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {

    static int getLCP(int x, int y, int[] a, int[] rsa, SparseTableMin sparse) {
        int ans = 0;
        while (true) {
            int a1 = rsa[x + ans];
            int a2 = rsa[y + ans];
            if (a1 > a2) {
                int t = a1;
                a1 = a2;
                a2 = t;
            }
            ans += sparse.getMin(a1, a2);
            if (getAt(x, ans, a) == getAt(y, ans, a)) {
                ++ans;
            } else {
                break;
            }
        }
        return ans;
    }

    static int getAt(int start, int pos, int[] a) {
        if (start + pos == a.length - 1) {
            return Integer.MAX_VALUE;
        }
        if (a[start + pos] > pos) {
            return -1;
        }
        return a[start + pos];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String s = in.next();
        int[] a = new int[n + 1];
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int letter = s.charAt(i) - 'a';
            a[i] = i - last[letter];
            last[letter] = i;
        }
        final int[] sa = SuffixArray.buildSuffixArray(a);
        final int[] lcp = SuffixArray.getLCP(sa, a);
        int[] rsa = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            rsa[sa[i]] = i;
        }
        SparseTableMin sparse = new SparseTableMin(lcp);
        Integer[] suffixes = new Integer[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }
        Arrays.sort(suffixes, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int c = getLCP(o1, o2, a, rsa, sparse);
                return Integer.compare(getAt(o1, c, a), getAt(o2, c, a));
            }
        });
        long ans = n - suffixes[0];
        for (int i = 1; i < n; i++) {
            int clcp = getLCP(suffixes[i - 1], suffixes[i], a, rsa, sparse);
            ans += n - suffixes[i] - clcp;
//            System.out.println(suffixes[i - 1] + " " + suffixes[i] + " " + clcp);
        }
        out.println(ans);
    }
}
