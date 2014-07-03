package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;

public class SimilarNames {
    public int count(String[] names, int[] info1, int[] info2) {
        int n = names.length;
        boolean[][] isPrefix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isPrefix[i][j] = names[j].startsWith(names[i]);
            }
        }
        int[] count = new int[n];
        for (int i : info1) {
            count[i]++;
        }
        for (int i : info2) {
            count[i]++;
        }
        int[] all = new int[info1.length + info2.length];
        cc = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > 1) {
                all[cc++] = i;
            }
        }
        all = ArrayUtils.sortAndUnique(all);
        for (int i = 0; i < info1.length; i++) {
            info1[i] = Arrays.binarySearch(all, info1[i]);
            info2[i] = Arrays.binarySearch(all, info2[i]);
        }
        cc = all.length;
        down = new int[info1.length + info2.length];
        up = new int[down.length];
        masks = new int[down.length];
        for (int i = 0; i < info1.length; i++) {
            if (info1[i] >= 0) {
                if (info2[i] >= 0) {
                    masks[info1[i]] |= 1 << info2[i];
                } else {
                    down[info1[i]]++;
                }
            } else {
                if (info2[i] >= 0) {
                    up[info2[i]]++;
                } else {
                    info1[i] = cc++;
                    down[info1[i]]++;
                }
            }
        }
        vCount = info1.length + info2.length;
        int allLen = 1;
        for (String e : names) {
            allLen += e.length();
        }
        trie = new int[26][allLen];
        for (int[] d : trie) {
            Arrays.fill(d, -1);
        }
        terminal = new boolean[allLen];
        free = 1;
        for (String e : names) {
            addWord(e);
        }
        int root = contract(0);
        dp = new int[allLen][][][];
        dfs(root);
        int[][][] cdp = dp[root];
        int ans = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] == 0) ++left;
        }
        for (int h1 = 0; h1 <= vCount; h1++) {
            int val = cdp[h1][0][(1 << cc) - 1];
            if (val == 0) continue;
            ans = add(ans, val);
        }
        for (int i = 2; i <= left; i++) ans = mul(ans, i);
        return ans;
    }

    static int[][] C;

    static {
        C = new int[555][555];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = add(C[i - 1][j - 1], C[i - 1][j]);
            }
        }
    }

    static int c(int n, int k) {
        if (k < 0 || k > n) return 0;
        return C[n][k];
    }

    static int[] down, up, masks;
    static int[][][][] dp;
    static int cc;
    static int vCount;

    static void dfs(int v) {
        for (int i = 0; i < 26; i++) {
            int to = trie[i][v];
            if (to < 0) {
                continue;
            }
            dfs(to);
        }
        int[][][] cdp = new int[vCount + 1][vCount + 1][1 << cc];
        cdp[0][0][0] = 1;
        int[][][] nextDP = new int[vCount + 1][vCount + 1][1 << cc];
        for (int i = 0; i < 26; i++) {
            int to = trie[i][v];
            if (to < 0) {
                continue;
            }
            int[][][] ndp = dp[to];
            for (int[][] d2 : nextDP) {
                for (int[] d1 : d2) {
                    Arrays.fill(d1, 0);
                }
            }
            for (int mask = 0; mask < 1 << cc; mask++) {
                for (int h1 = 0; h1 <= vCount; h1++) {
                    for (int h2 = 0; h2 <= vCount; h2++) {
                        int val = ndp[h1][h2][mask];
                        if (val == 0) continue;
                        int notMask = ((1 << cc) - 1) & (~mask);
                        for (int subMask = notMask; ; subMask = subMask - 1 & notMask) {
                            for (int g1 = 0; g1 <= vCount; g1++) {
                                for (int g2 = 0; g2 <= vCount; g2++) {
                                    int val2 = cdp[g1][g2][subMask];
                                    if (val2 == 0) {
                                        continue;
                                    }
                                    nextDP[Math.min(g1 + h1, vCount)][Math.min(g2 + h2, vCount)][subMask | mask] =
                                            add(nextDP[g1 + h1][g2 + h2][subMask | mask], mul(val, val2));
                                }
                            }
                            if (subMask == 0) {
                                break;
                            }
                        }
                    }
                }
            }
            for (int h1 = 0; h1 <= vCount; h1++) {
                for (int h2 = 0; h2 <= vCount; h2++) {
                    for (int mask = 0; mask < 1 << cc; mask++) {
                        cdp[h1][h2][mask] = nextDP[h1][h2][mask];
                    }
                }
            }
        }
        if (terminal[v]) {
            for (int[][] d2 : nextDP) {
                for (int[] d1 : d2) {
                    Arrays.fill(d1, 0);
                }
            }
            for (int mask = 0; mask < 1 << cc; mask++) {
                for (int i = 0; i < cc; i++) {
                    if (((mask >> i) & 1) == 1 || (mask & masks[i]) != masks[i]) continue;
                    for (int h1 = down[i]; h1 <= vCount; h1++) {
                        for (int h2 = 0; h2 + up[i] <= vCount; h2++) {
                            int val = cdp[h1][h2][mask];
                            if (val == 0) {
                                continue;
                            }
                            nextDP[h1 - down[i]][h2 + up[i]][mask | (1 << i)] = add(nextDP[h1 - down[i]][h2 + up[i]][mask | (1 << i)], mul(mul(val, c(h1, down[i])), f(down[i])));
                        }
                    }
                }
            }
            for (int mask = 0; mask < 1 << cc; mask++) {
                for (int h1 = 0; h1 <= vCount; h1++) {
                    for (int h2 = 0; h2 <= vCount; h2++) {
                        int val = cdp[h1][h2][mask];
                        if (val == 0) {
                            continue;
                        }
                        nextDP[Math.min(h1 + 1, vCount)][h2][mask] = add(nextDP[Math.min(h1 + 1, vCount)][h2][mask], val);
                    }
                }
            }
            for (int mask = 0; mask < 1 << cc; mask++) {
                for (int h1 = 0; h1 <= vCount; h1++) {
                    for (int h2 = 1; h2 <= vCount; h2++) {
                        int val = cdp[h1][h2][mask];
                        if (val == 0) {
                            continue;
                        }
                        nextDP[h1][h2 - 1][mask] = add(nextDP[h1][h2 - 1][mask], mul(val, h2));
                    }
                }
            }
            for (int h1 = 0; h1 <= vCount; h1++) {
                for (int h2 = 0; h2 <= vCount; h2++) {
                    for (int mask = 0; mask < 1 << cc; mask++) {
                        cdp[h1][h2][mask] = nextDP[h1][h2][mask];
                    }
                }
            }
        }
//        System.err.println(v);
        dp[v] = cdp;
        for (int h1 = 0; h1 <= vCount; h1++) {
            for (int h2 = 0; h2 <= vCount; h2++) {
                for (int mask = 0; mask < 1 << cc; mask++) {
//                    System.err.println(h1 + " " + h2 + " " + mask + " " + cdp[h1][h2][mask]);
                }
            }
        }
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static final int MOD = 1000000007;

    static int contract(int v) {
        int count = 0;
        int last = -1;
        for (int i = 0; i < 26; i++) {
            int to = trie[i][v];
            if (to < 0) {
                continue;
            }
            ++count;
            last = contract(to);
            trie[i][v] = last;
        }
        if (count < 2 || terminal[v]) {
            return v;
        }
        return last;
    }

    static void addWord(String e) {
        int v = 0;
        for (char ch : e.toCharArray()) {
            int c = ch - 'a';
            if (trie[c][v] < 0) {
                trie[c][v] = free++;
            }
            v = trie[c][v];
        }
        terminal[v] = true;
    }

    static boolean[] terminal;
    static int free;
    static int[][] trie;

    static int f(int n) {
        int ret = 1;
        for (int i = 1; i <= n; i++) {
            ret = mul(ret, i);
        }
        return ret;
    }
}
