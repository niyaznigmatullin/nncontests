package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {

    static int getC(int x) {
        int i = 0;
        while (i * (i - 1) / 2 < x) ++i;
        if (i * (i - 1) / 2 == x) return i;
        return -1;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a00 = in.nextInt();
        int a01 = in.nextInt();
        int a10 = in.nextInt();
        int a11 = in.nextInt();
        if (a00 == 0 && a01 == 0 && a10 == 0 && a11 == 0) {
            out.println("0");
            return;
        }
        int c0 = getC(a00);
        int c1 = getC(a11);
        if (a01 + a10 != 0) {
            if (c0 == 0) ++c0;
            if (c1 == 0) ++c1;
        }
        if (c0 < 0 || c1 < 0 || a01 + a10 != (long) c0 * c1) {
            out.println("Impossible");
            return;
        }
        int all = c0 * c1;
        int countRight = 0;
        while (all != a01 && all - c1 >= a01) {
            ++countRight;
            all -= c1;
        }
        if (all == a01) {
            for (int i = 0; i < c0 - countRight; i++) out.print('0');
            for (int i = 0; i < c1; i++) out.print('1');
            for (int i = 0; i < countRight; i++) out.print('0');
            out.println();
            return;
        }
        int move = all - a01;
        int countLeft = c0 - countRight - 1;
        for (int i = 0; i < countLeft; i++) out.print('0');
        for (int i = 0; i < move; i++) out.print('1');
        out.print('0');
        for (int i = move; i < c1; i++) out.print('1');
        for (int i = 0; i < countRight; i++) out.print('0');
        out.println();
    }
}
