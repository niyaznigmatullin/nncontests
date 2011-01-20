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
    double EPS = 1e-6;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    private void solve() {
        while (true) {
            int n = nextInt();
            if (n == 0) break;
            BigInteger c = nextBigInteger();
            int x = nextInt() - 1, y = nextInt() - 1;
            int[][] col = new int[n][n];
            for (int i = n - 1; i >= 0; --i)
                for (int j = n - 1; j >= 0; --j) {
                    col[i][j] = c.mod(BigInteger.valueOf(2)).intValue();
                    c = c.divide(BigInteger.valueOf(2));
                }
            int dir = 0;
            if (x == n - 1 && y == n - 1) {
                out.println("Yes");
                continue;
            }
            while (true) {
                if (x < 0 || y < 0 || x >= n || y >= n) {
                    out.println("Kaputt!");
                    break;
                }
                if (col[x][y] == 1) {
                    dir = (dir + 1) % 4;
                } else dir = (dir + 3) % 4;
                col[x][y] ^= 1;
                x += dx[dir];
                y += dy[dir];
                if (x == n - 1 && y == n - 1) {
                    out.println("Yes");
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