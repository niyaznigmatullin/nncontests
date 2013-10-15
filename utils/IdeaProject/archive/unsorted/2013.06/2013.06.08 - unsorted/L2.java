package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L2 {

    static class SAT3Solver {
        List<int[]> a;
        boolean[] was;
        boolean[] ans;
        List<int[]>[] whereMax;
        List<int[]>[] where;

        SAT3Solver(int var) {
            a = new ArrayList<>();
            was = new boolean[var];
            whereMax = new List[var];
            where = new List[var];
            for (int i = 0; i < var; i++) {
                whereMax[i] = new ArrayList<>();
                where[i] = new ArrayList<>();
            }
        }

        void addClause(int[] b) {
            a.add(b);
            int mv = 0;
            for (int i : b) {
                mv = Math.max(mv, getVar(i));
                where[getVar(i)].add(b);
            }
            whereMax[mv].add(b);
        }

        static int getVar(int a) {
            return a < 0 ? ~a : a;
        }

        boolean getValue(int a) {
            return a < 0 ? !ans[~a] : ans[a];
        }

        boolean go(int v) {
            if (v == was.length) {
                if (check(v)) return false;
                return true;
            }
            if (was[v]) {
                ans[v] = true;
                return go(v + 1);
            }
            boolean needTrue = false;
            boolean needFalse = false;
            for (int[] e : where[v]) {
                if (needTrue && needFalse) {
                    break;
                }
                boolean alreadyTrue = false;
                boolean wasVTrue = false;
                boolean wasVFalse = false;
                for (int j : e) {
                    if (getVar(j) < v && getValue(j)) {
                        alreadyTrue = true;
                        break;
                    }
                    if (getVar(j) == v && j >= 0) {
                        wasVTrue = true;
                    }
                    if (getVar(j) == v && j < 0) {
                        wasVFalse = true;
                    }
                }
                if (!alreadyTrue) {
                    if (wasVTrue) needTrue = true;
                    if (wasVFalse) needFalse = true;
                }
            }
//            needTrue = needFalse = true;
            if (needTrue) {
                ans[v] = true;
                if (checkClausesHere(v) && go(v + 1)) {
                    return true;
                }
            }
            if (needFalse) {
                ans[v] = false;
                if (checkClausesHere(v) && go(v + 1)) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkClausesHere(int v) {
            boolean ok = true;
            for (int[] b : whereMax[v]) {
                if (!checkClause(v + 1, b)) {
                    ok = false;
                    break;
                }
            }
            return ok;
        }

        private boolean check(int v) {
            for (int i = 0; i < a.size(); i++) {
                int[] e = a.get(i);
                boolean ok = checkClause(v, e);
                if (!ok) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkClause(int v, int[] e) {
            boolean ok = false;
            for (int j : e) {
                if (getVar(j) >= v || getValue(j)) {
                    ok = true;
                    break;
                }
            }
            return ok;
        }

        boolean[] solve() {
            ans = new boolean[was.length];
            if (!go(0)) return null;
            return ans;
        }
    }

    static int getClause(int x) {
        if (x / 100 == 2) {
            return ~(x % 100);
        } else {
            return x % 100;
        }
    }

    static boolean isDoor(int x) {
        return x / 100 >= 2;
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static String DIR = "DRUL";


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int n = in.nextInt();
        int m = in.nextInt();
        int maxVar = 0;
        int[][] field = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = in.nextInt();
                if (isDoor(field[i][j])) {
                    maxVar = Math.max(maxVar, field[i][j] % 100);
                }
            }
        }
        ++maxVar;
        boolean[][] wasDoor = new boolean[n][m];
        SAT3Solver solver = new SAT3Solver(maxVar);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (wasDoor[i][j]) continue;
                if (isDoor(field[i][j])) {
                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + dx[dir];
                        int y = j + dy[dir];
                        if (x < 0 || y < 0 || x >= n || y >= m || !isDoor(field[x][y])) {
                            continue;
                        }
                        for (int dir2 = 0; dir2 < 4; dir2++) {
                            if (dir == (dir2 + 2 & 3)) {
                                continue;
                            }
                            int xx = x + dx[dir2];
                            int yy = y + dy[dir2];
                            if (xx < 0 || yy < 0 || xx >= n || yy >= m || !isDoor(field[xx][yy])) {
                                continue;
                            }
                            int[] f = new int[]{getClause(field[i][j]), getClause(field[x][y]), getClause(field[xx][yy])};
                            solver.addClause(f);
                        }
                    }
                }
            }
        }
        Arrays.fill(solver.was, true);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] / 100 == 1 && field[i][j] % 100 < maxVar) {
                    solver.was[field[i][j] % 100] = false;
                }
                if (field[i][j] == 2) {
                    cx = i;
                    cy = j;
                }
                if (field[i][j] == 3) {
                    endx = i;
                    endy = j;
                }
            }
        }
        boolean[] ans = solver.solve();
        System.err.println(testNumber + " sat solved");
        ansS = new StringBuilder();
        for (int i = 0; i < maxVar; i++) {
            if (!ans[i]) {
//                System.out.println(i);
                all:
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < m; y++) {
                        if (field[x][y] == i + 100) {
                            goTo(field, x, y);
                            break all;
                        }
                    }
                }
                ansS.append('P');
            }
        }
        System.err.println(testNumber + " buttons pressed");
//        System.out.println(ansS);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isDoor(field[i][j])) {
                    if (!ans[field[i][j] % 100]) {
                        if (field[i][j] / 100 == 3) field[i][j] -= 100;
                        else field[i][j] += 100;
                    }
                }
            }
        }
        goTo(field, endx, endy);
        out.println(ansS);
    }

    static void goTo(int[][] field, int tox, int toy) {
        int n = field.length;
        int m = field[0].length;
        int[] q = new int[n * m];
        int[][] ldir = new int[n][m];
        boolean[][] was = new boolean[n][m];
        int head = 0;
        int tail = 1;
        q[0] = cx * m + cy;
        was[cx][cy] = true;
        while (head < tail) {
            int v = q[head++];
            int curx = v / m;
            int cury = v % m;
            for (int dir = 0; dir < 4; dir++) {
                int nx = curx + dx[dir];
                int ny = cury + dy[dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || field[nx][ny] == 0 || field[nx][ny] / 100 == 2 || was[nx][ny]) {
                    continue;
                }
                was[nx][ny] = true;
                ldir[nx][ny] = dir;
                q[tail++] = nx * m + ny;
            }
        }
        if (!was[tox][toy]) {
            throw new AssertionError();
        }
        StringBuilder add = new StringBuilder();
        int i = tox;
        int j = toy;
        while (i != cx || j != cy) {
            add.append(DIR.charAt(ldir[i][j]));
            int nx = i - dx[ldir[i][j]];
            int ny = j - dy[ldir[i][j]];
            i = nx;
            j = ny;
        }
        add.reverse();
        ansS.append(add.toString());
        cx = tox;
        cy = toy;
    }

    static StringBuilder ansS;
    static int cx;
    static int cy;
    static int endx, endy;
}
