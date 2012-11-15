package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.jvm.hotspot.debugger.dbx.x86.DbxX86Thread;

import java.util.*;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int[] posX = new int[26];
        int[] posY = new int[26];
        Arrays.fill(posX, -1);
        Arrays.fill(posY, -1);
        int[][][] d = new int[26][n][m];
        Point[][][] from = new Point[26][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] >= 'a' && c[i][j] <= 'z') {
                    int letter = c[i][j] - 'a';
                    posX[letter] = i;
                    posY[letter] = j;
                }
            }
        }
        for (int letter = 0; letter < 26; letter++) {
            if (posX[letter] < 0) {
                continue;
            }
            final int[][] curD = d[letter];
            for (int[] z : curD) {
                Arrays.fill(z, Integer.MAX_VALUE);
            }
            NavigableSet<Point> q = new TreeSet<Point>(new Comparator<Point>() {
                public int compare(Point o1, Point o2) {
                    if (curD[o1.x][o1.y] != curD[o2.x][o2.y]) {
                        return curD[o1.x][o1.y] - curD[o2.x][o2.y];
                    }
                    if (o1.x != o2.x) {
                        return o1.x - o2.x;
                    }
                    return o1.y - o2.y;
                }
            });
            curD[posX[letter]][posY[letter]] = 0;
            q.add(new Point(posX[letter], posY[letter]));
            while (!q.isEmpty()) {
                Point p = q.pollFirst();
                for (int dir = 0; dir < 4; dir++) {
                    int x = p.x + DX[dir];
                    int y = p.y + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '#') {
                        continue;
                    }
                    if (curD[x][y] > curD[p.x][p.y] + d(c[p.x][p.y])) {
                        Point to = new Point(x, y);
                        q.remove(to);
                        curD[x][y] = curD[p.x][p.y] + d(c[p.x][p.y]);
                        from[letter][x][y] = p;
                        q.add(to);
                    }
                }
            }
        }
        int x1 = in.nextInt() - 1;
        int y1 = in.nextInt() - 1;
        String path = in.next();
        int x2 = in.nextInt() - 1;
        int y2 = in.nextInt() - 1;
        int firstPath = d[path.charAt(0) - 'a'][x1][y1] - 1 + d(c[x1][y1]);
        if (firstPath > k) {
            Point cur = new Point(x1, y1);
            int letter = path.charAt(0) - 'a';
            int pathLength = 0;
            Point answer = null;
            while (c[cur.x][cur.y] != path.charAt(0)) {
                pathLength += d(c[cur.x][cur.y]);
                if (pathLength > k) {
                    answer = cur;
                    break;
                }
                cur = from[letter][cur.x][cur.y];
            }
            out.println(answer.x + 1 + " " + (answer.y + 1));
            return;
        }
        k -= firstPath;
        for (int i = 1; i < path.length(); i++) {
            int letter2 = path.charAt(i) - 'a';
            int letter1 = path.charAt(i - 1) - 'a';
            int lastX = posX[letter2];
            int lastY = posY[letter2];
            int pathLength = d[letter1][lastX][lastY];
            if (pathLength > k) {
                while (d[letter1][lastX][lastY] > k) {
                    Point z = from[letter1][lastX][lastY];
                    lastX = z.x;
                    lastY = z.y;
                }
                out.println(lastX + 1 + " " + (lastY + 1));
                return;
            }
            k -= pathLength;
        }
        int lastPath = d[path.charAt(path.length() - 1) - 'a'][x2][y2];
        if (lastPath > k) {
            int letter1 = path.charAt(path.length() - 1) - 'a';
            int lastX = x2;
            int lastY = y2;
            while (d[letter1][lastX][lastY] > k) {
                Point z = from[letter1][lastX][lastY];
                lastX = z.x;
                lastY = z.y;
            }
            out.println(lastX + 1 + " " + (lastY + 1));
            return;
        }
        out.println(x2 + 1 + " " + (y2 + 1));
    }

    static int d(char c) {
        return c >= '0' && c <= '9' ? c - '0' : 1;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};
}
