package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class SpoonInMatrix {
    static final String SPOONSTRING = "spoon";
    static final char[] SPOON = SPOONSTRING.toCharArray();

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = new char[n][];
        boolean ok = false;
        for (int i = 0; i < n; i++) {
            String line = in.next().toLowerCase();
            c[i] = line.toCharArray();
            if (line.indexOf(SPOONSTRING) >= 0) {
                ok = true;
            }
        }
        for (int i = 0; !ok && i + SPOON.length <= n; i++) {
            for (int j = 0; !ok && j < m; j++) {
                boolean found = true;
                for (int k = 0; k < SPOON.length; k++) {
                    if (c[i + k][j] != SPOON[k]) {
                        found = false;
                    }
                }
                if (found) {
                    ok = true;
                }
            }
        }
        if (ok) {
            out.println("There is a spoon!");
        } else {
            out.println("There is indeed no spoon!");
        }
    }
}
