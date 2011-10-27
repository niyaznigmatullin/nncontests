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
    class Point {
        double x, y;
        Point() { x = y = 0; }
        Point(double xx, double yy) { x = xx; y = yy; }
    }
    String INFILE = "robot.i", OUTFILE = "robot.o";
    double sqr(double x) { return x * x; }
    Point get(int i, int j, int k) {
        Point ret = new Point();
        ret.x=(sqr(r[i] + r[j]) + sqr(r[i] + r[k]) - sqr(r[j] + r[k])) / (2.0 * (r[i] + r[j]));
	    ret.y=Math.sqrt(sqr(r[i] + r[k]) - ret.x * ret.x);
        return ret;
    }
    int[] r;
    private void solve() {
        int n = nextInt();
        r = new int[n];
        for (int i = 0; i < n; i++) r[i] = nextInt();
        Arrays.sort(r);
        int ans = 0;
        for (int i = n - 1; i >= 3; i--)
            for (int j = i - 1; j >= 2; j--)
                for (int k = 0; k < j - 1; k++) {
                    int low = k + 1, high = j;
                    Point p1 = get(i,j,k);
                    while (low < high) {
                        int mid = ((low + high) >> 1);
                        Point p2 = get(i,j,mid);
                        if (sqr(p1.x - p2.x) + sqr(p1.y - p2.y) + 1e-9 >= sqr(r[mid] + r[k])) high = mid; else low = mid + 1;
                    }
                    ans += j - low;
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
