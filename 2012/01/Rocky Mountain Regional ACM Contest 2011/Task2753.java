package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task2753 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k1 = in.nextInt();
        int k2 = in.nextInt();
        if (n == 0 && k1 == 0 && k2 == 0) {
            throw new UnknownError();
        }
        Element[] a = new Element[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Element(in.nextInt(), i);
        }
        Arrays.sort(a);
        out.println("Case " + testNumber);
        int[] ans1 = new int[k1];
        for (int i = 0; i < k1; i++) {
            ans1[i] = a[i].num + 1;
        }
        Arrays.sort(ans1);
        List<Integer> ans2 = new ArrayList<Integer>();
        for (int i = n - 1; i >= n - k2; i--) {
            ans2.add(a[i].num + 1);
        }
        Collections.sort(ans2);
        Collections.reverse(ans2);
        out.printArray(ans1);
        out.printArray(ArrayUtils.toPrimitiveArray(ans2));
    }

    static class Element implements Comparable<Element> {
        int x;
        int num;

        Element(int x, int num) {
            this.x = x;
            this.num = num;
        }

        public int compareTo(Element o) {
            return x - o.x;
        }
    }
}
