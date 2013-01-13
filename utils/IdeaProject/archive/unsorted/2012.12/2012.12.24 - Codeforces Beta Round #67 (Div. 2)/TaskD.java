package coding;

import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] b = new int[n][];
        for (int i = 0; i < n; i++) {
            b[i] = new int[in.nextInt()];
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = in.nextInt();
            }
        }
        int[] minimal = new int[n];
        long[] maximal = new long[n];
        long[] maxSum = new long[n];
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            int summ = 0;
            maxSum[i] = Long.MIN_VALUE;
            maximal[i] = Long.MIN_VALUE;
            for (int j : b[i]) {
                summ += j;
                maxSum[i] = Math.max(maxSum[i], summ - minimal[i]);
                minimal[i] = Math.min(minimal[i], summ);
                maximal[i] = Math.max(maximal[i], summ);
            }
            sum[i] = summ;
        }
        long curMin = 0;
        long current = 0;
        long answer = Long.MIN_VALUE;
        for (int iteration = 0; iteration < m; iteration++) {
            int i = in.nextInt() - 1;
            answer = Math.max(answer, maxSum[i]);
            answer = Math.max(answer, maximal[i] + current - curMin);
            curMin = Math.min(curMin, current + minimal[i]);
            current += sum[i];
        }
        out.println(answer);
    }
}
