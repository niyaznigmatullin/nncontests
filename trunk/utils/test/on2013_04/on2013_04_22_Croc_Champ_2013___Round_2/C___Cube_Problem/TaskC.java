package lib.test.on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.C___Cube_Problem;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        if (n % 3 != 0) {
            out.println(0);
            return;
        }
        n /= 3;
        long[] divisors;
        {
            List<Long> d = new ArrayList<>();
            for (long i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    d.add(i);
                    if (i * i != n) d.add(n / i);
                }
            }
            divisors = new long[d.size()];
            for (int i = 0; i < divisors.length; i++) {
                divisors[i] = d.get(i);
            }
            Arrays.sort(divisors);
        }
        long ans = 0;
        for (long ac : divisors) {
            for (long ab : divisors) {
                if (ab > ac) {
                    break;
                }
                if ((n / ac) % ab != 0) {
                    continue;
                }
                long bc = n / ac / ab;
                if (bc < ac || bc < ab) {
                    break;
                }
                long twiceB = ab + bc - ac;
                if ((twiceB & 1) != 0) {
                    continue;
                }
                long b = twiceB >> 1;
                long a = ab - b;
                if (a <= 0) {
                    continue;
                }
                long c = bc - b;
                if (c <= 0 || c < b) {
                    continue;
                }
                if ((a + b) * (b + c) * (a + c) == n) {
                    if (a == b && b == c) {
                        ans++;
                    } else if (a == b || b == c) {
                        ans += 3;
                    } else {
                        ans += 6;
                    }
                }
            }
        }
        out.println(ans);
    }
}
