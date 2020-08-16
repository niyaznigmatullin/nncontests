package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class Ration {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] type = new int[n];
        long allA = 0;
        long allB = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            type[i] = "AB".indexOf(in.next());
            if (type[i] == 0) {
                allA += a[i];
            } else {
                allB += a[i];
            }
        }
        if (allA == 0 || allB == 0) {
            out.println(allA + allB);
            return;
        }
        long g = MathUtils.gcd(allA, allB);
        allA /= g;
        allB /= g;
        long haveA = 0;
        long haveB = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int add = a[i];
            if (type[i] == 0) {
                ans += getAns(allA, allB, haveA, haveB, add);
                haveA += add;
            } else {
                ans += getAns(allB, allA, haveB, haveA, add);
                haveB += add;
            }
        }
        out.println(ans);
    }

    private int getAns(long allA, long allB, long haveA, long haveB, int add) {
//        if (haveB > 0 && haveB % allB == 0) {    haveA < allA / allB * haveB
//            long needA = haveB / allB * allA;
//            if (needA > haveA && needA <= haveA + add) {
//                return 1;
//            }
//        }
        if (haveA * allB < allA * haveB && (haveA + add) * allB >= allA * haveB && allA * haveB % allB == 0) {
            return 1;
        }
        return 0;
    }
}
