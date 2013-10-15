package lib.test.on2013_03.on2013_03_31_2013_TopCoder_Open_Algorithm.TheMagicMatrix;



import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TheMagicMatrix {

    static final int MOD = 1234567891;

    public int find(int n, int[] rows, int[] columns, int[] values) {
        int[] row = new int[n];
        int[] col = new int[n];
        for (int i = 0; i < rows.length; i++) {
            row[rows[i]]++;
            col[columns[i]]++;
        }
        boolean hasCol = false;
        boolean hasRow = false;
        for (int i = 0; i < n; i++) {
            if (0 == col[i]) hasCol = true;
            if (0 == row[i]) hasRow = true;
        }
        if (hasCol && hasRow) {
            return MathUtils.modPow(10, n * n - 2 * (n - 1) - rows.length, MOD);
        }
        int[][] a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, -1);
        }
        for (int i = 0; i < rows.length; i++) {
            a[rows[i]][columns[i]] = values[i];
        }
        int[][] id = new int[n][n];
        int[] sr = new int[n];
        int[] sc = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] < 0) {
                    id[i][j] = cnt++;
                } else {
                    sr[i] = (sr[i] + a[i][j]) % 10;
                    sc[j] = (sc[j] + a[i][j]) % 10;
                    id[i][j] = -1;
                }
            }
        }
        long ans = 0;
        for (int d = 0; d < 10; d++) {
            int[][] matrix = new int[n + n][cnt + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (id[i][j] >= 0) {
                        matrix[i][id[i][j]] = 1;
                        matrix[j + n][id[i][j]] = 1;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                matrix[i][cnt] = (d + 10 - sr[i]) % 10;
                matrix[i + n][cnt] = (d + 10 - sc[i]) % 10;
            }
            ans = (ans + getAnswer(matrix)) % MOD;
        }
        return (int) ans;
    }

    static int getAnswer(int[][] a) {
        int n = a.length;
        int m = a[0].length - 1;
        int row = 0;
        for (int i = 0; row < n && i < m; i++) {
            if (a[row][i] == 0) {
                for (int j = row + 1; j < n; j++) {
                    if (a[j][i] != 0) {
                        int[] t = a[row];
                        a[row] = a[j];
                        a[j] = t;
                        break;
                    }
                }
                if (a[row][i] == 0) {
                    continue;
                }
            }
            for (int j = row + 1; j < n; j++) {
                while (a[j][i] != 0) {
                    int[] t = a[j];
                    a[j] = a[row];
                    a[row] = t;
                    int q = a[j][i] / a[row][i];
                    for (int k = 0; k <= m; k++) {
                        a[j][k] = (a[j][k] - q * a[row][k] + 100) % 10;
                    }
                }
            }
            row++;
        }
        for (int i = 0; i < n; i++) {
            boolean isZero = true;
            for (int j = 0; j < m; j++) {
                if (a[i][j] != 0) {
                    isZero = false;
                    break;
                }
            }
            if (isZero && a[i][m] != 0) {
                return 0;
            }
        }
        return MathUtils.modPow(10, m - row, MOD);
    }
}
