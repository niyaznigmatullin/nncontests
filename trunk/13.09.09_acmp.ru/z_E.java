import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.math.*;
import java.io.*;

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
    StringTokenizer st;
    PrintWriter out;
    BufferedReader br;
    boolean eof = false, in_out = false;
    private String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return "0";
            }
        }
        return st.nextToken();
    }
    private String nextLine() {
        String ret = "";
        try {
            ret = br.readLine();
        } catch (Exception e) {
            ret = "";
        }
        if (ret == null) {
            eof = true;
            return "$";
        }
        return ret;
    }
    private String nextString() {
        return nextToken();
    }
    private int nextInt() {
        return Integer.parseInt(nextToken());
    }
    private long nextLong() {
        return Long.parseLong(nextToken());
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
        ret += "." + Long.toString((long)Math.floor(x));
        if ((long)Math.floor(x) == 0) {
            ret += "0";
        }
        return ret;
    }
    private static double EPS = 1e-9;
    String INFILE = "bear.in", OUTFILE = "bear.out";
    class Point {
        int x, y;
        Point() { x = y = 0; }
        Point(int xx, int yy) { x = xx; y = yy; }
        int dist2(Point p) {
            return (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y);
        }
        double dist(Point p) {
            return Math.sqrt(dist2(p));
        }
    }
    int tr_area(Point p1, Point p2, Point p3) {
        return Math.abs((p1.x - p2.x) * (p1.y - p3.y) - (p1.y - p2.y) * (p1.x - p3.x));
    }
    boolean contains(Point p1, Point p2, Point p3, Point p) {
        int ar1 = tr_area(p1, p2, p), ar2 = tr_area(p1, p3, p), ar3 = tr_area(p2, p3, p);
        if (ar1 == 0 || ar2 == 0 || ar3 == 0) return true;
        if (tr_area(p1, p2, p3) == ar1 + ar2 + ar3) {
            return false;
        } else return true;
    }
    private void solve() {
        int n = nextInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point(nextInt(), nextInt());
        }
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++) {
                    boolean ok = true;
                    for (int t = 0; t < n; t++) {
                        if (!contains(p[i], p[j], p[k], p[t])) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) ans++;                    
                }
        out.println(ans);
    }
    public void run() {
        try {
            br = new BufferedReader(new FileReader(new File((in_out) ?  INFILE  : "input.txt")));
            out = new PrintWriter(new File((in_out) ? OUTFILE : "output.txt"));
            //br = new BufferedReader(new FileReader(new File("input.txt")));
            //out = new PrintWriter(new File("output.txt"));
        } catch (Exception e) {
            System.exit(111);
        }
        solve();
        out.close();
    }
}
