package ru.ifmo.niyaz.io;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 05.12.11
 * Time: 1:38
 * To change this template use File | Settings | File Templates.
 */
public class FastScanner extends BufferedReader {

    boolean isEOF;

    public FastScanner(InputStream is) {
        super(new InputStreamReader(is));
    }

    @Override
    public int read() {
        try {
            int ret = super.read();
            if (isEOF && ret < 0) {
                throw new InputMismatchException();
            }
            isEOF = ret == -1;
            return ret;
        } catch (IOException e) {
            throw new InputMismatchException();
        }
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        int c = read();
        while (isWhiteSpace(c)) {
            c = read();
        }
        while (!isWhiteSpace(c)) {
            sb.appendCodePoint(c);
            c = read();
        }
        return sb.toString();
    }

    static boolean isWhiteSpace(int c) {
        return c >= -1 && c <= 32;
    }

    public String nextToken() {
        return next();
    }

    public int nextInt() {
        int c = read();
        while (isWhiteSpace(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int ret = 0;
        while (!isWhiteSpace(c)) {
            if (c < '0' || c > '9') {
                throw new NumberFormatException("digit expected " + (char) c
                        + " found");
            }
            ret = ret * 10 + c - '0';
            c = read();
        }
        return ret * sgn;
    }

    public char nextChar() {
        int c = read();
        while (isWhiteSpace(c)) {
            c = read();
        }
        return (char) c;
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        try {
            int c = read();
            String ret = readLine();
            if (c != 13) {
                return (char) c + ret;
            }
            return ret;
        } catch (IOException e) {
            return null;
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

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextInt();
        }
        return ret;
    }
    public long[] readLongArray(int n) {
        long[] ret = new long[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextLong();
        }
        return ret;
    }
    public double[] readDoubleArray(int n) {
        double[] ret = new double[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextDouble();
        }
        return ret;
    }

    public String[] readTokenArray(int n) {
        String[] ret = new String[n];
        for (int i = 0; i < n; i++) {
            ret[i] = next();
        }
        return ret;
    }

    public char[][] readCharacterFieldTokens(int n, int m) {
        char[][] ret = new char[n][];
        for (int i = 0; i < n; i++) {
            ret[i] = next().toCharArray();
            if (ret[i].length != m) {
                throw new AssertionError("length expected " + m + ", found " + ret[i].length);
            }
        }
        return ret;
    }

}
