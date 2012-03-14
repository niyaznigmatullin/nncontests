package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {

    static final int ALPHABET = 30;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int left1 = in.nextInt();
        int right1 = in.nextInt();
        int left2 = in.nextInt();
        int right2 = in.nextInt();
        int ans = 0;
        for (int i = ALPHABET - 1; i >= 0; i--) {
            List<int[]> a = getLimits(left1, right1, i);
            List<int[]> b = getLimits(left2, right2, i);
            for (int[] first : a) {
                for (int[] second : b) {
                    int toLeft = Math.min(first[0], second[0]);
                    int toRight = Math.min(first[1], second[1]);
                    ans = Math.max(ans, toLeft + toRight + 1);
                }
            }
        }
        out.println(ans);
    }

    static List<int[]> getLimits(int left, int right, int letter) {
        int count = getCount(left, right, letter);
        List<int[]> ret = new ArrayList<int[]>();
        if (count == 0) {
            return ret;
        }
        if (count >= 3) {
            ret.add(new int[]{getMaxPart(letter), getMaxPart(letter)});
            return ret;
        }
        List<Integer> all = getAll(left, right, letter);
        for (int i : all) {
            ret.add(new int[]{Math.min(getMaxPart(letter), i - left), Math.min(getMaxPart(letter), right - i)});
        }
        return ret;
    }

    private static int getMaxPart(int letter) {
        return (1 << letter) - 1;
    }

    static int getCount(int left, int right, int letter) {
        int ret = 0;
        long add = 1L << letter + 1;
        for (long i = getFirst(left - 1, letter); i <= right; i += add) {
            ret++;
            if (ret >= 3) {
                return ret;
            }
        }
        return ret;
    }


    static List<Integer> getAll(int left, int right, int letter) {
        long add = 1L << letter + 1;
        List<Integer> ret = new ArrayList<Integer>();
        for (long i = getFirst(left - 1, letter); i <= right; i += add) {
            ret.add((int) i);
        }
        return ret;
    }

    static long getFirst(long x, int letter) {
        x &= ~((1L << letter) - 1);
        x += 1L << letter;
        if (((x >> letter) & 1) == 0) {
            x += 1L << letter;
        }
        return x;
    }
}
