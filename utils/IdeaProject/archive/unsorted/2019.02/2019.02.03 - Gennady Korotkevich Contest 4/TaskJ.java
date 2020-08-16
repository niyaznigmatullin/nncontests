package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class TaskJ {

    static long UP_TO = 1000000000L;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        gen();
        long left = in.nextLong();
        long right = in.nextLong();
        out.println(get(right) - get(left - 1));
//        Random rng = new Random(123L);
//        for (int it = 1000000; it <= 2000000; it++) {
//            int i = rng.nextInt(9000000) + 1000000;
//            long smart = get(i);
//            long stupid = stupid(i);
//            if (i % 100000 == 0) System.err.println(i);
//            if (smart != stupid) {
//                System.out.println("i = " + i + ", " + smart + " " + stupid + " " + (stupid != smart ? "BAD" : ""));
//                break;
//            }
//        }
    }

    static long stupid(final long n) {
        int l = -1;
        int r = STUPID.length;
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (STUPID[mid] > n) r = mid; else l = mid;
        }
        return r;
    }


    static long get(long n) {
        n++;
        int[] d = convert(n);
        int len = d.length;
        List<int[]>[] patterns = new List[len + 1];
        for (int i = 0; i <= len; i++) {
            patterns[i] = new ArrayList<>();
        }
        for (int x : LESS_THAN_100000) {
            int[] e = convert(x);
            if (e.length <= len) {
                boolean sixnine = e.length >= 2;
                for (int i : e) sixnine &= i == 6 || i == 0 || i == 9;
                if (!sixnine)
                patterns[e.length].add(e);
            }
        }
        for (int patternLength = 6; patternLength <= len; patternLength++) {
            {
                int[] e = new int[patternLength];
                e[0] = 8;
                e[e.length - 1] = 1;
                patterns[e.length].add(e);
            }
            {
                int[] e = new int[patternLength];
                e[0] = 4;
                e[e.length - 1] = 9;
                for (int i = 1; i < e.length - 1; i++) e[i] = 6;
                patterns[e.length].add(e);
            }
//            {
//                int[] e = new int[patternLength];
//                e[0] = 6;
//                e[e.length - 1] = 9;
//                e[e.length - 2] = 4;
//                patterns[e.length].add(e);
//            }
        }
        long ans = 0;
        for (int curLen = 1; curLen < len; curLen++) {
            for (int patternLength = 1; patternLength <= curLen; patternLength++) {
                long appendWays = (1L << (2 * (curLen - patternLength)));
                ans += patterns[patternLength].size() * appendWays;
                if (patternLength >= 2) {
                    ans += 2 * POW3[patternLength - 2] * appendWays;
                }
            }
        }
//        {
//            List<int[]> q = new ArrayList<>();
//            q.add(new int[]{6});
//            q.add(new int[]{9});
//            for (int i = 0; i < q.size(); i++) {
//                int[] e = q.get(i);
//                if (e.length >= 2 && e[e.length - 1] == 9) {
//                    patterns[e.length].add(e);
//                }
//                if (e.length + 1 <= len) {
//                    for (int x : new int[]{0, 6, 9}) {
//                        int[] ne = Arrays.copyOf(e, e.length + 1);
//                        ne[ne.length - 1] = x;
//                        q.add(ne);
//                    }
//                }
//            }
//        }
        for (int patternLength = 1; patternLength <= len; patternLength++) {
            for (int lcp = 0; lcp < len; lcp++) {
                for (int[] pattern : patterns[patternLength]) {
                    boolean check = true;
                    for (int i = 0; i < lcp && i < pattern.length; i++) {
                        if (pattern[i] != d[i]) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    for (int i = patternLength; i < lcp; i++) {
                        if (!canAppendWith(d[i])) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    for (int nextDigit = 0; nextDigit < d[lcp]; nextDigit++) {
                        if (lcp < pattern.length) {
                            if (nextDigit != pattern[lcp]) {
                                continue;
                            }
                        } else if (!canAppendWith(nextDigit)) {
                            continue;
                        }
                        int appendLength = len - Math.max(lcp + 1, patternLength);
                        long appendWays = 1L << (2 * appendLength);
                        ans += appendWays;
                    }
                }
                if (patternLength >= 2) {
                    boolean check = true;
                    for (int i = 0; i < lcp && i < patternLength; i++) {
                        if (d[i] != 6 && d[i] != 9 && d[i] != 0) {
                            check = false;
                            break;
                        }
                        if (i == patternLength - 1 && d[i] != 9) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    for (int i = patternLength; i < lcp; i++) {
                        if (!canAppendWith(d[i])) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) continue;
                    for (int nextDigit = 0; nextDigit < d[lcp]; nextDigit++) {
                        if (lcp < patternLength) {
                            if (nextDigit != 6 && nextDigit != 9 && nextDigit != 0) {
                                continue;
                            }
                            if (lcp == 0 && nextDigit != 6 && nextDigit != 9) continue;
                            if (lcp == patternLength - 1 && nextDigit != 9) continue;
                        } else if (!canAppendWith(nextDigit)) {
                            continue;
                        }
                        int appendLength = len - Math.max(lcp + 1, patternLength);
                        long appendWays = 1L << (2 * appendLength);
                        for (int i = lcp + 1; i + 1 < patternLength; i++) {
                            appendWays *= 3;
                        }
//                        System.out.println(Arrays.toString(d) + " " + lcp + " " + nextDigit + " " + patternLength + " " + appendLength + " " + appendWays);
                        ans += appendWays;
                    }
                }
            }
        }
        return ans;
    }

    static boolean canAppendWith(int d) {
        return d == 4 || d == 6 || d == 8 || d == 0;
    }

    static final long[] POW3 = new long[20];

    static {
        POW3[0] = 1;
        for (int i = 1; i < POW3.length; i++) POW3[i] = POW3[i - 1] * 3;
    }

    private static int[] convert(long n) {
        int[] d = new int[(n + "").length()];
        {
            long m = n;
            for (int i = 0; i < d.length; i++) {
                d[d.length - i - 1] = (int) (m % 10);
                m /= 10;
            }
        }
        return d;
    }

    static void gen() {
        Queue<Long> q = new ArrayDeque<>();
        Set<Long> all = new HashSet<>();
        q.add(1L);
        q.add(4L);
        q.add(6L);
        q.add(8L);
        q.add(9L);
        all.add(1L);
        all.add(4L);
        all.add(6L);
        all.add(8L);
        all.add(9L);
        int cc = 0;
        while (!q.isEmpty()) {
            long v = q.poll();
            ++cc;
            if (cc % 100000 == 0) System.err.println(cc);
            boolean[] has = new boolean[10];
            for (long e = v; e > 0; e /= 10) has[(int) (e % 10)] = true;
            for (int d = 0; d < 10; d++) {
                if (has[1] && (d == 1 || d == 9)) {
                    continue;
                }
                if ((has[4] || has[6]) && d == 1) continue;
                if (has[8] && d == 9) continue;
                if (d == 2 || d == 3 || d == 5 || d == 7) {
                    continue;
                }
                long nv = v * 10 + d;
                if (nv > UP_TO || all.contains(nv)) continue;
                if (d % 2 == 0 || check(nv)) {
                    q.add(nv);
                    all.add(nv);
                }
            }
        }
        STUPID = new long[all.size()];
        int cn = 0;
        for (long e : all) {
            STUPID[cn++] = e;
        }
        Arrays.sort(STUPID);
        System.out.println(all.stream().filter(x -> x > 10000 && x % 10 == 9 && contains(x, 4)).collect(Collectors.toList()));
//        Set<Long> z = all.stream().filter(x -> x % 10 == 1 || x % 10 == 9).collect(Collectors.toSet());
//        System.out.println(z);
//        System.out.println(z.size());
//        for (int i = 10; i <= 100000; i *= 10) {
//            final int v = i;
//            System.out.println(all.stream().filter(x -> x < v).collect(Collectors.toList()).size());
//        }
    }


    static long[] STUPID;

    static int[] LESS_THAN_100000 = {1, 4, 6, 8, 9, 66049, 96009, 6669, 9999, 69909, 66069, 99609, 99099, 60699, 90909, 801, 69669, 60969, 6699, 90669, 66609, 49, 9009, 66099, 6969, 4669, 8001, 69699, 99909, 96069, 69, 60999, 9801, 90699, 46669, 69969, 81, 99669, 6999, 90969, 66649, 91, 66909, 96609, 609, 96099, 99, 60009, 66669, 9069, 69999, 99699, 90999, 9081, 6009, 99969, 80001, 901, 649, 9609, 66699, 9099, 96909, 909, 69009, 60049, 66969, 90009, 96669, 669, 99999, 6049, 60069, 949, 9909, 6069, 66999, 96699, 699, 99009, 60609, 60099, 9669, 96969, 969, 94669, 69069, 6609, 6099, 90069, 469, 981, 66009, 9699, 96999, 999, 69609, 69099, 60909, 90609, 9969, 90099, 6649, 99069, 9469, 6909, 60669,
            6600049, 6660049, 600049, 660049, 666049, 6000049};


    static int count(long v, int d) {
        return v == 0 ? 0 : (v % 10 == d ? 1 : 0) + count(v / 10, d);
    }

    static boolean contains(long v, int d) {
        return count(v, d) > 0;
    }

    static boolean check(long v) {
        String s = v + "";
        for (int mask = 1 << (s.length() - 1); mask < 1 << s.length(); mask++) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (((mask >> i) & 1) == 0) continue;
                t.append(s.charAt(i));
            }
            if (new BigInteger(t.toString()).isProbablePrime(20)) {
                return false;
            }
        }
        return true;
    }
}
