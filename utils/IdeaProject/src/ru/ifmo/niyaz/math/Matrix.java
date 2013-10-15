package ru.ifmo.niyaz.math;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 24.01.12
 * Time: 1:18
 * To change this template use File | Settings | File Templates.
 */
public class Matrix {
    final int[][] a;
    public final int n;
    public final int m;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        a = new int[n][m];
    }

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix(int[][] a) {
        n = a.length;
        if (n == 0) {
            throw new AssertionError();
        }
        m = a[0].length;
        this.a = new int[n][];
        for (int i = 0; i < n; i++) {
            if (m != a[i].length) {
                throw new AssertionError();
            }
            this.a[i] = a[i].clone();
        }
    }

    public int get(int x, int y) {
        return a[x][y];
    }

    public static Matrix getOne(int n) {
        Matrix ret = new Matrix(n);
        for (int i = 0; i < n; i++) {
            ret.a[i][i] = 1;
        }
        return ret;
    }

    public Matrix multiplyMod(Matrix b, int mod) {
        if (m != b.n) {
            throw new AssertionError();
        }
        Matrix ret = new Matrix(n, b.m);
        long modmod = (long) mod * mod;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < b.m; j++) {
                long d = 0;
                for (int k = 0; k < m; k++) {
                    d = (d + (long) a[i][k] * b.a[k][j]);
                    if (d >= modmod) {
                        d -= modmod;
                    }
                }
                ret.a[i][j] = (int) (d % mod);
            }
        }
        return ret;
    }

    public static Matrix powMod(Matrix a, long b, int mod) {
        if (a.n != a.m) {
            throw new AssertionError();
        }
        Matrix ret = getOne(a.n);
        while (b > 0) {
            if ((b & 1) == 1) {
                ret = ret.multiplyMod(a, mod);
            }
            a = a.multiplyMod(a, mod);
            b >>= 1;
        }
        return ret;
    }

    public int[][] toArray() {
        int[][] ret = new int[n][];
        for (int i = 0; i < n; i++) {
            ret[i] = a[i].clone();
        }
        return ret;
    }

    public int determinantAbs(int mod) {
        int[][] b = a.clone();
        double[][] z = new double[b.length][b[0].length];
        for (int i = 0; i < b.length; i++) {
            b[i] = b[i].clone();
            for (int j = 0; j < b[i].length; j++) {
                z[i][j] = b[i][j];
                b[i][j] %= mod;
                if (b[i][j] < 0) {
                    b[i][j] += mod;
                }
            }
        }
        int ans = 1;
        int mul = 1;
        int n = b.length;
        double detDouble = 1.;
        for (int i = 0; i < n; i++) {
            if (z[i][i] == 0) {
                for (int j = i + 1; j < n; j++) {
                    if (z[j][i] != 0) {
                        double[] t = z[i];
                        z[i] = z[j];
                        z[j] = t;
                        detDouble = -detDouble;
                        break;
                    }
                }
            }
            detDouble *= Math.signum(z[i][i]);
            for (int j = i + 1; j < n; j++) {
                double div = z[j][i] / z[i][i];
                for (int k = i; k < n; k++) {
                    z[j][k] -= div * z[i][k];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (b[i][i] == 0) {
                for (int j = i + 1; j < n; j++) {
                    if (b[j][i] != 0) {
                        int[] t = b[i];
                        b[i] = b[j];
                        b[j] = t;
                        mul = -mul;
                        break;
                    }
                }
                if (b[i][i] == 0) {
                    return 0;
                }
            }
            for (int j = i + 1; j < n; j++) {
                while (b[j][i] != 0) {
                    {
                        int[] t = b[i];
                        b[i] = b[j];
                        b[j] = t;
                        mul = -mul;
                    }
                    int div = b[j][i] / b[i][i];
                    for (int k = i; k < n; k++) {
                        b[j][k] = (int) ((b[j][k] - (long) b[i][k] * div) % mod);
                        if (b[j][k] < 0) {
                            b[j][k] += mod;
                        }
                    }
                }
            }
            ans = (int) ((long) ans * b[i][i] % mod);
        }
        if (mul == -1) {
            ans = (mod - ans) % mod;
        }
        if (detDouble < 0) {
            ans = (mod - ans) % mod;
        }
        return ans;
    }

    public Matrix addMod(Matrix b, int mod) {
        if (n != b.n || m != b.m) {
            throw new AssertionError();
        }
        int[][] ret = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int s = a[i][j] + b.a[i][j];
                if (s >= mod) {
                    s -= mod;
                }
                ret[i][j] = s;
            }
        }
        return new Matrix(ret);
    }
}
