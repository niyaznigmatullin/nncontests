package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskD {

    static final int DELTA = 50;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        long n = 1;
        int[] p = new int[k];
        int[] d = new int[k];
        int allDivisors = 1;
        for (int i = 0; i < k; i++) {
            p[i] = in.nextInt();
            d[i] = in.nextInt();
            for (int j = 0; j < d[i]; j++) {
                n *= p[i];
            }
            allDivisors *= d[i] + 1;
        }
        divs = new long[allDivisors];
        countDivs = 0;
        getAllDivs(0, p, d, 1);
        Arrays.sort(divs);
        int med = 0;
        while (med < allDivisors && divs[med] * divs[med] <= n / divs[med]) {
            ++med;
        }
        for (int i = Math.max(0, med - DELTA); i <= med + 1 && i < allDivisors; i++) {
            long z = n / divs[i];
            int id = Arrays.binarySearch(divs, z);

        }
	}

    static void getAllDivs(int cur, int[] p, int[] d, long div) {
        if (cur == p.length) {
            divs[countDivs++] = div;
            return;
        }
        for (int i = 0; i <= d[cur]; i++, div *= p[cur]) {
            getAllDivs(cur + 1, p, d, div);
        }
    }

    static long[] divs;
    static int countDivs;
}
