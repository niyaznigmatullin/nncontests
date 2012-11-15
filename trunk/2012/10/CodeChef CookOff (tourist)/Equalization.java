package mypackage;

import math.Factor;
import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equalization {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            in.next();
        }
        Factor[] f = MathUtils.factorize(n);
        List<Group> a = new ArrayList<Group>();
        for (int i = 0; i < n; i++) {
            a.add(new Group(new ArrayList<Integer>(Arrays.asList(i + 1))));
        }
        List<int[]> answers = new ArrayList<int[]>();
        for (Factor e : f) {
            for (int pow = 0; pow < e.pow; pow++) {
                List<Group> next = new ArrayList<Group>();
                for (int i = 0; i < a.size(); i += e.prime) {
                    for (int j = 0; j < a.get(i).g.size(); j++) {
                        int[] ret = new int[(int) e.prime];
                        for (int k = 0; k < e.prime; k++) {
                            ret[k] = a.get(i + k).g.get(j);
                        }
                        answers.add(ret);
                    }
                    List<Integer> newGroup = new ArrayList<Integer>();
                    for (int k = 0; k < e.prime; k++) {
                        newGroup.addAll(a.get(i + k).g);
                    }
                    next.add(new Group(newGroup));
                }
                a = next;
            }
        }
        out.println(answers.size());
        for (int[] e : answers) {
            out.print(e.length);
            for (int i : e) {
                out.print(" " + i);
            }
            out.println();
        }
    }

    static class Group {
        List<Integer> g;

        Group(List<Integer> g) {
            this.g = g;
        }
    }
}
