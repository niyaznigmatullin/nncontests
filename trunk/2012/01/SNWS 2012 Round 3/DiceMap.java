package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class DiceMap {
    // up, S, W, E, N, down

    static long curX;
    static long curY;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        boolean rev = false;
        int[] what = new int[]{1, 2, 3, 4, 5, 6};
        curX = curY = 0;
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == '-') {
                rev = true;
                i++;
                continue;
            } else if (s.charAt(i) == '+') {
                rev = false;
                i++;
                continue;
            } else if (s.charAt(i) == 'X') {
                rollH(1, rev, what);
                i++;
            } else if (s.charAt(i) == 'Y') {
                rollV(1, rev, what);
                i++;
            } else if (Character.isDigit(s.charAt(i))) {
                int times = 0;
                while (Character.isDigit(s.charAt(i))) {
                    times = times * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (s.charAt(i) == 'Y') {
                    rollV(times, rev, what);
                } else {
                    rollH(times, rev, what);
                }
                i++;
            } else if (s.charAt(i) == '.') {
                break;
            }
        }
        out.println(curX + " " + curY + " " + what[0]);
	}

    static void rollV(int z, boolean rev, int[] a) {
        if (rev) {
            curY -= z;
            z = (4 - (z & 3));
        } else {
            curY += z;
            z &= 3;
        }
        while (z-- > 0) {
            rollUp(a);
        }
    }

    static void rollH(int z, boolean rev, int[] a) {
        if (rev) {
            curX -= z;
            z = (4 - (z & 3));
        } else {
            curX += z;
            z &= 3;
        }
        while (z-- > 0) {
            rollRight(a);
        }
    }

    static void rollRight(int[] a) {
        int tmp = a[5];
        a[5] = a[3];
        a[3] = a[0];
        a[0] = a[2];
        a[2] = tmp;
    }

    static void rollUp(int[] a) {
        int tmp = a[5];
        a[5] = a[4];
        a[4] = a[0];
        a[0] = a[1];
        a[1] = tmp;
    }
}
