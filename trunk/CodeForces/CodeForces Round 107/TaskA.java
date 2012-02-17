package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        List<Long> divs = new ArrayList<Long>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            divs.add(i);
            if (i * i != n) {
                divs.add(n / i);
            }
        }
        Collections.sort(divs);
        boolean[] win = new boolean[divs.size()];
        long[] move = new long[divs.size()];
        for (int i = 0; i < win.length; i++) {
            long curD = divs.get(i);
            boolean couldGetMove = false;
            for (int j = 1; j < i; j++) {
                long nextD = divs.get(j);
                if (curD % nextD == 0) {
                    if (!win[j]) {
                        move[i] = nextD;
                        win[i] = true;
                    }
                    couldGetMove = true;
                }
            }
            if (!couldGetMove) {
                win[i] = true;
            }
        }
        if (win[divs.size() - 1]) {
            out.println(1);
            out.println(move[divs.size() - 1]);
        } else {
            out.println(2);
        }
	}
}
