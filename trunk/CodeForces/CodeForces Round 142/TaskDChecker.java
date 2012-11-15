package mypackage;

import niyazio.FastScanner;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.*;

public class TaskDChecker {
	public Verdict check(FastScanner input, FastScanner expected, FastScanner actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
        List<Test> ret = new ArrayList<Test>();
        Random rand = new Random(123213L);
        for (int t = 0; t < 0; t++) {
            int n = rand.nextInt(5000) + 1;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = rand.nextInt(1000000000) + 1;
            }
            StringBuilder sb = new StringBuilder(n + "\n");
            for (int i : a) {
                sb.append(i + " ");
            }
            ret.add(new Test(sb.toString(), "", 0));
        }
        return ret;
	}
}
