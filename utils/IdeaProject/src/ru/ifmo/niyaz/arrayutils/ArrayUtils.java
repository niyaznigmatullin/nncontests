package ru.ifmo.niyaz.arrayutils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.12
 * Time: 2:20
 * To change this template use File | Settings | File Templates.
 */
public class ArrayUtils {


    static final long seed = System.nanoTime();

    static final Random rand = new Random(seed);

    public static boolean nextPermutation(int[] a) {
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                int cur = i + 1;
                for (int j = i + 2; j < a.length; j++) {
                    if (a[j] > a[i] && a[j] < a[cur]) {
                        cur = j;
                    }
                }
                {
                    int t = a[cur];
                    a[cur] = a[i];
                    a[i] = t;
                }
                for (int j = i + 1, k = a.length - 1; j < k; j++, k--) {
                    int t = a[j];
                    a[j] = a[k];
                    a[k] = t;
                }
                return true;
            }
        }
        return false;
    }

    public static long countNumberOfInversions(int[] a) {
        return inversions(a, null, 0, a.length);
    }

    static long inversions(int[] a, int[] b, int left, int right) {
        if (b == null) {
            b = new int[a.length];
        }
        if (left + 1 >= right) {
            return 0;
        }
        int middle = left + right >> 1;
        long ret = inversions(a, b, left, middle) + inversions(a, b, middle, right);
        for (int i = left, j = middle, k = 0; i < middle || j < right; k++) {
            if (j >= right || i < middle && a[i] <= a[j]) {
                ret += j - middle;
                b[k] = a[i++];
            } else {
                b[k] = a[j++];
            }
        }
        for (int i = left; i < right; i++) {
            a[i] = b[i - left];
        }
        return ret;
    }

    public static long countNumberOfInversions(double[] a) {
        return inversions(a, null, 0, a.length);
    }

    static long inversions(double[] a, double[] b, int left, int right) {
        if (b == null) {
            b = new double[a.length];
        }
        if (left + 1 >= right) {
            return 0;
        }
        int middle = left + right >> 1;
        long ret = inversions(a, b, left, middle) + inversions(a, b, middle, right);
        for (int i = left, j = middle, k = 0; i < middle || j < right; k++) {
            if (j >= right || i < middle && a[i] <= a[j]) {
                ret += j - middle;
                b[k] = a[i++];
            } else {
                b[k] = a[j++];
            }
        }
        for (int i = left; i < right; i++) {
            a[i] = b[i - left];
        }
        return ret;
    }

    static public int[] toPrimitiveArrayInteger(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    static public long[] toPrimitiveArrayLong(List<Long> list) {
        long[] ret = new long[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    static public double[] toPrimitiveArraysDouble(List<Double> list) {
        double[] ret = new double[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    static public int[] sortAndUnique(int[] a) {
        int[] ret = a.clone();
        sort(ret);
        if (ret.length == 0) {
            return ret;
        }
        int last = ret[0];
        int j = 1;
        for (int i = 1; i < ret.length; i++) {
            if (last != ret[i]) {
                ret[j++] = ret[i];
                last = ret[i];
            }
        }
        return Arrays.copyOf(ret, j);
    }

    static public long[] sortAndUnique(long[] a) {
        long[] ret = a.clone();
        sort(ret);
        if (ret.length == 0) {
            return ret;
        }
        long last = ret[0];
        int j = 1;
        for (int i = 1; i < ret.length; i++) {
            if (last != ret[i]) {
                ret[j++] = ret[i];
                last = ret[i];
            }
        }
        return Arrays.copyOf(ret, j);
    }

    public static void sort(int[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = rand.nextInt(i + 1);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void sort(double[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static void shuffle(double[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = rand.nextInt(i + 1);
            double t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }


    public static void sort(long[] a) {
        shuffle(a);
        Arrays.sort(a);
    }

    public static void shuffle(long[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = rand.nextInt(i + 1);
            long t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static void shuffle(char[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = rand.nextInt(i + 1);
            char t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static <T> void shuffle(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = rand.nextInt(i + 1);
            T t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static int[] reversed(int[] a) {
        int n = a.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = a[n - i - 1];
        }
        return ret;
    }

    public static long[] reversed(long[] a) {
        int n = a.length;
        long[] ret = new long[n];
        for (int i = 0; i < n; i++) {
            ret[i] = a[n - i - 1];
        }
        return ret;
    }

    public static double[] reversed(double[] a) {
        int n = a.length;
        double[] ret = new double[n];
        for (int i = 0; i < n; i++) {
            ret[i] = a[n - i - 1];
        }
        return ret;
    }

    public static <T> T[] reversed(T[] a) {
        int n = a.length;
        T[] ret = a.clone();
        for (int i = 0; i < n; i++) {
            ret[i] = a[n - i - 1];
        }
        return ret;
    }

    public static <T> void reverse(T[] a, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            T t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    public static <T> void reverse(T[] a) {
        reverse(a, 0, a.length);
    }

    public static void reverse(int[] c, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            int t = c[i];
            c[i] = c[j];
            c[j] = t;
        }
    }

    public static void reverse(int[] c) {
        reverse(c, 0, c.length);
    }

    public static void reverse(long[] c, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            long t = c[i];
            c[i] = c[j];
            c[j] = t;
        }
    }

    public static void reverse(long[] c) {
        reverse(c, 0, c.length);
    }

    public static void reverse(double[] c, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            double t = c[i];
            c[i] = c[j];
            c[j] = t;
        }
    }

    public static void reverse(double[] c) {
        reverse(c, 0, c.length);
    }

    public static void reverse(char[] c, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            char t = c[i];
            c[i] = c[j];
            c[j] = t;
        }
    }

    public static void reverse(char[] c) {
        reverse(c, 0, c.length);
    }

    public static char[][] rotateCounterclockwise(char[][] c) {
        if (c.length == 0) return c;
        int n = c.length;
        int m = c[0].length;
        char[][] d = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[m - j - 1][i] = c[i][j];
            }
        }
        return d;
    }

    public static char[][] rotateClockwise(char[][] c) {
        if (c.length == 0) return c;
        int n = c.length;
        int m = c[0].length;
        char[][] d = new char[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[j][n - i - 1] = c[i][j];
            }
        }
        return d;
    }

    public static int[] merge(int[] a, int[] b) {
        if (a == null) return b;
        if (b == null) return a;
        int[] ret = new int[a.length + b.length];
        for (int i = 0, j = 0, k = 0; i + j < a.length + b.length; ) {
            if (j >= b.length || (i < a.length && a[i] <= b[j])) {
                ret[k++] = a[i++];
            } else {
                ret[k++] = b[j++];
            }
        }
        return ret;
    }

    public static int lowerBound(int[] a, int x) {
        int left = -1;
        int right = a.length;
        while (left < right - 1) {
            int mid = (left + right) >>> 1;
            if (a[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static int upperBound(int[] a, int x) {
        int left = -1;
        int right = a.length;
        while (left < right - 1) {
            int mid = (left + right) >>> 1;
            if (a[mid] <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

}
