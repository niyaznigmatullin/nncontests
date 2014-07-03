package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class MagicTrick {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int r1 = in.nextInt() - 1;
        int[][] a = in.readInt2DArray(4, 4);
        int r2 = in.nextInt() - 1;
        int[][] b = in.readInt2DArray(4, 4);
        boolean[] can1 = new boolean[16];
        boolean[] can2 = new boolean[16];
        for (int i : a[r1]) can1[i - 1] = true;
        for (int i : b[r2]) can2[i - 1] = true;
        int count = 0;
        for (int i = 0; i < 16; i++) if (can1[i] && can2[i]) ++count;
        out.print("Case #" + testNumber + ": ");
        if (count == 0) {
            out.println("Volunteer cheated!");
        } else if (count > 1) {
            out.println("Bad magician!");
        } else {
            for (int i = 0; i < 16; i++) if (can1[i] && can2[i]) out.println(i + 1);
        }
    }
}
