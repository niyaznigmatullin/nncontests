package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        final int BLOCK = 666;
        int[] countNonZero = new int[(n - 1) / BLOCK + 1];
        int[] block = new int[n];
        for (int i = 0; i < n; i++) {
            block[i] = i / BLOCK;
            if (a[i] > 0) {
                countNonZero[block[i]]++;
            }
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            if (x < 0) {
                int curBlock = -1;
                for (int j = 0; j < countNonZero.length; j++) {
                    if (countNonZero[j] > 0) {
                        curBlock = j;
                        break;
                    }
                }
                int start = BLOCK * curBlock;
                while (start < n && block[start] == curBlock) {
                    if (a[start] > 0) {
                        --a[start];
                        out.print(start + 1 + " ");
                        if (a[start] == 0) {
                            --countNonZero[curBlock];
                        }
                        break;
                    }
                    ++start;
                }
            } else {
                a[x]++;
                if (a[x] == 1) {
                    countNonZero[block[x]]++;
                }
            }
        }
    }
}
