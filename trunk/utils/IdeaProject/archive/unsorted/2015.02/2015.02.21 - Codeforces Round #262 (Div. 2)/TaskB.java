package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        List<Integer> answer = new ArrayList<>();
        for (int s = 1; s <= 88; s++) {
            long z = 1;
            for (int i = 0; i < a; i++) z *= s;
            z *= b;
            z += c;
            if (z <= 0 || z >= 1000000000) continue;
            if (d(z) == s) {
                answer.add((int) z);
            }
        }
        out.println(answer.size());
        Collections.sort(answer);
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(answer));
    }

    static int d(long x) {
        int ret = 0;
        while (x > 0) {
            ret += x % 10;
            x /= 10;
        }
        return ret;
    }
}
