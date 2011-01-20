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
    private BigInteger get_num(int n, String s) {
        if (n == 1) return BigInteger.valueOf((int)s.charAt(0) - '0');
        char c = s.charAt(0);
        BigInteger mid = BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE);
        if (c == '0') return get_num(n - 1, s.substring(1)); else return mid.subtract(get_num(n - 1, s.substring(1)));
    }
    private String get_gray(int n, BigInteger x) {
        if (n == 1) return x + "";
        BigInteger mid = BigInteger.valueOf(2).pow(n - 1);
        if (x.compareTo(mid) != -1) {
            return "1" + get_gray(n - 1, mid.add(mid).subtract(BigInteger.ONE).subtract(x));
        } else {
            return "0" + get_gray(n - 1, x);
        }
    }
    class Point {
        int x, y;
        Point() { x = y = 0; }
        Point(int xx, int yy) { x = xx; y = yy; }
    }
    class Line {
        double a, b, c ;
        Line() { a = b = c = 0; }
        Line(double aa, double bb, double cc) { a = aa; b = bb; c = cc; }
        Line(Point p1, Point p2) {
            a = p2.y - p1.y;
            b = p1.x - p2.x;
            c = -(p1.x * a + p1.y * b);
        }
    }
    private int sign(int x) {
        if (x > 0) return 1; else if (x < 0) return -1; else return 0;
    }
    private boolean intersects(Point p1, Point p2, Point s1, Point s2) {
        int q1 = sign((p1.x - s2.x) * (p1.y - s1.y) - (p1.y - s2.y) * (p1.x - s1.x)), q2 = sign((p2.x - s2.x) * (p2.y - s1.y) - (p2.y - s2.y) * (p2.x - s1.x)), q3 = sign((s1.x - p2.x) * (s1.y - p1.y) - (s1.y - p2.y) * (s1.x - p1.x)), q4 = sign((s2.x - p2.x) * (s2.y - p1.y) - (s2.y - p2.y) * (s2.x - p1.x));
        if (q1 == 0 || q2 == 0 || q3 == 0 || q4 == 0) {
            return false;
        }
        return (q1 != q2 && q3 != q4);
    }
    private int vect(Point p1, Point p2, Point p3) {
        return Math.abs((p1.x - p2.x) * (p1.y - p3.y) - (p1.x - p3.x) * (p1.y - p2.y)); 
    }
    private boolean contains(Point[] p, Point p1) {
        double area = 0, area2 = 0;
        for (int i = 0; i < p.length; i++) area += p[i].x * p[(i + 1) % p.length].y - p[i].y * p[(i + 1) % p.length].x;
        area = Math.abs(area) * .5;
        for (int i = 0; i < p.length; i++) area2 += .5 * vect(p1, p[i], p[(i + 1) % p.length]);
        return Math.abs(area - area2) < 1e-9;
    }
    private boolean can(Point[] p1, Point[] p2) {
        for (int i = 0; i < p1.length; i++)
            for (int j = 0; j < p2.length; j++) if (intersects(p1[i], p1[(i + 1) % p1.length], p2[j], p2[(j + 1) % p2.length])) return true;
        for (int i = 0; i < p1.length; i++) if (contains(p2, p1[i])) return true;
        for (int i = 0; i < p2.length; i++) if (contains(p1, p2[i])) return true;
        return false;
    }
    private void dfs(int x) {
        was[x] = true;
        for (int i = 0; i < n; i++) if (!was[i] && G[x][i]) dfs(i);
    }
    int n;
    boolean[] was;
    boolean[][] G;
    private void solve() {
        while (true) {
            n = nextInt();
            if (n == 0) break;
            Point[][] P = new Point[n][];
            for (int i = 0; i < n; i++) {
                ArrayList<Point> R = new ArrayList<Point>();
                StringTokenizer ss = new StringTokenizer(nextLine());
                while (ss.hasMoreTokens()) {
                    R.add(new Point(Integer.parseInt(ss.nextToken()), Integer.parseInt(ss.nextToken())));
                }
                P[i] = new Point[R.size()];
                for (int j = 0; j < R.size(); j++) P[i][j] = R.get(j);
            }
            G = new boolean[n][n];
            was = new boolean[n];
            Arrays.fill(was, false);
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    G[i][j] = G[j][i] = can(P[i], P[j]);
            int ans = 0;
            for (int i = 0; i < n; i++) if (!was[i]) {
                dfs(i);
                ans++;
            }
            out.println(ans);
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