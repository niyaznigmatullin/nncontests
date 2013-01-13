package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lineup {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.readIntArray(n);
        int answer = smart(n, k, a);
        out.println(answer);
    }

//    void stress() {
//        Random rand = new Random(434L);
//        while (true) {
//            int n = rand.nextInt(100) + 1;
//            int[] a = new int[n];
//            for (int i = 0; i < n; i++) {
//                a[i] = rand.nextInt(25);
//            }
//            int k = rand.nextInt(n);
//            int got = smart(n, k, a.clone());
//            int got2 = stupid(n, k, a.clone());
//            if (got != got2) {
//                throw new AssertionError();
//            }
//            System.err.println("OK");
//        }
//    }
//
//    int stupid(int n, int k, int[] a) {
//        int answer = 0;
//        for (int i = 0; i < n; i++) {
//            Set<Integer> set = new HashSet<Integer>();
//            int countFirst = 0;
//            for (int j = i; set.size() <= k && j < n; j++) {
//                if (a[j] == a[i]) {
//                    ++countFirst;
//                } else {
//                    set.add(a[j]);
//                }
//            }
//            answer = Math.max(answer, countFirst);
//        }
//        return answer;
//    }

    int smart(int n, int k, int[] a) {
        int[] b = ArrayUtils.sortAndUnique(a);
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.binarySearch(b, a[i]);
        }
        int[] last = new int[n];
        Arrays.fill(last, -1);
        Fenwick f = new Fenwick(n);
        int[] cnt = new int[b.length];
        for (int i : a) {
            cnt[i]++;
        }
        int[][] d = new int[b.length][];
        for (int i = 0; i < b.length; i++) {
            d[i] = new int[cnt[i]];
        }
        int[] index = new int[n];
        Arrays.fill(cnt, 0);
        for (int i = 0; i < n; i++) {
            index[i] = cnt[a[i]];
            d[a[i]][cnt[a[i]]++] = i;
        }
        int answer = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (last[a[i]] >= 0) {
                f.add(last[a[i]], -1);
            }
            last[a[i]] = i;
            f.add(i, 1);
            int j;
            {
                int l = i + 1;
                int r = n + 1;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    if (f.getSum(i, mid) <= k + 1) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                j = l;
            }
            {
                int l = -1;
                int r = cnt[a[i]];
                int[] z = d[a[i]];
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    if (z[mid] >= j) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                j = l;
            }
            answer = Math.max(answer, j - index[i] + 1);
        }
        return answer;
    }
}
