package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class ParallelComputing {

    static class Answer {
        int a;
        int b;
        int c;

        Answer(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String toString() {
            return (a + 1) + "+" + (b + 1) + "=" + (c + 1);
        }
    }

    static List<Answer>[] answer;
    static int current;

    static void go(int n, int depth) {
        if (n >> depth == 0) {
            return;
        }
        boolean ok1 = false;
        for (int i = (1 << depth) - 1; i < n; i += 1 << depth + 1) {
            if (i + (1 << depth) < n) {
                answer[current].add(new Answer(i, i + (1 << depth), i + (1 << depth)));
                ok1 = true;
            }
        }
        if (ok1) {
            ++current;
        }
        go(n, depth + 1);
        boolean ok2 = false;
        for (int i = (1 << depth) - 1; i < n; i += 1 << depth + 1) {
            if (i - (1 << depth) >= 0) {
                answer[current].add(new Answer(i, i - (1 << depth), i));
                ok2 = true;
            }
        }
        if (ok2) {
            ++current;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        answer = new List[21];
        for (int i = 0; i < 21; i++) {
            answer[i] = new ArrayList<Answer>();
        }
        current = 0;
        go(n, 0);
        int size = -1;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i].isEmpty()) {
                out.println(size = i);
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            List<Answer> e = answer[i];
            if (e.isEmpty()) {
                break;
            }
            out.print(e.size());
            for (Answer f : e) {
                out.print(" " + f);
            }
            out.println();
        }
    }
}
