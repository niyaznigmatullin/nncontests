package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        Map<Integer, Integer> last = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        for (int i = n - 1; i >= 0; i--) {
            Integer z = last.get(a[i]);
            if (z != null) {
                prev[z] = i;
            }
            last.put(a[i], i);
        }
        Hash h = new Hash(a);
        List<Segment>[] b = new List[n];
        for (int i = 0; i < n; i++) {
            b[i] = new ArrayList<Segment>(1);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = prev[i]; j >= 0; j = prev[j]) {
                b[i - j].add(new Segment(j, i));
            }
        }
        int removed = 0;
        for (int i = 0; i < n; i++) {
            Collections.sort(b[i]);
        }
        for (int i = 0; i < n; i++) {
            for (Segment e : b[i]) {
                int left = e.a * 2 - e.b + 1;
                if (left < removed ||
                        h.get1(left, e.a + 1) != h.get1(e.a + 1, e.b + 1)
                        || h.get2(left, e.a + 1) != h.get2(e.a + 1, e.b + 1)) {
                    continue;
                }
                removed = e.a + 1;
            }
        }
        out.println(n - removed);
        out.printArray(Arrays.copyOfRange(a, removed, n));
	}

    static class Segment implements Comparable<Segment> {
        int a;
        int b;

        Segment(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Segment o) {
            return a - o.a;
        }
    }

    static final Random rand = new Random(234734L);
    static class Hash {
        static final int P1 = BigInteger.probablePrime(23, rand).intValue();
        static final int P2 = BigInteger.probablePrime(22, rand).intValue();
        static final int MOD1 = BigInteger.probablePrime(30, rand).intValue();
        static final int MOD2 = BigInteger.probablePrime(30, rand).intValue();
        static int[] POW1;
        static int[] POW2;
        static final int MAXN = 300000;
        static {
            POW1 = new int[MAXN];
            POW2 = new int[MAXN];
            POW1[0] = 1;
            POW2[0] = 1;
            for (int i = 1; i < MAXN; i++) {
                POW1[i] = (int) ((long) POW1[i - 1] * P1 % MOD1);
                POW2[i] = (int) ((long) POW2[i - 1] * P2 % MOD2);
            }
        }
        int[] a;
        int[] hash1;
        int[] hash2;

        Hash(int[] a) {
            this.a = a;
            int n = a.length;
            hash1 = new int[n];
            hash2 = new int[n];
            long h = 0;
            for (int i = 0; i < n; i++) {
                h = (h * P1 + a[i]) % MOD1;
                hash1[i] = (int) h;
            }
            h = 0;
            for (int i = 0; i < n; i++) {
                h = (h * P2 + a[i]) % MOD2;
                hash2[i] = (int) h;
            }
        }

        int get1(int left, int right) {
            long ret = hash1[right - 1];
            if (left > 0) {
                ret -= (long) hash1[left - 1] * POW1[right - left] % MOD1;
                if (ret < 0) {
                    ret += MOD1;
                }
            }
            return (int) ret;
        }

        int get2(int left, int right) {
            long ret = hash2[right - 1];
            if (left > 0) {
                ret -= (long) hash2[left - 1] * POW2[right - left] % MOD2;
                if (ret < 0) {
                    ret += MOD2;
                }
            }
            return (int) ret;
        }
    }
}
