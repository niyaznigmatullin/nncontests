package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class I {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] type = new int[n];
        final int[] w = new int[n];
        int na = a;
        int nb = b;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            type[i] = in.nextInt();
            w[i] = in.nextInt();
            order[i] = i;
        }
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(w[o1], w[o2]);
            }
        });
        int[] weGet = new int[n];
        Arrays.fill(weGet, -1);
        int all = 0;
        long power = 0;
        for (int i : order) {
            if (all == na + nb) break;
            if (type[i] == 3) {
                ++all;
                weGet[i] = 3;
                power += w[i];
                continue;
            }
            if (type[i] == 1) {
                if (a > 0) {
                    --a;
                    weGet[i] = 1;
                    ++all;
                    power += w[i];
                }
            } else {
                if (b > 0) {
                    --b;
                    weGet[i] = 2;
                    ++all;
                    power += w[i];
                }
            }
        }
        for (int i : order) {
            if (weGet[i] == 3) {
                if (a > 0) {
                    --a;
                    weGet[i] = 1;
                } else if (b > 0) {
                    --b;
                    weGet[i] = 2;
                } else {
                    throw new AssertionError();
                }
            }
        }
        a = 0;
        b = na;
        out.println(all + " " + power);
        for (int i : order) {
            if (weGet[i] == 1) {
                out.println((i + 1) + " " + (++a));
            } else if (weGet[i] == 2) {
                out.println((i + 1) + " " + (++b));
            }
        }

    }
}
