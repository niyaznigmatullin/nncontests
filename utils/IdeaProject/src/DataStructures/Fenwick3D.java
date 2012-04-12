package DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 18.01.12
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class Fenwick3D {
    long[][][] a;

    public Fenwick3D(int n, int m, int k) {
        a = new long[n][m][k];
    }

    public long getSum(int x, int y, int z) {
        long ret = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
                for (int k = z; k >= 0; k = (k & (k + 1)) - 1) {
                    ret += a[i][j][k];
                }
            }
        }
        return ret;
    }

    public long getSum(int x1, int y1, int z1, int x2, int y2, int z2) {
        return getSum(x2, y2, z2) - getSum(x1 - 1, y2, z2) - getSum(x2, y1 - 1, z2) -
                getSum(x2, y2, z1 - 1) + getSum(x2, y1 - 1, z1 - 1) + getSum(x1 - 1, y2, z1 - 1) +
                getSum(x1 - 1, y1 - 1, z2) - getSum(x1 - 1, y1 - 1, z1 - 1);
    }

    public void add(int x, int y, int z, long add) {
        for (int i = x; i < a.length; i |= i + 1) {
            for (int j = y; j < a[i].length; j |= j + 1) {
                for (int k = z; k < a[i][j].length; k |= k + 1) {
                    a[i][j][k] += add;
                }
            }
        }
    }

}
