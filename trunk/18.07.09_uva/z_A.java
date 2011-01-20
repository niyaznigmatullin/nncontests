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
    int[][] p;
    private int gcd(int x, int y) {
        while (x != 0) {
            int p = y % x;
            y = x;
            x = p;
        }
        return y;
    }
    private void solve() {
        int n, m;
        while (true) {
            n = nextInt();
            m = nextInt();
            if (n == 0 || m == 0) break;
            p = new int[n][m];
            int round = -1;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    p[i][j] = nextInt();
                    if (p[i][j] != 0) {
                        round = Math.max(round, j);
                    }
                }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += p[i][round];
            }
            for (int i = 0; i < n; i++) {
                int x = p[i][round], y = sum;
                if (x == 0) out.println("0 / 1"); else {
                    int g = gcd(x, y);
                    x /= g;
                    y /= g;
                    out.println(x + " / " + y);
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