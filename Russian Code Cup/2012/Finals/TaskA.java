package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int length = in.nextInt();
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }
        final int[] where = new int[n + m];
        for (int i = 0; i < n + m; i++) {
            where[i] = (i < n ? a[i] : b[i - n]);
        }
        Comparator<Integer> birdComp = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return where[o1] - where[o2];
            }
        };
        Integer[] order = new Integer[n + m];
        for (int i = 0; i < n + m; i++) {
            order[i] = i;
        }
        Arrays.sort(order, birdComp);
        NavigableSet<Integer> runRight = new TreeSet<Integer>(birdComp);
        NavigableSet<Integer> runLeft = new TreeSet<Integer>(birdComp);
        for (int i = 0; i < n; i++) {
            runRight.add(i);
        }
        for (int i = 0; i < m; i++) {
            runLeft.add(n + i);
        }
        long time1 = 0;
        long time2 = 0;
        long[] ans = new long[n + m];
        int left = 0;
        int right = n + m;
        while (!runLeft.isEmpty() || !runRight.isEmpty()) {
            Integer last = runRight.isEmpty() ? null : runRight.last();
            Integer first = runLeft.isEmpty() ? null : runLeft.first();
            if (last != null && first != null) {
                long p1 = where[first] - time1 + time2;
                long p2 = length - (where[last] + time1 - time2);
//                System.err.println(p1 + " " + p2);
                if (p1 == p2) {
                    ans[left++] = time1 + time2 + p1;
                    ans[--right] = time1 + time2 + p2;
                    runRight.pollLast();
                    runLeft.pollFirst();
                    time1 += p1;
                } else if (p1 > p2) {
                    ans[--right] = time1 + time2 + p2;
                    time1 += p2;
                    runRight.pollLast();
                    long t = time1;
                    time1 = time2;
                    time2 = t;
                    NavigableSet<Integer> tempSet = runLeft;
                    runLeft = runRight;
                    runRight = tempSet;
                } else {
                    ans[left++] = time1 + time2 + p1;
                    time1 += p1;
                    runLeft.pollFirst();
                    long t = time1;
                    time1 = time2;
                    time2 = t;
                    NavigableSet<Integer> tempSet = runLeft;
                    runLeft = runRight;
                    runRight = tempSet;
                }
            } else if (last == null) {
                long p1 = where[first] - time1 + time2;
                ans[left++] = time1 + time2 + p1;
                time1 += p1;
                runLeft.pollFirst();
                long t = time1;
                time1 = time2;
                time2 = t;
                NavigableSet<Integer> tempSet = runLeft;
                runLeft = runRight;
                runRight = tempSet;
            } else {
                long p2 = length - (where[last] + time1 - time2);
                ans[--right] = time1 + time2 + p2;
                time1 += p2;
                runRight.pollLast();
                long t = time1;
                time1 = time2;
                time2 = t;
                NavigableSet<Integer> tempSet = runLeft;
                runLeft = runRight;
                runRight = tempSet;
            }
//            System.out.println(runLeft + " " + runRight + " " + time1 + " " + time2);
        }
        long[] answer = new long[n + m];
        for (int i = 0; i < n + m; i++) {
            answer[order[i]] = ans[i];
        }
        for (int i = 0; i < n; i++) {
            out.print(answer[i] + " ");
        }
        out.println();
        for (int i = 0; i < m; i++) {
            out.print(answer[i + n] + " ");
        }
        out.println();
    }
}
