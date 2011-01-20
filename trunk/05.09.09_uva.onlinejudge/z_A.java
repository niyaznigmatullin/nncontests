import java.util.*;
import java.math.*;
import java.io.*;

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
    StringTokenizer st;
    PrintWriter out;
    BufferedReader br;
    String chars = "";
    boolean eof = false;
    private String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return "";
            }
        }
        return st.nextToken();
    }
    private String nextLine() {
        String ret = "";
        try {
            ret = br.readLine();
        } catch (Exception e) {
            ret = "$";
            eof = true;
        }
        if (ret == null) {
            eof = true;
            return "";
        }
        return ret;
    }
    private String nextString() {
        return nextToken();
    }
    private long nextLong() {
        return Long.parseLong(nextToken());
    }
    private int nextInt() {
        return Integer.parseInt(nextToken());
    }
    private double nextDouble() {
        return Double.parseDouble(nextToken());
    }
    private BigInteger nextBigInteger() {
        return new BigInteger(nextToken());
    }
    private String to_precision(double x, int kol) {
        if (kol == 0) {
            return Long.toString((long)Math.floor(x));
        }
        String ret = Long.toString((long)Math.floor(x));
        x -= Math.floor(x);
        for (int i=0; i<kol; i++) {
            x *= 10;
        }
        ret += ".";
        String ss = Long.toString((long)Math.floor(x));
        for (int i = 0; i < kol - ss.length(); i++) ret += "0";
        ret += ss;
        return ret;
    }
    class Point{
        int x, y;
        Point() { x = y = 0; }
        Point(int xx, int yy) { x = xx; y = yy; }
    }
    double EPS = 1e-6;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    private void solve() {
        while (true) {
            int n = nextInt(), m = nextInt();
            if (n == 0 && m == 0) break;
            int[] xx = new int[n + 1], yy = new int[n + 1];
            for (int i = 0; i < n; i++) {
                xx[i] = nextInt();
                yy[i] = nextInt();
            }
            xx[n] = xx[0];
            yy[n] = yy[0];
            Point[] que = new Point[1001 * 1001];
            int[][] dd = new int [1001][1001];
            int head = 0, tail = 0;
            boolean[][] was = new boolean[1001][1001];
            for (int i = 0; i <= 1000; i++) Arrays.fill(was[i], false);
            for (int i = 0; i < n; i++) {
                for (int x = Math.min(xx[i], xx[i + 1]); x <= Math.max(xx[i], xx[i + 1]); ++x)
                    for(int y = Math.min(yy[i], yy[i + 1]); y <= Math.max(yy[i], yy[i + 1]); ++y) {
                        que[tail++] = new Point(x, y);
                        was[x][y] = true;
                        dd[x][y] = 0;
                    }
            }
            while (head < tail) {
                int x = que[head].x, y = que[head].y, z = dd[x][y];
                head++;
                if (z == m) continue;
                for (int dir = 0; dir < 4; dir++) {
                    int x0 = x + dx[dir], y0 = y + dy[dir];
                    if (x0 < 0 || y0 < 0 || x0 > 1000 || y0 > 1000 || was[x0][y0]) continue;
                    que[tail++] = new Point(x0, y0);
                    dd[x0][y0] = z + 1;
                    was[x0][y0] = true;
                }
            }
            head = 0;
            tail = 0;
            que[tail++] = new Point(nextInt(), nextInt());
            while (head < tail) {
                int x = que[head].x, y = que[head].y;
                head++;
                for (int dir = 0; dir < 4; dir++) {
                    int x0 = x + dx[dir], y0 = y + dy[dir];
                    if (x0 < 0 || y0 < 0 || x0 > 1000 || y0 > 1000 || was[x0][y0]) continue;
                    que[tail++] = new Point(x0, y0);
                    was[x0][y0] = true;
                }
            }
            if (was[nextInt()][nextInt()]) out.println("Yes"); else out.println("No");
        }
    }
    public void run() {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        solve();
        try {

        } catch (Exception e) {
            System.exit(111);
        }
        out.flush();
    }
}