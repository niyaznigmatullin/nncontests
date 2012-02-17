package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TaskG {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        List<Integer> answer = new LinkedList<Integer>();
        answer.add(0);
        for (int add = 1; add < n; add++) {
            ListIterator<Integer> it = answer.listIterator();
            while (it.hasNext()) {
                if (a[add][it.next()] == 1) {
                    it.previous();
                    break;
                }
            }
            it.add(add);
        }
        boolean first = true;
        for (int i : answer) {
            if (first) {
                first = false;
            } else {
                out.print(' ');
            }
            out.print(i + 1);
        }
        out.println();
    }
}
