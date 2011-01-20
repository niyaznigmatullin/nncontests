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
    private static double EPS = 1e-9;
    private double heron(double a, double b, double c) {
        double p = (a + b + c) * .5;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    private double area_tr(double a, double b, double h) {
        return (a + b) * .5 * h;
    }
    private void solve() {
        int T = nextInt();
        for (int www = 1; www <= T; www++) {
            double x1 = nextDouble(), x2 = nextDouble(), y1 = nextDouble(), y2 = nextDouble();
            double h = 2 * heron(y1, y2, x1 - x2) / (x1 - x2);
            double l = 0, r = h, ar1 = 0, ar2 = 0;
            while (r - l > EPS) {
                double mid = (l + r) * .5, g = mid / h * (x1 - x2) + x2;
                ar1 = area_tr(x1, g, h - mid);
                ar2 = area_tr(x2, g, mid);
                if (ar1 < ar2) r = mid; else l = mid;
            }
            out.println("Land #" + www + ": " + (y1 - y1 * l / h) + " " + (y2 - y2 * l / h));
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