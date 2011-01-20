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
        double x, y;
        Point() { x = y = 0; }
        Point(double xx, double yy) { x = xx; y = yy; }
        Point(Point p) { x = p.x; y = p.y; }
        public boolean equals(Point p) {
            return Math.abs(x - p.x) < EPS && Math.abs(y - p.y) < EPS;
        }

    }
    double EPS = 1e-9;
    private boolean eq(double x1, double x2) {
        return Math.abs(x1 - x2) < EPS;
    }
    private Point go(Point p1, double p, double q) {
        Point pp = new Point(p1);
        if (eq(p, 0)) {
            if (q < 0) pp.y = 0; else pp.y = n;
            return pp;
        }
        if (eq(q, 0)) {
            if (p < 0) pp.x = 0; else pp.x = m;
            return pp;
        }
        double difx, dify;
        if (p < 0) difx = -pp.x / p; else difx = (m - pp.x) / p;
        if (q < 0) dify = -pp.y / q; else dify = (n - pp.y) / q;
        if (difx > dify) {
            pp.x += dify * p;
            pp.y += dify * q;
        } else {
            pp.x += difx * p;
            pp.y += difx * q;
        }
        return pp;
    }
    int n, m;
    private void solve() {
        while (true) {
            m = nextInt();
            n = nextInt();
            if (m == 0 && n == 0) break;
            double x1 = nextDouble(), y1 = nextDouble(), x2 = nextDouble(), y2 = nextDouble(), p = nextDouble(), q = nextDouble();
            Point p1 = new Point(x1, y1), p2 = new Point(x2, y2);
            Point g1 = go(p2, p, q), g2 = go(p2, -p, q), g3 = go(p2, -p, -q), g4 = go(p2, p, -q), cur = p1, to_go = go(p1, p, q);
            double pp = p, qq = q;
            boolean can = false;
            int www = 0;
            while (true) {
                cur = go(cur, pp, qq);
                if (can && to_go.equals(cur) && eq(p, pp) && eq(q, qq)) {
                    out.println("MISS");
                    break;
                }
                boolean ok = false;
                if (cur.equals(g1)) {
                    ok |= (eq(p, pp) && eq(q, qq));
                } else if (cur.equals(g2)) {
                    ok |= (eq(-p, pp) && eq(q, qq));
                } else if (cur.equals(g3)) {
                    ok |= (eq(-p, pp) && eq(-q, qq));
                } else if (cur.equals(g4)) {
                    ok |= (eq(p, pp) && eq(-q, qq));
                }
                if (ok) {
                    out.println("HIT");
                    break;
                }
                if (eq(cur.x, 0) || eq(cur.x, m)) pp = -pp;
                if (eq(cur.y, 0) || eq(cur.y, n)) qq = -qq;
                can = true;
                www++;
                if (www == 1000000) {
                    out.println("HIT");
                    break;
                }
            }
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