package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Decorate {

    int getSum(int[][] a, int x1, int y1, int x2, int y2) {
        return a[x2][y2] - a[x1][y2] - a[x2][y1] + a[x1][y1];
    }

    int[] compress(int[] a) {
        Arrays.sort(a);
        int sz = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[sz - 1]) {
                a[sz++] = a[i];
            }
        }
        return Arrays.copyOf(a, sz);
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();

        int[] x1 = new int[n];
        int[] x2 = new int[n];
        int[] y1 = new int[n];
        int[] y2 = new int[n];

        int[] allX = new int[2 + 2 * n];
        int[] allY = new int[2 + 2 * n];
        for (int i = 0; i < n; i++) {
            allX[2 * i] = x1[i] = in.nextInt();
            allY[2 * i] = y1[i] = in.nextInt();
            allX[2 * i + 1] = x2[i] = in.nextInt();
            allY[2 * i + 1] = y2[i] = in.nextInt();
        }
        allX[2 * n] = 0;
        allX[2 * n + 1] = w;
        allY[2 * n] = 0;
        allY[2 * n + 1] = h;

        allX = compress(allX);
        allY = compress(allY);

        int[][] occ = new int[allX.length][allY.length];

        for (int i = 0; i < n; i++) {
            x1[i] = Arrays.binarySearch(allX, x1[i]);
            y1[i] = Arrays.binarySearch(allY, y1[i]);
            x2[i] = Arrays.binarySearch(allX, x2[i]);
            y2[i] = Arrays.binarySearch(allY, y2[i]);

            for (int x = x1[i] + 1; x <= x2[i]; x++)
                for (int y = y1[i] + 1; y <= y2[i]; y++) {
                    occ[x][y] = 1;
                }
        }

        for (int i = 1; i < allX.length; i++)
            for (int j = 1; j < allY.length; j++)
                occ[i][j] += occ[i - 1][j] + occ[i][j - 1] - occ[i - 1][j - 1];

//        for (int i = 0; i < allX.length; i++)
//            System.out.println(Arrays.toString(occ[i]));

        int[] needX = new int[allX.length];
        int[] needY = new int[allY.length];

        int newW = in.nextInt();
        int newH = in.nextInt();

        for (int i = 0; i < allX.length; i++) {
            if (allX[i] + newW > w) {
                needX[i] = -1;
                continue;
            }
            int j = i;
            while (allX[j] - allX[i] < newW)
                j++;
            needX[i] = j;
        }
//        System.err.println(newW);
//        System.err.println(Arrays.toString(allX));
//        System.err.println(Arrays.toString(needX));

        for (int i = 0; i < allY.length; i++) {
            if (allY[i] + newH > h) {
                needY[i] = -1;
                continue;
            }
            int j = i;
            while (allY[j] - allY[i] < newH)
                j++;
            needY[i] = j;
        }
//        System.err.println(newH);
//        System.err.println(Arrays.toString(allY));
//        System.err.println(Arrays.toString(needY));

        for (int y = 0; needY[y] != -1; y++) {
            for (int x = 0; needX[x] != -1; x++) {
                if (getSum(occ, x, y, needX[x], needY[y]) == 0) {
                    out.println(allX[x] + " " + allY[y]);
                    return;
                }
            }
        }

        out.println("Fail!");
	}
}
