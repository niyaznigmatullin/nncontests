package lib.test.on2013_08.on2013_08_25_.Railsort;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class Railsort {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[1 << n];
        for (int i = 0; i < 1 << n; i++) {
            a[i] = in.nextInt() - 1;
        }
        answer = new ArrayList<>();
        go(n, 1 << (n - 1), a);
        for (int i : answer) {
            out.print(1 + i + " ");
        }
    }

    static List<Integer> answer;

    static void go(int n, int half, int[] a) {
        if (n == 0) return;
        int quart = n == 1 ? 0 : (1 << n - 2);
        int[] z = new int[1 << n - 1];
        int cn = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= half) {
                answer.add(a[i]);
            } else {
                answer.add(a[i]);
                answer.add(a[i]);
                z[cn++] = a[i];
            }
        }
        go(n - 1, half - quart, z);
        cn = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] >= half) {
                answer.add(a[i]);
                z[cn++] = a[i];
            }
        }
        go(n - 1, half + quart, z);
    }
}
