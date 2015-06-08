import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class FIPSC {

    class Pair {
        int f, s;

        public int hashCode() {
            return f * 31 + s;
        }

        public Pair(int f, int s) {
            this.f = f;
            this.s = s;
        }

        public boolean equals(Object o) {
            Pair p = (Pair) o;
            return f == p.f && s == p.s;
        }
    }

    Map<Pair, BigInteger> map = new HashMap<>();

    BigInteger get(int n, int y) {
        if (n - y * Math.PI < 4) return BigInteger.ONE;
        Pair key = new Pair(n, y);
        BigInteger now = map.get(key);
        if (now != null) return now;
        BigInteger ans = get(n - 1, y).add(get(n, y + 1));
        map.put(key, ans);
        return ans;
    }

    void solve() throws IOException {
        for (int i = 0; i < 23_500; ++i) {
            for (int j = 0; j < i / 3; ++j) {
                get(i, j);
            }
            if (i % 1 == 0) err.println(i);
        }
        err.println("OK");
        int n = in.nextInt();
        while (n != -1) {
            print(get(n, 0).toString().toCharArray());
            n = in.nextInt();
        }
    }

    void solve1() throws IOException {
        BigDecimal[] p = new BigDecimal[40_000];
        BigInteger[] res = new BigInteger[p.length];
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.ZERO;
        final BigDecimal pi = new BigDecimal(Math.PI);
        final BigDecimal four = new BigDecimal(4);
        for (int i = 0; i < p.length; ++i) {
            if (a.compareTo(b) < 0) {
                p[i] = a;
                a = a.add(BigDecimal.ONE);
            } else {
                p[i] = b;
                b = b.add(pi);
            }
            BigDecimal x = p[i];
            if (x.compareTo(four) < 0) {
                res[i] = BigInteger.ONE;
            } else {
                int j = i;
                x = p[i].subtract(BigDecimal.ONE);
                while (p[j].compareTo(x) >= 0) {
                    j--;
                }
                j++;
                int k = i;
                x = p[i].subtract(pi);
                while (p[k].compareTo(x) >= 0) {
                    k--;
                }
                k++;
//                err.println(i + " " + j + " " + k);
                res[i] = res[j].add(res[k]);
            }
        }
        int n = in.nextInt();
        while (n != -1) {
            int i = 0;
            BigDecimal now = new BigDecimal(n);
            while (p[i].compareTo(now) < 0) {
                i++;
            }
            print(res[i].toString().toCharArray());
            n = in.nextInt();
        }
    }

    void print(char[] a) {
        for (int i = 0; i < a.length; i += 50) {
            out.println(new String(a, i, Math.min(i + 50, a.length)));
        }
    }

    static FIPSCReader in;
    static PrintWriter out;
    static PrintStream err;

    public static void main(String[] args) {
        try {
            in = new FIPSCReader();
            out = new PrintWriter(System.out);
            err = System.err;
            new FIPSC().solve();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

class FIPSCReader extends BufferedReader {
    StringTokenizer st;

    FIPSCReader(InputStream is) {
        super(new InputStreamReader(is));
    }

    FIPSCReader() {
        this(System.in);
    }

    FIPSCReader(String file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    String nextToken() throws IOException {
        while (st == null || !st.hasMoreElements()) {
            st = new StringTokenizer(readLine());
        }
        return st.nextToken();
    }

    public boolean hasMoreTokens() throws IOException {
        if (st != null && st.hasMoreTokens()) {
            return true;
        }
        String line = readLine();
        if (line == null) {
            return false;
        }
        st = new StringTokenizer(line);
        return true;
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }
}