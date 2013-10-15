package lib.test.on2013_06.on2013_06_08_IPSC_2013.J;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class J {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        for (int mask = 0; mask < 1 << 8; mask++) {
            boolean ok1 = (mask & 1) == 1;
            boolean ok2 = ((mask >> 7) & 1) == 0;
            boolean ok3 = false;
            for (int m2 = 0; m2 < 8; m2++) {
                if (((mask >> m2) & 1) == ((mask >> (m2 ^ 7)) & 1)) {
                    ok3 = true;
                    break;
                }
            }
            boolean ok4 = false;
            for (int m2 = 0; m2 < 8; m2++) {
                for (int m3 = 0; m3 < 8; m3++) {
                    boolean greater = true;
                    for (int j = 0; j < 3; j++) {
                        if ((1 & (m2 >> j)) < (1 & (m3 >> j))) {
                            greater = false;
                            break;
                        }
                    }
                    if (!greater) continue;
                    if ((1 & (mask >> m2)) < (1 & (mask >> m3))) {
                        ok4 = true;
                    }
                }
            }
            boolean ok5 = true;
            for (int m2 = 0; m2 < 8; m2++) {
                boolean ok = true;
                for (int m3 = 0; m3 < 8; m3++) {
                    int val = 0;
                    for (int j = 0; j < 3; j++) {
                        val ^= (1 & (m2 >> j)) & (1 & (m3 >> j));
                    }
                    if (val != ((mask >> m3) & 1)) ok = false;
                }
                if (ok) {
                    ok5 = false;
                }
            }
            if (ok1 && ok2 && ok3 && ok4 && ok5) {
                for (int m = 0; m < 8; m++) {
                    if (m > 0) out.print(' ');
                    out.print((mask >> m) & 1);
                }
                out.println();
            }
        }

    }
}
