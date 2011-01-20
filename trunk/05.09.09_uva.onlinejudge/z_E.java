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
    private void solve() {
        while (true) {
            int x1 = nextInt(), ii = nextInt(), jj = nextInt();
            if (x1 == 0) break;
            String last = x1 + "";
            for (int i = 1; i < ii; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < last.length(); ) {
                    int k = j + 1;
                    while (k < last.length() && last.charAt(j) == last.charAt(k)) ++k;
                    sb.append((k - j));
                    sb.append(last.charAt(j));
                    j = k;
                    if (sb.length() > jj) break;
                }
                last = sb.toString();
            }
            out.println(last.charAt(jj - 1));
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