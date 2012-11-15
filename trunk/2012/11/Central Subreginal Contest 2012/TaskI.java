package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskI {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] c = new int[1];
        c[0] = 1;
        for (int i = 1; ; i++) {
            c = Arrays.copyOf(c, c.length + 1);
            int[] d = new int[c.length];
            d[0] = 1;
            for (int j = 1; j < d.length; j++) {
                d[j] = c[j - 1] + c[j];
                if (d[j] >= n) {
                    out.println(i);
                    List<Integer>[] plays = new List[i];
                    for (int e = 0; e < i; e++) {
                        plays[e] = new ArrayList<Integer>();
                    }
                    for (int z = 0; n > 0 && z < 1 << i; z++) {
                        if (Integer.bitCount(z) == j) {
                            --n;
                            for (int e = 0; e < i; e++) {
                                if (((z >> e) & 1) == 1) {
                                    plays[e].add(n);
                                }
                            }
                        }
                    }
                    for (int e = 0; e < i; e++) {
                        for (int q = 0; q < plays[e].size(); q++) {
                            if (q > 0) {
                                out.print(' ');
                            }
                            out.print(plays[e].get(q) + 1);
                        }
                        out.println();
                    }
                    return;
                }
            }
            c = d;
        }
    }
}
