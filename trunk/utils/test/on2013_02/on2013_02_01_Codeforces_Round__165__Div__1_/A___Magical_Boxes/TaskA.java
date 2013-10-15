package lib.test.on2013_02.on2013_02_01_Codeforces_Round__165__Div__1_.A___Magical_Boxes;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TaskA {


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long answer = 0;
        int n = in.nextInt();
        Map<Integer, Long> map = new TreeMap<Integer, Long>();
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int count = in.nextInt();
            Long z = map.get(k);
            if (z == null) {
                z = 0L;
            }
            map.put(k, z + count);
        }
        for (Map.Entry<Integer, Long> it : map.entrySet()) {
            int k = it.getKey();
            long count = it.getValue();
            if (count == 1) {
                answer = Math.max(answer, k + 1);
            } else {
                int z = Long.numberOfTrailingZeros(Long.highestOneBit(count - 1));
                z = z / 2 + 1;
                answer = Math.max(answer, z + k);
            }
        }
        out.println(answer);
    }
}
