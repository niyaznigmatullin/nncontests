package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int count = 0;
        int last = Integer.MIN_VALUE;
        List<Integer> first = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (last == Integer.MIN_VALUE || last == x) {
                ++count;
            } else {
                first.add(count);
                count = 1;
            }
            last = x;
        }
        if (count > 0) {
            first.add(count);
        }
        int answer = 0;
        while (true) {
            List<Integer> next = new ArrayList<Integer>();
            for (int i = 0; i < first.size(); i++) {
                if (first.get(i) > 1) {
                    answer++;
                }
            }
        }
	}
}
