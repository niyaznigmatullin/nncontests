package lib.test.on2012_12.on2012_12_15_.A;





import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class A {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int r = in.nextInt();
        int c = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        for (int row1 = 0; row1 < r; row1++) {
            for (int row2 = 0; row2 < a; row2++) {
                for (int col1 = 0; col1 < c; col1++) {
                    for (int col2 = 0; col2 < b; col2++) {
                        if (((row1 ^ col1) & 1) == 0) {
                            out.print('X');
                        } else {
                            out.print('.');
                        }
                    }
                }
                out.println();
            }
        }
    }
}
