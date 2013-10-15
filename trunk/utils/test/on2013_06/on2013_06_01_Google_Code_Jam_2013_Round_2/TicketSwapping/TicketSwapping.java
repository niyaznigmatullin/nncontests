package lib.test.on2013_06.on2013_06_01_Google_Code_Jam_2013_Round_2.TicketSwapping;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TicketSwapping {

    static final int MOD = 1000002013;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        int m = in.nextInt();
        final int[] start = new int[m];
        int[] finish = new int[m];
        int[] count = new int[m];
        for (int i = 0; i < m; i++) {
            start[i] = in.nextInt();
            finish[i] = in.nextInt();
            count[i] = in.nextInt();
        }
        long curAnswer = getAnswer(n, m, start, finish, count);
//        boolean changed = true;
//        while (changed) {
//            changed = false;
//            for (int i = 0; i < m; i++) {
//                for (int j = i + 1; j < m; j++) {
//                    long ans1 = getAnswer(n, Math.abs(start[i] - finish[i])) + getAnswer(n, Math.abs(start[i] - finish[i]));
//                    long ans2 = getAnswer(n, Math.abs(start[i] - finish[j])) + getAnswer(n, Math.abs(start[j] - finish[i]));
//                    if (ans2 < ans1) {
//                        int t = finish[i];
//                        finish[i] = finish[j];
//                        finish[j] = t;
//                    }
//                }
//            }
//        }
        Integer[] id = new Integer[m];
        for (int i = 0; i < m; i++) {
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return start[o1] - start[o2];
            }
        });
        long bestAnswer = 0;
        for (int i = 0; i < m; ) {
            int end = finish[id[i]];
            int j = i;
            while (j < m && start[id[j]] <= end) {
                end = Math.max(end, finish[id[j]]);
                ++j;
            }
            int[] xs = new int[j - i + j - i];
            for (int k = i; k < j; k++) {
                xs[k - i] = start[id[k]];
                xs[k - i + j - i] = finish[id[k]];
            }
            xs = ArrayUtils.sortAndUnique(xs);
            int zn = xs.length;
            long[] cnt1 = new long[zn];
            long[] cnt2 = new long[zn];
            for (int k = i; k < j; k++) {
                cnt1[Arrays.binarySearch(xs, start[id[k]])] += count[id[k]];
                cnt2[Arrays.binarySearch(xs, finish[id[k]])] += count[id[k]];
            }
            for (int k = 0; k < zn; k++) {
                long z = Math.min(cnt1[k], cnt2[k]);
                cnt1[k] -= z;
                cnt2[k] -= z;
            }
            List<Integer> idz = new ArrayList<>();
            List<Long> avail = new ArrayList<>();
            for (int k = 0; k < zn; k++) {
                if (cnt1[k] > 0) {
                    idz.add(k);
                    avail.add(cnt1[k]);
                } else if (cnt2[k] > 0) {
                    while (cnt2[k] > 0) {
                        long got = Math.min(avail.get(avail.size() - 1), cnt2[k]);
                        bestAnswer = (bestAnswer + got % MOD * getAnswerMod(n, xs[k] - xs[idz.get(avail.size() - 1)])) % MOD;
                        avail.set(avail.size() - 1, avail.get(avail.size() - 1) - got);
                        cnt2[k] -= got;
                        while (!avail.isEmpty() && avail.get(avail.size() - 1) == 0) {
                            avail.remove(avail.size() - 1);
                            idz.remove(idz.size() - 1);
                        }
                    }
                }
            }
//            for (int k1 = 0, k2 = zn - 1; k1 < zn && k2 >= 0; ) {
//                while (k1 < zn && cnt1[k1] == 0) {
//                    k1++;
//                }
//                while (k2 >= 0 && cnt2[k2] == 0) {
//                    k2--;
//                }
//                if (k1 >= zn && k2 < 0) {
//                    break;
//                }
//                if (k1 >= zn || k2 < 0) {
//                    throw new AssertionError();
//                }
//                long z = Math.min(cnt1[k1], cnt2[k2]);
//                bestAnswer = (bestAnswer + z % MOD * getAnswerMod(n, Math.abs(xs[k1] - xs[k2]))) % MOD;
//                cnt1[k1] -= z;
//                cnt2[k2] -= z;
//            }
            i = j;
        }
        bestAnswer %= MOD;
        out.println((curAnswer - bestAnswer + MOD) % MOD);
    }

    private long getAnswer(int n, int m, int[] start, int[] finish, int[] count) {
        long curAnswer = 0;
        for (int i = 0; i < m; i++) {
            curAnswer = (curAnswer + count[i] * getAnswerMod(n, finish[i] - start[i])) % MOD;
        }
        curAnswer %= MOD;
        return curAnswer;
    }

    static long getAnswerMod(long n, long x) {
        return getAnswer(n, x) % MOD;
    }

    static long getAnswer(long n, long x) {
        return (f(n) - f(n - x));
    }

    static long f(long n) {
        return n * (n + 1) / 2;
    }
}
