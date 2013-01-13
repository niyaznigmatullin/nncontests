package lib.test.on2013_01.on2013_01_11_Codeforces_Beta_Round__23.C___Oranges_and_Apples;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = 2 * in.nextInt() - 1;
        Box[] a = new Box[n];
        long allOrange = 0;
        for (int i = 0; i < n; i++) {
            a[i] = new Box(in.nextInt(), in.nextInt(), i);
            allOrange += a[i].orange;
        }
        Arrays.sort(a, new Comparator<Box>() {
            public int compare(Box o1, Box o2) {
                return o1.apple - o2.apple;
            }
        });
        long countOrange = 0;
        for (int i = 0; i < n; i += 2) {
            countOrange += a[i].orange;
        }
        out.println("YES");
        if (countOrange * 2 >= allOrange) {
            for (int i = 0; i < n; i += 2) {
                out.print(a[i].id + 1 + " ");
            }
        } else {
            for (int i = 1; i < n; i += 2) {
                out.print(a[i].id + 1 + " ");
            }
            out.print(a[n - 1].id + 1 + " ");
        }
        out.println();
    }

    static class Box {
        int apple;
        int orange;
        int id;

        Box(int apple, int orange, int id) {
            this.apple = apple;
            this.orange = orange;
            this.id = id;
        }
    }
}
