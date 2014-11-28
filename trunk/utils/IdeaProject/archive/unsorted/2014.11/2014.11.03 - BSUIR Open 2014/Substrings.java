package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.DataStructures.SuffixArray;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayDeque;
import java.util.Queue;

public class Substrings {
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
        int queriesCount = in.nextInt();
        long[] answer = new long[n];
        for (int i = 0; i < n; i++) {
            int len = n - sa[i] - 1;
            if (i > 0) answer[i] += answer[i - 1];
            answer[i] += len;
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n - 1);
        while (!q.isEmpty()) {
            int v = q.poll();
            int left = v / n;
            int right = v % n;
            int curLCP = tree.getMin(left, right);
            int cur = left;
            while (cur <= right) {
                int l = cur;
                int r = right;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    if (a[(curLCP + sa[mid]) % n] == a[(sa[l] + curLCP) % n]) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                q.add(cur * n + l);
                cur = r;
            }
        }
        for (int i = 0; i < queriesCount; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            int len = r - l;
            int w = rev[l];
            {
                int left = 0;
                int right = w + 1;
                while (left < right - 1) {
                    int mid = (left + right) >> 1;
                    if (tree.getMin(w - mid, w) >= len) {
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
