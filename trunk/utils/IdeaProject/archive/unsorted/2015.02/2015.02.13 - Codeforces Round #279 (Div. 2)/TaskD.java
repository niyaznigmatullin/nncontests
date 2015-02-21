package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a1 = in.nextInt();
        int b1 = in.nextInt();
        int a2 = in.nextInt();
        int b2 = in.nextInt();
        if ((long) free(a1) * free(b1) != (long) free(a2) * free(b2)) {
            out.println(-1);
            return;
        }
        int two1 = two(a1) + two(b1);
        int three1 = three(a1) + three(b1);
        int two2 = two(a2) + two(b2);
        int three2 = three(a2) + three(b2);
        int best = Integer.MAX_VALUE;
        int ansTwo = -1;
        int ansThree = -1;
        for (int two = 0; two <= two1 || two <= two2; two++) {
            for (int three = 0; three <= three1 && three <= three2; three++) {
                int cost = three1 + three2 - three * 2;
                int c1 = two1 + three1 - three;
                int c2 = two2 + three2 - three;
                if (c1 < two || c2 < two) continue;
                cost += c1 - two;
                cost += c2 - two;
                if (cost < best) {
                    best = cost;
                    ansTwo = two;
                    ansThree = three;
                }
            }
        }
        while (three1 > ansThree) {
            if (a1 % 3 == 0) {
                a1 /= 3;
                a1 *= 2;
                --three1;
                ++two1;
                continue;
            }
            if (b1 % 3 == 0) {
                b1 /= 3;
                b1 *= 2;
                --three1;
                ++two1;
                continue;
            }
            throw new AssertionError();
        }
        while (three2 > ansThree) {
            if (a2 % 3 == 0) {
                a2 /= 3;
                a2 *= 2;
                --three2;
                ++two2;
                continue;
            }
            if (b2 % 3 == 0) {
                b2 /= 3;
                b2 *= 2;
                --three2;
                ++two2;
                continue;
            }
            throw new AssertionError();
        }
        while (two1 > ansTwo) {
            if (a1 % 2 == 0) {
                a1 /= 2;
                --two1;
                continue;
            }
            if (b1 % 2 == 0) {
                b1 /= 2;
                --two1;
                continue;
            }
            throw new AssertionError();
        }
        while (two2 > ansTwo) {
            if (a2 % 2 == 0) {
                a2 /= 2;
                --two2;
                continue;
            }
            if (b2 % 2 == 0) {
                b2 /= 2;
                --two2;
                continue;
            }
            throw new AssertionError();
        }
        out.println(best);
        out.println(a1 + " " + b1);
        out.println(a2 + " " + b2);
    }


    static int free(int n) {
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        return n;
    }

    static int countIt(int n, int a) {
        int count = 0;
        while (n % a == 0) {
            n /= a;
            ++count;
        }
        return count;
    }

    static int three(int n) {
        return countIt(n, 3);
    }

    static int two(int n) {
        return countIt(n, 2);
    }

    static int build(int two, int three) {
        int ret = 1;
        for (int i = 0; i < three; i++) ret *= 3;
        return ret << two;
    }
}
