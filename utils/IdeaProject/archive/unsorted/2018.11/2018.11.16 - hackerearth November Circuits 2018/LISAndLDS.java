package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class LISAndLDS {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextInt();
        int[] vEdges = new int[m];
        int[] uEdges = new int[m];
        for (int i = 0; i < m; i++) {
            vEdges[i] = in.nextInt() - 1;
            uEdges[i] = in.nextInt() - 1;
        }
        int[][] edges = GraphUtils.getEdgesUndirectedUnweighted(n, vEdges, uEdges);
        int[] answer = solve2(n, edges);
        out.println(answer.length);
        for (int i = 0; i < answer.length; i++) {
            if (i > 0) out.print(' ');
            out.print(answer[i] + 1);
        }
        out.println();
    }

    private int[] solve1(int n, int[][] edges) {
        long start = System.currentTimeMillis();
        Random rng = new Random(12323L);
        int[] path = new int[n];
        boolean[] used = new boolean[n];
        int[] answer = new int[]{0};
        long best = Long.MIN_VALUE;
        while (System.currentTimeMillis() - start < 1900) {
            for (int[] e : edges) {
                ArrayUtils.shuffle(e);
            }
            int v = rng.nextInt(n);
            int cn = 0;
            path[cn++] = v;
            Arrays.fill(used, false);
            used[v] = true;
            loop: while (true) {
                for (int to : edges[v]) {
                    if (used[to]) continue;
                    used[to] = true;
                    path[cn++] = to;
                    v = to;
                    continue loop;
                }
                break;
            }
            int[] a = Arrays.copyOf(path, cn);
            long value = (long) lis(a) * lds(a);
            if (value > best) {
                best = value;
                answer = a;
            }
        }
        return answer;
    }

    private int[] solve2(int n, int[][] edges) {
        long start = System.currentTimeMillis();
        Random rng = new Random(12323L);
        int[] answer = new int[]{0};
        long best = Long.MIN_VALUE;
        int[] fInc = new int[n + 1];
        int[] fDec = new int[n + 1];
        while (System.currentTimeMillis() - start < 3500) {
            for (int[] e : edges) ArrayUtils.shuffle(e);
            int v = rng.nextInt(n);
            Arrays.fill(fInc, Integer.MAX_VALUE);
            Arrays.fill(fDec, Integer.MIN_VALUE);
            fInc[0] = Integer.MIN_VALUE;
            fDec[0] = Integer.MAX_VALUE;
            Answer ans = new Answer(Long.MIN_VALUE, -1, -1);
            ans.dfs(v, fInc, 0, fDec, 0, edges, v);
            if (ans.best > best) {
                best = ans.best;
                answer = ans.getPath(ans.from, ans.to, edges);
            }
        }
        return answer;
    }

    static class Answer {
        long best;
        int from;
        int to;

        public Answer(long best, int from, int to) {
            this.best = best;
            this.from = from;
            this.to = to;
        }

        int[] path;
        int cn;
        boolean[] used;

        int[] getPath(int v, int t, int[][] edges) {
            if (path == null) {
                path = new int[edges.length];
                used = new boolean[edges.length];
                cn = 0;
            }
            path[cn++] = v;
            if (v == t) {
                return Arrays.copyOf(path, cn);
            }
            used[v] = true;
            for (int to : edges[v]) {
                if (used[to]) continue;
                int[] got = getPath(to, t, edges);
                if (got != null) return got;
            }
            --cn;
            return null;
        }

        void dfs(int v, int[] fInc, int bestInc, int[] fDec, int bestDec, int[][] edges, int start) {
            if (used == null) {
                used = new boolean[edges.length];
            }
            int curInc;
            int oldIncValue;
            int curDec;
            int oldDecValue;
            {
                int left = 0;
                int right = bestInc + 1;
                while (left < right - 1) {
                    int mid = (left + right) >>> 1;
                    if (fInc[mid] < v) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                oldIncValue = fInc[right];
                fInc[right] = v;
                curInc = right;
            }
            {
                int left = 0;
                int right = bestDec + 1;
                while (left < right - 1) {
                    int mid = (left + right) >>> 1;
                    if (fDec[mid] > v) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                oldDecValue = fDec[right];
                fDec[right] = v;
                curDec = right;
            }
            used[v] = true;
            for (int to : edges[v]) {
                if (used[to]) continue;
                dfs(to, fInc, Math.max(bestInc, curInc), fDec, Math.max(bestDec, curDec), edges, start);
            }
            long curValue = (long) Math.max(bestInc, curInc) * Math.max(bestDec, curDec);
            if (curValue > best) {
                best = curValue;
                from = start;
                to = v;
            }
            fInc[curInc] = oldIncValue;
            fDec[curDec] = oldDecValue;
        }

    }

    static int lis(int[] a) {
        int[] f = new int[a.length + 1];
        f[0] = Integer.MIN_VALUE;
        int best = 0;
        for (int x : a) {
            int left = 0;
            int right = best + 1;
            while (left < right - 1) {
                int mid = (left + right) >>> 1;
                if (f[mid] < x) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            f[right] = x;
            best = Math.max(best, right);
        }
        return best;
    }

    static int lds(int[] a) {
        a = a.clone();
        for (int i = 0; i < a.length; i++) a[i] = -a[i];
        return lis(a);
    }
}
