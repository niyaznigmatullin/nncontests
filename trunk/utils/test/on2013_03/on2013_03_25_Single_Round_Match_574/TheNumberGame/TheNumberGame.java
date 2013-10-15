package lib.test.on2013_03.on2013_03_25_Single_Round_Match_574.TheNumberGame;



import java.util.*;

public class TheNumberGame {
    public String determineOutcome(int a, int b) {
        Map<Integer, Integer> sa = getSet(a);
        Map<Integer, Integer> sb = getSet(b);
        List<Integer>[] za = getGraph(sa);
        List<Integer>[] zb = getGraph(sb);
        int[] n1 = getArray(sa);
        int[] n2 = getArray(sb);
        int n = za.length;
        int m = zb.length;
        boolean[][] eq = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (n1[i] == n2[j]) {
                    eq[i][j] = true;
                }
            }
        }
        boolean[][] win = new boolean[n][m];
        boolean[][] next = new boolean[n][m];
        for (int move = 999; move >= 0; move--) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (eq[i][j]) {
                        next[i][j] = (move & 1) == 0;
                        continue;
                    }
                    if ((move & 1) == 0) {
                        for (int it = 0; it < za[i].size(); it++) {
                            int to = za[i].get(it);
                            if (!win[to][j]) {
                                next[i][j] = true;
                            }
                        }
                    } else {
                        for (int it = 0; it < zb[j].size(); it++) {
                            int to = zb[j].get(it);
                            if (!win[i][to]) {
                                next[i][j] = true;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    win[i][j] = next[i][j];
                    next[i][j] = false;
                }
            }
        }
        return win[sa.get(a)][sb.get(b)] ? "Manao wins" : "Manao loses";
    }

    static int[] getArray(Map<Integer, Integer> s) {
        int[] ret = new int[s.size()];
        for (int i : s.keySet()) {
            ret[s.get(i)] = i;
        }
        return ret;
    }

    static List<Integer>[] getGraph(Map<Integer, Integer> s) {
        List<Integer>[] ret = new ArrayList[s.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new ArrayList<Integer>();
        }
        for (int i : s.keySet()) {
            int j = 0;
            for (int x = i; x > 0; x /= 10) {
                j = j * 10 + x % 10;
            }
            ret[s.get(i)].add(s.get(j));
            ret[s.get(i)].add(s.get(i / 10));
        }
        return ret;
    }

    static Map<Integer, Integer> getSet(int n) {
        Map<Integer, Integer> ret = new HashMap<Integer, Integer>();
        String s = (n + "");
        String srev = rev(s);
        ret.put(0, 0);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                int x = Integer.parseInt(s.substring(i, j));
                if (!ret.containsKey(x)) {
                    ret.put(x, ret.size());
                }
                int y = Integer.parseInt(srev.substring(i, j));
                if (!ret.containsKey(y)) {
                    ret.put(y, ret.size());
                }
            }
        }
        return ret;
    }

    static String rev(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
