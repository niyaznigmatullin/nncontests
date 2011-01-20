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
    private void solve() {
        while (true) {
            int m = nextInt();
            String s = nextString();
            if (m == 0) break;
            int n = s.length();
            BigInteger to_get = get_num(n, s).add(BigInteger.valueOf(m)).mod(BigInteger.valueOf(2).pow(n));
            out.println(get_gray(n, to_get));            
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