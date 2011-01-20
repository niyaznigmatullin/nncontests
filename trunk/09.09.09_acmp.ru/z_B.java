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
    }
    class Vector {
        int x, y;
        Vector() { x = y = 0; }
        Vector(int xx, int yy) { x = xx; y = yy; }
        Vector(Point p1, Point p2) { x = p2.x - p1.x; y = p2.y - p1.y; }
        int mul(Vector v) { return x * v.y - v.x * y; }
    }
    private int sign(int x) {
        return (x == 0) ? 0 : (x < 0 ? -1 : 1);
    }
    private boolean intersects(Point p1, Point p2, Point s1, Point s2) {
        int mul1 = sign(new Vector(p1, s1).mul(new Vector(p1, s2))), mul2 = sign(new Vector(p2, s1).mul(new Vector(p2, s2))), mul3 = sign(new Vector(s1, p1).mul(new Vector(s1, p2))), mul4 = sign(new Vector(s2, p1).mul(new Vector(s2, p2)));
        if (mul1 == 0 && mul2 == 0 && mul3 == 0 && mul4 == 0) {
            return Math.min(Math.max(p1.x, p2.x), Math.max(s1.x, s2.x)) >= Math.max(Math.min(p1.x, p2.x), Math.min(s1.x, s2.x)) && Math.min(Math.max(p1.y, p2.y), Math.max(s1.y, s2.y)) >= Math.max(Math.min(p1.y, p2.y), Math.min(s1.y, s2.y));
        } else return mul1 != mul2 && mul3 != mul4;
    }
    private void solve() {
        Point p1 = new Point(nextInt(), nextInt()), p2 = new Point(nextInt(), nextInt()), p3 = new Point(nextInt(), nextInt()), p4 = new Point(nextInt(), nextInt());
        if (intersects(p1, p2, p3, p4)) {
            out.println("Yes");
        } else out.println("No");
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
