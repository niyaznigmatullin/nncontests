package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class ProblemE {

    static final Random rand = new Random(121123L);
    static final long PRIME = BigInteger.probablePrime(20, rand).longValue();
    static final long[] pows;
    static final int MAXN = 1000;

    static {
        pows = new long[MAXN];
        pows[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            pows[i] = pows[i - 1] * PRIME;
        }
    }

    static class Hash {
        String s;
        long[] hash;

        Hash(String s) {
            this.s = s;
            hash = new long[s.length()];
            long curHash = 0;
            for (int i = 0; i < s.length(); i++) {
                curHash = curHash * PRIME + s.charAt(i);
                hash[i] = curHash;
            }
        }

        long getHash(int l, int r) {
            long ret = hash[r - 1];
            if (l > 0) {
                ret -= pows[r - l] * hash[l - 1];
            }
            return ret;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        Set<Long> set = new HashSet<Long>();
        Hash[] h = new Hash[n];
        for (int i = 0; i < n; i++) {
            h[i] = new Hash(s[i]);
            set.add(h[i].hash[h[i].s.length() - 1]);
        }
        boolean[][] canSuf = new boolean[n][];
        for (int i = 0; i < n; i++) {
            String cur = s[i];
            canSuf[i] = new boolean[cur.length() + 1];
            boolean[] can = canSuf[i];
            can[cur.length()] = true;
            for (int j = cur.length() - 1; j >= 0; j--) {
                for (int k = j + 1; k <= cur.length(); k++) {
                    if (!can[k]) {
                        continue;
                    }
                    if (set.contains(h[i].getHash(j, k))) {
                        can[j] = true;
                        break;
                    }
                }
            }
        }
        List<Integer>[] allSuf = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            allSuf[i] = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (s[j].length() > s[i].length() && s[j].startsWith(s[i])) {
                    allSuf[i].add(j);
                }
            }
        }
        int curAnswer = Integer.MAX_VALUE;
        String answer = null;
        for (int i = 0; i < n; i++) {
            String cur = s[i];
            boolean[] can = new boolean[cur.length() + 1];
            can[0] = true;
            for (int j = 1; j <= cur.length(); j++) {
                for (int k = 0; k < j; k++) {
                    if (!can[k]) {
                        continue;
                    }
                    if (set.contains(h[i].getHash(k, j))) {
                        can[j] = true;
                        break;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                String last = s[j];
                all:
                for (int common = Math.min(cur.length() - 1, last.length() - 1); common > 0; common--) {
                    if (!can[cur.length() - common] || canSuf[j][common]
                            || h[i].getHash(cur.length() - common, cur.length()) != h[j].getHash(0, common)) {
                        continue;
                    }
                    int all = cur.length() + last.length() - common;
                    if (all >= curAnswer) {
                        break;
                    }
                    long hash = h[i].getHash(0, cur.length()) * pows[last.length() - common]
                            + h[j].getHash(common, last.length());
                    for (int z = 0; z < allSuf[i].size(); z++) {
                        int id = allSuf[i].get(z);
                        if (s[id].length() < all) {
                            long hash2 = h[id].getHash(0, s[id].length()) * pows[all - s[id].length()]
                                    + h[j].getHash(s[id].length() - cur.length() + common, last.length());
                            if (hash == hash2) {
                                continue all;
                            }
                        } else if (s[id].length() == all) {
                            if (hash == h[id].getHash(0, s[id].length())) {
                                continue all;
                            }
                        }
                    }
                    curAnswer = all;
                    answer = cur + last.substring(common);
                    break;
                }
            }
        }
        if (answer == null) {
            out.println("Good vocabulary!");
        } else {
            out.println(answer);
        }
    }
}
