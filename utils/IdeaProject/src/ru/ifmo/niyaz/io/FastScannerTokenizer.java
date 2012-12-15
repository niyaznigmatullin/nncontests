package ru.ifmo.niyaz.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 05.12.11
 * Time: 1:38
 * To change this template use File | Settings | File Templates.
 */
public class FastScannerTokenizer {
    BufferedReader br;
    StringTokenizer st;
    IOException happened;

    public FastScannerTokenizer(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public boolean hasNext() {
        while (st == null || !st.hasMoreTokens()) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                happened = e;
                return false;
            }
            if (line == null) {
                return false;
            }
            st = new StringTokenizer(line);
        }
        return true;
    }

//    public String next() {
//        if (!hasNext()) {
//            return null;
//        }
//        return st.nextToken();
//    }

    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                String line = br.readLine();
                st = new StringTokenizer(line);
            } catch (IOException e) {
                happened = e;
                return null;
            }
        }
        return st.nextToken();
    }

    public String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                String line = br.readLine();
                if (line == null) {
                    return null;
                }
                st = new StringTokenizer(line);
            } catch (IOException e) {
                happened = e;
                return null;
            }
        }
        return st.nextToken();
    }

    public int indianNextInt() {
        String e = nextToken();
        if (e == null) {
            return 0;
        }
        return Integer.parseInt(e);

    }

    public int fastNextInt() {
        try {
            int c = br.read();
            while (c <= 32 && c >= 0) {
                c = br.read();
            }
            if (c == -1) {
                throw new NoSuchElementException();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = br.read();
            }
            if (c < '0' || c > '9') {
                throw new NumberFormatException("digit expected " + (char) c
                        + " found");
            }
            int ret = 0;
            while (c >= '0' && c <= '9') {
                ret = ret * 10 + c - '0';
                c = br.read();
            }
            if (c > 32) {
                throw new NumberFormatException("space character expected "
                        + (char) c + " found");
            }
            return ret * sgn;
        } catch (IOException e) {
            return Integer.MIN_VALUE;
        }
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        try {
            if (st != null && st.hasMoreTokens()) {
                StringBuilder ret = new StringBuilder();
                while (st.hasMoreElements()) {
                    ret.append(st.nextElement());
                }
                return ret.toString();
            } else {
                String line = br.readLine();
                return line;
            }
        } catch (IOException e) {
            happened = e;
            return null;
        }
    }

    public void skipLine() {
        if (st == null || !st.hasMoreElements()) {
            try {
                br.readLine();
            } catch (IOException e) {
                happened = e;
                return;
            }
        } else {
            st = null;
        }
    }

    public int readTimeHM(char delim) {
        String s = next();
        int pos = s.indexOf(delim);
        if (pos < 0) {
            throw new NumberFormatException("no delim found");
        }
        return Integer.parseInt(s.substring(0, pos)) * 60 + Integer.parseInt(s.substring(pos + 1));
    }

    public int readTimeHMS(char delim1, char delim2) {
        String s = next();
        int pos = s.indexOf(delim1);
        if (pos < 0) {
            throw new NumberFormatException("no delim found");
        }
        int pos2 = s.indexOf(delim2, pos + 1);
        if (pos2 < 0) {
            throw new NumberFormatException("no second delim found");
        }
        return Integer.parseInt(s.substring(0, pos)) * 3600 + Integer.parseInt(s.substring(pos + 1, pos2)) * 60
                + Integer.parseInt(s.substring(pos2 + 1));
    }

    public int readTimeHMS(char delim) {
        return readTimeHMS(delim, delim);
    }
}
