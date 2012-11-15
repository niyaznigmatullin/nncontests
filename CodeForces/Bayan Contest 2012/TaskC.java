package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int leftHole = in.nextInt();
        int rightHole = in.nextInt();
        int n = in.nextInt();
        int[] score = new int[n];
        boolean[] isUp = new boolean[n];
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = in.nextInt();
            isUp[i] = in.next().equals("T");
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        int answer = 0;
        final int height = 100;
        final int width = 100000;
        for (int goUp = 0; goUp < 2; goUp++) {
            all: for (int hits = 1; hits <= n; hits++) {
                int leftHoleVirtual = leftHole + (hits / 2) * height * 2;
                if ((hits & 1) == 1) {
                    leftHoleVirtual += 2 * (height - leftHole);
                }
                double aLine = leftHoleVirtual - rightHole;
                double bLine = width;
                double cLine = -bLine * leftHoleVirtual;
                boolean up = true;
                boolean[] was = new boolean[n];
                int curAnswer = 0;
                for (int v = 0; v < hits; v++, up = !up) {
                    double y = (v + 1) * height;
                    double x = (-cLine - bLine * y) / aLine;
                    boolean ok = false;
                    for (int i = 0; i < n; i++) {
                        if (isUp[i] != up) {
                            continue;
                        }
                        if (compare(a[i], x) <= 0 && compare(x, b[i]) <= 0) {
                            if (was[i]) {
                                continue all;
                            }
                            curAnswer += score[i];
                            was[i] = true;
                            ok = true;
                            break;
                        }
                    }
                    if (!ok) {
                        continue all;
                    }
                }
                if (answer < curAnswer) {
                    answer = curAnswer;
                    System.err.println(hits + " " + goUp);
                }
            }
            leftHole = height - leftHole;
            rightHole = height - rightHole;
            for (int i = 0; i < n; i++) {
                isUp[i] = !isUp[i];
            }
        }
        out.println(answer);
	}

    static final double EPS = 1e-9;

    static int compare(double a, double b) {
        return Math.abs(a - b) < EPS ? 0 : a < b ? -1 : 1;
    }
}
